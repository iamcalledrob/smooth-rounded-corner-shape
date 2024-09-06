package io.github.iamcalledrob.smoothRoundedCornerShape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

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
        bottomStart = corner,
    )

    constructor(
        smoothing: Float = DefaultSmoothing,
        topStart: Dp,
        topEnd: Dp,
        bottomEnd: Dp,
        bottomStart: Dp,
    ) : this(
        smoothing = smoothing,
        topStart = CornerSize(topStart),
        topEnd = CornerSize(topEnd),
        bottomEnd = CornerSize(bottomEnd),
        bottomStart = CornerSize(bottomStart),
    )

    constructor(
        smoothing: Float = DefaultSmoothing,
        topStartPercent: Int,
        topEndPercent: Int,
        bottomEndPercent: Int,
        bottomStartPercent: Int,
    ) : this(
        smoothing = smoothing,
        topStart = CornerSize(topStartPercent),
        topEnd = CornerSize(topEndPercent),
        bottomEnd = CornerSize(bottomEndPercent),
        bottomStart = CornerSize(bottomStartPercent),
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

        val path = Path.smoothRoundedRectangle(
            smoothing = smoothing,
            size = size,
            topLeft = topLeft,
            topRight = topRight,
            bottomRight = bottomRight,
            bottomLeft = bottomLeft,
        )
        return Outline.Generic(path)
    }
}

/** Matches the "iOS" notch (60%) in Figma */
const val DefaultSmoothing = 0.6f

