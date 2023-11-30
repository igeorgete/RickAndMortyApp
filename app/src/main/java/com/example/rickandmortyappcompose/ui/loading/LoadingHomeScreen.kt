package com.example.rickandmortyappcompose.ui.loading

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.rickandmortyappcompose.R

@Composable
fun LoadingScreen(onLoadingFinished: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.rick_and_morty_loading_screen),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        CircularProgressIndicator()
    }

    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed({
        onLoadingFinished()
    }, 3000)
}