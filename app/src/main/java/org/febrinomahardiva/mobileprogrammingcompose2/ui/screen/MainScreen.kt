package org.febrinomahardiva.mobileprogrammingcompose2.ui.screen

import android.Manifest
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.febrinomahardiva.mobileprogrammingcompose2.model.Coordinate
import ovh.plrapps.mapcompose.api.scrollTo
import ovh.plrapps.mapcompose.ui.MapUI

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    modifier: Modifier
) {
    val viewModel: MainViewModel = viewModel()
    val mapState = viewModel.mapState

    val context = LocalContext.current
    val userLocation by viewModel.getUserLocation(context).collectAsState(null)

    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(Unit) {
        val pos = Coordinate(-6.973377, 107.631543)
        mapState.scrollTo(pos.xScaled, pos.yScaled)

        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        }
    }

    LaunchedEffect(userLocation) {
        userLocation?.let {
            Log.d("MainScreen", "Location: ${it.latitude}, ${it.longitude}")
        }
    }

    MapUI(
        modifier = modifier,
        state = mapState
    )
}