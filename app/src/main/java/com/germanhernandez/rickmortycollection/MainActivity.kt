package com.germanhernandez.rickmortycollection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.germanhernandez.rickmortycollection.presentation.navigation.AppNavHost
import com.germanhernandez.rickmortycollection.ui.theme.RickMortyCollectionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyCollectionTheme {
                val navHostComposable = rememberNavController()
                val snackBarHostState = remember { SnackbarHostState() }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackBarHostState) }
                ) {
                    AppNavHost(
                        navHostController = navHostComposable,
                        snackBarHostState = snackBarHostState,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}
