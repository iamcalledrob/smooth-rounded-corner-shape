# smoothRoundedCornerShape

## Introduction
Kotlin Multiplatform library that provides `SmoothRoundedCornerShape` -- which works just like `RoundedCornerShape`,
but takes an additional `smoothing` parameter.

The `smoothing` value approximately maps to Figma's corner smoothing setting as a fraction, for example
`smoothing: 0.6f` is roughly equivalent to Figma's `Corner smoothing: 60%`


There is no path/smoothing logic contained in this library, it's just a lightweight binding on top of [androidx.graphics.shapes](https://developer.android.com/reference/kotlin/androidx/graphics/shapes/package-summary)

## Installation
Add the [jitpack](https://jitpack.io/) repository to your build file:
```kotlin
repositories {
    maven { url 'https://jitpack.io' }
}
```

Then add as a module dependency:
```kotlin
dependencies {
    implementation("com.github.iamcalledrob:smoothRoundedCornerShape:1.0.1")
}
```

## Usage
Use in place of `RoundedCornerShape` for example:
```kotlin
val shape = SmoothRoundedCornerShape(smoothing = 0.6f, radius = 10.dp)
Box(Modifier.background(Color.Red, shape = shape).width(128.dp).height(64.dp))
```


## Notes
Currently only builds desktop (jvm) and android targets, since only those have been tested. Feel free to make a PR for
other platforms.
