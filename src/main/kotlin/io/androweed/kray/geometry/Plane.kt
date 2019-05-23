package io.androweed.kray.geometry

import io.androweed.kray.core.Position
import io.androweed.kray.core.Ray
import io.androweed.kray.core.Vector

class Plane(vector: Vector) : Primitive {
    private val normal = vector.normalize()

    override fun intersect(ray: Ray): IntersectionResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun normalAt(position: Position) = normal
}