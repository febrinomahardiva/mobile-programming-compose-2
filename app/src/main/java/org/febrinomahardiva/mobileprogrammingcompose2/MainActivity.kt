package org.febrinomahardiva.mobileprogrammingcompose2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.febrinomahardiva.mobileprogrammingcompose2.ui.screen.app.DosenApp
import org.febrinomahardiva.mobileprogrammingcompose2.ui.theme.MobileProgrammingCompose2Theme
import org.febrinomahardiva.mobpro2s.ui.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileProgrammingCompose2Theme {
                DosenApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DosenAppPreview() {
    MobileProgrammingCompose2Theme {
        WelcomeScreen(
            appLogo = R.mipmap.ic_launcher,
            appName = R.string.app_name
        )
    }
}