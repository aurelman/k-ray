package io.androweed.kray.geometry

import io.androweed.kray.core.Position
import io.androweed.kray.core.Ray
import io.androweed.kray.core.Vector

data class Plane(val point: Position, val normal: Vector) : Primitive {

    override fun intersect(ray: Ray): IntersectionResult {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun normalAt(position: Position) = normal.normalized()
}