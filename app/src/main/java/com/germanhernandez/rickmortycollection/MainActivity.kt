package com.germanhernandez.rickmortycollection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.germanhernandez.rickmortycollection.presentation.navigation.AppNavHost
import com.germanhernandez.rickmortycollection.ui.theme.RickMortyCollectionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostComposable = rememberNavController()

            RickMortyCollectionTheme {
                Scaffold {
                    AppNavHost(
                        navHostController = navHostComposable,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}
