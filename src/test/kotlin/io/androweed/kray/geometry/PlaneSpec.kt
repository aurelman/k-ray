package io.androweed.kray.geometry

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.androweed.kray.core.Position
import io.androweed.kray.core.Vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

internal object PlaneSpec : Spek({

    describe("a Plane") {
        it("should return the normal at any point") {
            val vector = Vector(5.0, 10.0, 30.0)
            val plane = Plane(Position(), vector)

            assertThat(plane.normalAt(Position())).isEqualTo(vector.normalized())
        }
    }
})

