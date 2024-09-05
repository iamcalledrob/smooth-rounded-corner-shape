package com.github.iamcalledrob.smoothRoundedCornerShape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon

class SmoothRoundedCornerShape(
    val smoothing: Float = DefaultSmoothing,
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize
) : CornerBasedShape(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart,
) {

    constructor(smoothing: Float = DefaultSmoothing, radius: Dp) : this(smoothing, CornerSize(radius))
    constructor(smoothing: Float = DefaultSmoothing, percent: Int) : this(smoothing, CornerSize(percent))
    constructor(smoothing: Float = DefaultSmoothing, corner: CornerSize) : this(
        smoothing = smoothing,
        topStart = corner,
        topEnd = corner,
        bottomEnd = corner,
        bottomStart = corner
    )

    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ) = SmoothRoundedCornerShape(
        smoothing = smoothing,
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart,
    )

    override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection
    ): Outline {
        val topLeft = if (layoutDirection == LayoutDirection.Ltr) topStart else topEnd
        val topRight = if (layoutDirection == LayoutDirection.Ltr) topEnd else topStart
        val bottomLeft = if (layoutDirection == LayoutDirection.Ltr) bottomStart else bottomEnd
        val bottomRight = if (layoutDirection == LayoutDirection.Ltr) bottomEnd else bottomStart

        val polygon = RoundedPolygon(
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
        )

        return Outline.Generic(polygon.toComposePath())
    }
}

const val DefaultSmoothing = 0.6f


