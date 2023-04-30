package com.diamount_studio.camkit

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diamount_studio.camkit.Route.VIDEO_PREVIEW_ARG
import com.diamount_studio.camkit.ui.theme.CamKitTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CamKitTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.VIDEO
                    ) {
                        composable(Route.VIDEO) {
                            VideoCaptureScreen(navController = navController)
                        }

                        composable(Route.VIDEO_PREVIEW_FULL_ROUTE) {
                            val uri = it.arguments?.getString(VIDEO_PREVIEW_ARG) ?: ""
                            VideoPreviewScreen(uri = uri)
                        }
                    }
                }


            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CamKitTheme {
        Greeting("Android")
    }
}