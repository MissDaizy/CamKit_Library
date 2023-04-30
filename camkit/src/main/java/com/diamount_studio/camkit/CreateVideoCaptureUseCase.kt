package com.diamount_studio.camkit

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.video.*
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

@RequiresApi(Build.VERSION_CODES.P)
suspend fun Context.createVideoCaptureUseCase(
    lifecycleOwner: LifecycleOwner,
    cameraSelector: CameraSelector,
    previewView: PreviewView
): VideoCapture<Recorder> {
    // Provides a camera preview stream for displaying on-screen
    val preview = Preview.Builder()
        .build()
        .apply { setSurfaceProvider(previewView.surfaceProvider) }
    // Defines the desired quality setting
    val qualitySelector = QualitySelector.from(
        Quality.FHD,
        FallbackStrategy.lowerQualityOrHigherThan(Quality.FHD)
    )
    /*
    We create an Recorder and use it to get the VideoCapture object
    by calling VideoCapture.withOutput(recorder)
    */
    val recorder = Recorder.Builder()
        .setExecutor(mainExecutor)
        .setQualitySelector(qualitySelector)
        .build()
    val videoCapture = VideoCapture.withOutput(recorder)

    /*
    A camera provider is an object of ProcessCameraProvider singleton
    that allows us to bind the lifecycle of cameras to any LifecycleOwner
    within an application’s process.
     */
    val cameraProvider = getCameraProvider()
    cameraProvider.unbindAll()
    cameraProvider.bindToLifecycle(
        lifecycleOwner,
        cameraSelector,
        preview,
        videoCapture
    )

    return videoCapture
}