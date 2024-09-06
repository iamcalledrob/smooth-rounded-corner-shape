package io.github.iamcalledrob.smoothRoundedCornerShape

import androidx.compose.ui.graphics.Path
import androidx.graphics.shapes.Cubic
import androidx.graphics.shapes.RoundedPolygon

actual fun RoundedPolygon.toComposePath() =
    Path().apply { pathFromCubics(this, cubics) }


// Code from androidx.graphics.shapes.RoundedPolygon.toPath, which is only available for the Android
// target -- and returns an Android Path rather than a Compose Path.

private fun pathFromCubics(path: Path, cubics: List<Cubic>) {
    var first = true
    path.rewind()
    for (element in cubics) {
        val cubic = element
        if (first) {
            path.moveTo(cubic.anchor0X, cubic.anchor0Y)
            first = false
        }
        path.cubicTo(
            cubic.control0X,
            cubic.control0Y,
            cubic.control1X,
            cubic.control1Y,
            cubic.anchor1X,
            cubic.anchor1Y
        )
    }
    path.close()
}