# CamKit Library

CamKit is an open-source video recording library for Android applications. It allows users to record video from the camera, with options to enable/disable audio and switch between the front and back cameras.

## Getting started

### Prerequisites

Before using CamKit, make sure you have the following:

- Android Studio 4.0 or later
- Android SDK 21 or later
- JDK 1.8 or later

### Installing

To use CamKit in your Android project, follow these steps:

1. Add it in your root `build.gradle` at the end of repositories:

```gradle
  allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
          }
        }
 ```

2. Add the following dependencies in you app level gradle file if not exists:
```gradle
dependencies {
	        implementation 'com.github.MissDaizy:CamKit_Library:Tag'
	}
```

### Using CamKit

To use CamKit in your Android application, follow these steps:

1. Create two composable Screens instances of VideoPreviewScreen and VideoCaptureScreen using Navigation componenent - NavHost:
```kotlin
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
```
## Main Methods
### `VideoCaptureScreen(navController: NavController)`
Screen of Video Capturing with with Two Buttons - Microphone and Switching Camera from front <---> back 

* Parameters
 `navController` - the nav conroller that navigates between screens.
     
* Example
                  
```kotlin  
composable(Route.VIDEO) {
     VideoCaptureScreen(navController = navController)
} 
```
* Screenshot

![Screenshot_20230430_203215_CamKit](https://user-images.githubusercontent.com/68230600/235368133-63107183-a081-4db7-a69b-ae2154afd100.jpg)

                        
### `fun VideoPreviewScreen(uri: String)`
Screen of Video Preview for previewing the video which was recorded now.

* Parameters
 `uri` - the uri of the video which was recorded so it can display the video on the screen.
     
* Example
                  
```kotlin  
composable(Route.VIDEO_PREVIEW_FULL_ROUTE) {
   val uri = it.arguments?.getString(VIDEO_PREVIEW_ARG) ?: ""
      VideoPreviewScreen(uri = uri)
}
```
        
* Screenshot

![Screenshot_20230430_202856_CamKit](https://user-images.githubusercontent.com/68230600/235368164-e74a3f26-42e1-4369-bc16-4fe4474d8ba2.jpg)

