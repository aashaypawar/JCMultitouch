package com.geeksforgeeks.jcmultitouch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // Creating a Simple Scaffold Layout for the application
            Scaffold(

                // Creating a Top Bar
                topBar = { TopAppBar(title = { Text("GFG | Multi-touch Gestures", color = Color.White) }, backgroundColor = Color(0xff0f9d58)) },

                // Creating Content
                content = {

                    // Creating a Column Layout
                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                        // setting up all transformation states
                        var scale by remember { mutableStateOf(1f) }
                        var rotation by remember { mutableStateOf(0f) }
                        var offset by remember { mutableStateOf(Offset.Zero) }
                        val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
                            scale *= zoomChange
                            rotation += rotationChange
                            offset += offsetChange
                        }

                        Box(
                            Modifier
                                // apply other transformations like rotation and zoom
                                .graphicsLayer(
                                    scaleX = scale,
                                    scaleY = scale,
                                    rotationZ = rotation,
                                    translationX = offset.x,
                                    translationY = offset.y
                                )
                                // add transformable to listen to multitouch transformation events after offset
                                .transformable(state = state)
                                .background(Color.Blue)
                                .fillMaxSize()
                        )

                    }
                }
            )
        }
    }
}
