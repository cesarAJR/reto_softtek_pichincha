package com.cesar.reto_softtek_pichincha

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.cesar.reto_softtek_pichincha.navigation.Screen
import com.cesar.reto_softtek_pichincha.navigation.SetupNavGraph
import com.cesar.reto_softtek_pichincha.ui.theme.AppTheme
import com.cesar.reto_softtek_pichincha.ui.theme.Reto_softtek_pichinchaTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashscreen = installSplashScreen()
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(2000)
            keepSplashScreen = false
        }
        enableEdgeToEdge()
        setContent {
            AppTheme {
               val firstLauncher =  sharedPreferences.getBoolean("firstLauncher",true)
                var startDestination = Screen.List.route
                if (firstLauncher){
                    startDestination = Screen.OnBoarding.route
                }
                val navController = rememberNavController()
                SetupNavGraph(navController = navController,startDestination)
            }
        }
    }

    fun updateFirstLauncher(){
        sharedPreferences.edit().putBoolean("firstLauncher", false).apply()
    }


}

