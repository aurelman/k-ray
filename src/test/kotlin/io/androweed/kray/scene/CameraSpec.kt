package io.androweed.kray.scene

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.androweed.kray.core.Vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

internal object CameraSpec : Spek({

    describe("a Camera") {
        context("construction") {
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