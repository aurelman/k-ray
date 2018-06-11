package io.androweed.kray.core

import io.androweed.kray.scene.IntersectionResult
import io.androweed.kray.scene.Primitive

/**
 * `Ray` is THE essential type in `k-ray`.
 */
data class Ray(val origin: Position, val direction: Vector) {
    fun intersect(primitive: Primitive): IntersectionResult {
        return primitive.intersect(this)
    }

    //  constructor(origin:Position, direction: Vector) : this(origin, direction.normalize())
}