@file:OptIn(ExperimentalMaterial3Api::class)

package com.foreverrafs.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var randomString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content(text = randomString)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(text: String) {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val state = rememberStandardBottomSheetState(
                initialValue = SheetValue.Hidden,
                skipHiddenState = false,
            )

            LaunchedEffect(Unit) {
                delay(2000L)
                state.partialExpand()
            }

            BottomSheetScaffold(
                scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = state,
                ),
                sheetContent = {
                    Text(
                        text = "Hello from bottom sheet",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
                sheetContentColor = Color.White,
                sheetContainerColor = Color.Blue,
                sheetSwipeEnabled = false,
                sheetDragHandle = null,
            ) {
                Text(text = "Welcome to the world of bottom sheet")
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Content(text = "Hello There")
}
