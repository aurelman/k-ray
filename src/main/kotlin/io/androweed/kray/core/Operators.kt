package io.androweed.kray.core

/**
 * Allows to make operation such as : `3.0 * Vector(1.0, 2.0, 3.0)`
 * Relies on the fact the `times` operator is overridden in [Vector]
 */
operator fun Double.times(vec: Vector) =
        vec * this