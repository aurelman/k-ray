package io.androweed.kray.scene.read

import assertk.assertThat
import assertk.assertions.hasClass
import assertk.assertions.hasMessage
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

internal object SceneReaderSpec : Spek({

    describe("a SceneReader") {

        val sceneReader = SceneReader

        context("reading a not valid json input") {

            it("should raise an exception if Json is malformed") {

                assertThat {
                    sceneReader.read("{[}")
                }.thrownError {
                    hasClass(SceneReadingException::class)
                }
            }

            it("should raise an exception when no camera is defined") {
                assertThat {
                    sceneReader.read("""{  }""")
                }.thrownError {
                    hasClass(SceneReadingException::class)
                    hasMessage("missing attribute 'camera'")
                }
            }
        }
    }
})