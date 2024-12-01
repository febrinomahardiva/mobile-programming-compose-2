package org.febrinomahardiva.mobileprogrammingcompose2.ui.screen

import android.Manifest
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.febrinomahardiva.mobileprogrammingcompose2.model.Coordinate
import ovh.plrapps.mapcompose.api.addMarker
import ovh.plrapps.mapcompose.api.hasMarker
import ovh.plrapps.mapcompose.api.moveMarker
import ovh.plrapps.mapcompose.api.scrollTo
import ovh.plrapps.mapcompose.ui.MapUI
import ovh.plrapps.mapcompose.ui.state.MapState

private const val USER_LOCATION = "userLocation"

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
            if (mapState.hasMarker(USER_LOCATION))
                mapState.moveMarker(USER_LOCATION, it.xScaled, it.yScaled)
            else
                mapState.addCustomMarker(USER_LOCATION, it, Color.Blue)
        }
    }

    MapUI(
        modifier = modifier,
        state = mapState
    )
}

private fun MapState.addCustomMarker(
    id: String,
    coordinate: Coordinate,
    color: Color = Color.Red
) {
    addMarker(id, coordinate.xScaled, coordinate.yScaled) {
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = null,
            tint = color
        )
    }
}