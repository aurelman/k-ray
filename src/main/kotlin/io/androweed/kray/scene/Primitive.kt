package io.androweed.kray.scene

import io.androweed.kray.core.Position
import io.androweed.kray.core.Ray
import io.androweed.kray.core.Vector

interface Primitive {
    fun intersect(ray: Ray): IntersectionResult

    fun normalAt(position: Position): Vector
}

class IntersectionResult(private val primitive: Primitive, val intersect: Boolean, val intersection: Position?) {

    val normal by lazy {
        primitive.normalAt(intersection!!).normalize()
    }
}