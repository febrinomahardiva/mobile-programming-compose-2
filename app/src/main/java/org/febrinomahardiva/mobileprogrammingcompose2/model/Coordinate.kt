package org.febrinomahardiva.mobileprogrammingcompose2.model

import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.tan

data class Coordinate(
    val latitude: Double,
    val longitude: Double
) {
    val xScaled = (longitude + 180.0) / 360.0
    private val lRadian = Math.toRadians(latitude)
    val yScaled = (1.0 - ln(tan(lRadian) + 1 / cos(lRadian)) / Math.PI) / 2.0
}
