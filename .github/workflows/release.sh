#!/usr/bin/env bash
now=$(date +'%Y.%m.%d')

gh api \
  --method POST \
  -H "Accept: application/vnd.github+json" \
  -H "X-GitHub-Api-Version: 2022-11-28" \
  /repos/rafsanjani/version-catalogs/releases \
  -f tag_name="$now" \
 -f target_commitish='main' \
 -f name="$now" \
 -F draft=false \
 -F prerelease=false \
 -F generate_release_notes=true