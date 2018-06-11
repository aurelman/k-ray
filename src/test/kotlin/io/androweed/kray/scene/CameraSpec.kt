package io.androweed.kray.scene

import io.androweed.kray.core.Vector
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

internal object CameraSpec : Spek ({

    describe("a Camera") {
        on("construction") {
            val cam = Camera(up = Vector(0.0, 2.0, 0.0), direction = Vector(0.0, 0.0, 2.0))
            it("should normalize components") {
                assertThat(cam.up).isEqualTo(Vector(0.0, 1.0, 0.0))
                assertThat(cam.direction).isEqualTo(Vector(0.0, 0.0, 1.0))
            }

            it("should have a consistent right component computed") {
                assertThat(cam.right).isEqualTo(Vector(1.0, 0.0, 0.0))
            }
        }
    }
})