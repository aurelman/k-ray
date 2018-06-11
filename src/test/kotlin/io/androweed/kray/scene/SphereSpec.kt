package io.androweed.kray.scene

import io.androweed.kray.core.Position
import io.androweed.kray.core.Ray
import io.androweed.kray.core.Vector
import io.androweed.main.io.androweed.kray.scene.Sphere
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

internal object SphereSpec : Spek({

    describe("a Sphere") {

        val sphere = Sphere(
                center = Vector(0.0, 0.0, 0.0),
                radius = 10.0)

        on("testing intersections") {

            it("should detect no intersection if none") {
                val ray = Ray(origin = Vector(100.0, 100.0, 100.0), direction = Vector(12.0, 25.0, 100.0))
                val result = sphere.intersect(ray)
                assertThat(result.intersect).isFalse()
            }

            it("should detect intersection with a shaving ray") {
                val ray  = Ray(origin = Position(x = 0.0, y = 10.0, z = -100.0), direction = Vector(x= 0.0, y = 0.0, z = 1.0))
                val result = sphere.intersect(ray)
                assertThat(result.intersect).isTrue()
                assertThat(result.intersection?.x).isCloseTo(0.0, offset(0.000001))
                assertThat(result.intersection?.y).isCloseTo(10.0, offset(0.000001))
                assertThat(result.intersection?.z).isCloseTo(0.0, offset(0.000001))

                assertThat(result.normal.x).isCloseTo(0.0, offset(0.000001))
                assertThat(result.normal.y).isCloseTo(1.0, offset(0.000001))
                assertThat(result.normal.z).isCloseTo(0.0, offset(0.000001))
            }

            it("should detect intersection with a traversing ray") {
                val ray  = Ray(origin = Position(x = 0.0, y = 0.0, z = -100.0), direction = Vector(x= 0.0, y = 0.0, z = 1.0))

                val result = sphere.intersect(ray)

                assertThat(result.intersect).isTrue()
                assertThat(result.intersection?.x).isCloseTo(0.0, offset(0.000001))
                assertThat(result.intersection?.y).isCloseTo(0.0, offset(0.000001))
                assertThat(result.intersection?.z).isCloseTo(-10.0, offset(0.000001))

                assertThat(result.normal.x).isCloseTo(0.0, offset(0.000001))
                assertThat(result.normal.y).isCloseTo(0.0, offset(0.000001))
                assertThat(result.normal.z).isCloseTo(-1.0, offset(0.000001))
            }
        }
    }
})