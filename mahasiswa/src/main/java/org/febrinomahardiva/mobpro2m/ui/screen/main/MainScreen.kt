package org.febrinomahardiva.mobpro2m.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseUser
import org.febrinomahardiva.mobpro2m.R
import org.febrinomahardiva.mobpro2s.ui.AppBarWithLogout
import org.febrinomahardiva.mobpro2s.ui.UserProfileCard

@Composable
fun MainScreen(
    user: FirebaseUser
) {
    Scaffold(
        topBar = { AppBarWithLogout(R.string.app_name) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            UserProfileCard(user)
        }
    }
}