package io.github.iamcalledrob.smoothRoundedCornerShape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class AbsoluteSmoothRoundedCornerShape(
    val smoothing: Float = DefaultSmoothing,
    topLeft: CornerSize,
    topRight: CornerSize,
    bottomRight: CornerSize,
    bottomLeft: CornerSize
) : CornerBasedShape(
    topStart = topLeft,
    topEnd = topRight,
    bottomEnd = bottomRight,
    bottomStart = bottomLeft,
) {
    constructor(
        smoothing: Float = DefaultSmoothing,
        topLeft: Dp,
        topRight: Dp,
        bottomRight: Dp,
        bottomLeft: Dp,
    ) : this(
        smoothing = smoothing,
        topLeft = CornerSize(topLeft),
        topRight = CornerSize(topRight),
        bottomRight = CornerSize(bottomRight),
        bottomLeft = CornerSize(bottomLeft),
    )

    constructor(
        smoothing: Float = DefaultSmoothing,
        topLeftPercent: Int,
        topRightPercent: Int,
        bottomRightPercent: Int,
        bottomLeftPercent: Int,
    ) : this(
        smoothing = smoothing,
        topLeft = CornerSize(topLeftPercent),
        topRight = CornerSize(topRightPercent),
        bottomRight = CornerSize(bottomRightPercent),
        bottomLeft = CornerSize(bottomLeftPercent),
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
        val path = Path.smoothRoundedRectangle(
            smoothing = smoothing,
            size = size,
            topLeft = topStart,
            topRight = topEnd,
            bottomRight = bottomEnd,
            bottomLeft = bottomStart,
        )
        return Outline.Generic(path)
    }
}
