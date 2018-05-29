package io.androweed.kray.scene.read

import com.fasterxml.jackson.core.JsonParseException
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

internal object SceneReaderSpec : Spek({

    describe("a SceneReader") {

        val sceneReader = SceneReader

        on("reading a not valid json input") {

            it("should raise an exception if Json is malformed") {

                assertThatExceptionOfType(SceneReadingException::class.java)
                        .isThrownBy { sceneReader.read("{[}") }
                        .withCauseInstanceOf(JsonParseException::class.java)
            }

            it("should raise an exception when no camera is defined") {
                assertThatExceptionOfType(SceneReadingException::class.java)
                        .isThrownBy { sceneReader.read("""{  }""") }
                        .withMessage("missing attribute 'camera'")
            }
        }
    }
})