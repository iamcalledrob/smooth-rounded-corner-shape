package io.github.iamcalledrob.smoothRoundedCornerShape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon

fun Path.Companion.smoothRoundedRectangle(size: Size, radius: Float, smoothing: Float) =
    smoothRoundedRectangle(
        smoothing = smoothing,
        size = size,
        topLeft = radius,
        topRight = radius,
        bottomLeft = radius,
        bottomRight = radius,
    )

fun Path.Companion.smoothRoundedRectangle(
    smoothing: Float,
    size: Size,
    topLeft: Float,
    topRight: Float,
    bottomLeft: Float,
    bottomRight: Float,
): Path =
    RoundedPolygon(
        vertices = floatArrayOf(
            0f, 0f,                     // topLeft
            size.width, 0f,             // topRight
            size.width, size.height,    // bottomRight
            0f, size.height             // bottomLeft
        ),
        perVertexRounding = listOf(
            CornerRounding(radius = topLeft, smoothing = smoothing),
            CornerRounding(radius = topRight, smoothing = smoothing),
            CornerRounding(radius = bottomRight, smoothing = smoothing),
            CornerRounding(radius = bottomLeft, smoothing = smoothing),
        )
    ).toComposePath()