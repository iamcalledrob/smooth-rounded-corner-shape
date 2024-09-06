# SmoothRoundedCornerShape

## Introduction
Kotlin Multiplatform library for Compose that provides `SmoothRoundedCornerShape` -- which works just like
`RoundedCornerShape`, but takes an additional `smoothing` parameter, which provides smoothed superellipse corners.

The `smoothing` value approximately maps to Figma's corner smoothing setting as a fraction, for example
`smoothing: 0.6f` is roughly equivalent to Figma's `Corner smoothing: 60%`

`AbsoluteSmoothRoundedCornerShape` and `Path.smoothRoundedRectangle` are also provided.

There is no path/smoothing logic contained in this library, it's just a lightweight wrapper around
[androidx.graphics.shapes](https://developer.android.com/reference/kotlin/androidx/graphics/shapes/package-summary), which is a non-compose library.

## Installation
Ensure your project is using the maven central repository:
```kotlin
repositories {
    mavenCentral()
}
```

Then add as a module dependency:
```kotlin
dependencies {
    implementation("io.github.iamcalledrob:smoothRoundedCornerShape:1.0.4")
}
```

## Usage
Use in place of `RoundedCornerShape` and `AbsoluteRoundedCornerShape` for example:
```kotlin
// SmoothRoundedCornerShape
SmoothRoundedCornerShape(smoothing = 0.6f, radius = 10.dp)
SmoothRoundedCornerShape(smoothing = 0.6f, percent = 25)
SmoothRoundedCornerShape(smoothing = 0.6f, topStart = 10.dp, topEnd = 5.dp, bottomEnd = 0.dp, bottomStart = 20.dp)
SmoothRoundedCornerShape(smoothing = 0.6f, topStartPercent = 10, topEndPercent = 5, bottomEndPercent = 0, bottomStartPercent = 20)

// AbsoluteSmoothRoundedCornerShape
AbsoluteSmoothRoundedCornerShape(smoothing = 0.6f, topLeft = 10.dp, topRight = 5.dp, bottomRight = 0.dp, bottomLeft = 20.dp)
AbsoluteSmoothRoundedCornerShape(smoothing = 0.6f, topLeftPercent = 10, topRightPercent = 5, bottomRightPercent = 0, bottomLeftPercent = 20)

// Path.smoothRoundedRectangle
Path.smoothRoundedRectangle(smoothing = 0.6f, size = Size(100f, 50f), topLeft = 10f, topRight = 5f, bottomRight = 0f, bottomLeft = 20f)
```


## Notes
Currently only builds desktop (jvm) and android targets, since androidx.graphics.shapes only supports those targets.
Feel free to make a PR for other platforms if they become supported.