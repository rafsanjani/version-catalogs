#!/usr/bin/env bash
set -euo pipefail

# Path to build.gradle.kts
CONFIG_PATH="$(git rev-parse --show-toplevel)/build.gradle.kts"

echo "🔍 Config path: $CONFIG_PATH"

if [[ ! -f "$CONFIG_PATH" ]]; then
  echo "❌ ERROR: File not found at $CONFIG_PATH"
  exit 1
fi

# Extract old version
OLD_VERSION=$(grep -E 'versionCode\s*=' "$CONFIG_PATH" | sed -E 's/.*=\s*"([^"]+)".*/\1/' || true)

if [[ -z "$OLD_VERSION" ]]; then
  echo "❌ ERROR: Could not parse versionCode from $CONFIG_PATH"
  exit 1
fi

echo "📦 Old version: $OLD_VERSION"

# Generate new version (yyyy.MM.dd)
NEW_VERSION=$(date +'%Y.%m.%d')
echo "🆕 New version: $NEW_VERSION"

# Backup file
cp "$CONFIG_PATH" "$CONFIG_PATH.bak"

# macOS vs Linux sed differences
if [[ "$OSTYPE" == "darwin"* ]]; then
  # macOS BSD sed requires '' after -i and uses escaped parentheses
  sed -i '' -E "s/^[[:space:]]*val[[:space:]]+versionCode[[:space:]]*=[[:space:]]*\"[^\"]+\"/val versionCode = \"$NEW_VERSION\"/" "$CONFIG_PATH"
else
  # GNU sed (Linux)
  sed -i -E "s/^[[:space:]]*val[[:space:]]+versionCode[[:space:]]*=[[:space:]]*\"[^\"]+\"/val versionCode = \"$NEW_VERSION\"/" "$CONFIG_PATH"
fi

# Check result
if diff "$CONFIG_PATH.bak" "$CONFIG_PATH" > /dev/null; then
  echo "⚠️ No changes made — versionCode line might not match expected pattern."
else
  echo "✅ Version updated successfully!"
  echo "----- Diff -----"
  diff -u "$CONFIG_PATH.bak" "$CONFIG_PATH" || true
fi

# Cleanup
rm -f "$CONFIG_PATH.bak"