package org.febrinomahardiva.mobileprogrammingcompose2.ui.screen

import androidx.lifecycle.ViewModel
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.scale
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.layout.Forced
import ovh.plrapps.mapcompose.ui.state.MapState
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.pow

class MainViewModel : ViewModel() {

    private val tileStreamProvider = makeProvider()

    private val maxLevel = 17
    private val minLevel = 13
    private val tileSize = 256
    private val mapSize = tileSize * 2.0.pow(maxLevel).toInt()

    val mapState = MapState(
        levelCount = maxLevel + 1, mapSize, mapSize, workerCount = 16
    ) {
        minimumScaleMode(Forced((1 / 2.0.pow(maxLevel - minLevel)).toFloat()))
    }.apply {
        addLayer(tileStreamProvider)
        scale = 2f
    }


    private fun makeProvider() = TileStreamProvider { row, col, zoomLv1 ->
        try {
            val url = URL("https://tile.openstreetmap.org/" +
                "$zoomLv1/$col/$row.png")
            val connection = url.openConnection() as HttpURLConnection
            connection.setRequestProperty("User-Agent",
                "Chrome/120.0.0.0 Safari/537.36")
            connection.doInput = true
            connection.connect()
            BufferedInputStream(connection.inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}