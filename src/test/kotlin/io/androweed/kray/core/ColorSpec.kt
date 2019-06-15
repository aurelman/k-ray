import assertk.assertThat
import assertk.assertions.hasClass
import assertk.assertions.hasMessage
import assertk.assertions.isCloseTo
import io.androweed.kray.core.Color
import io.androweed.kray.core.times
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.lang.IllegalArgumentException


object ColorSpec : Spek({
    describe("a color instance") {

        context("creating a new instance") {

            it("should not raise any errors when its components are within [0.0, 1.0] range") {
                assertThat {
                    Color(0.0f, 1.0f, 0.0f)
                }.doesNotThrowAnyException()
            }

            it("should raise error if any of its components are negative") {
                assertThat {
                    Color(-1.0f, -1.0f, -1.0f)
                }.thrownError {
                    hasClass(IllegalArgumentException::class)
                    hasMessage("component value must be in [0.0, 1.0]")
                }
            }

            it("should raise error if any of the component is greater than 1.0") {
                assertThat {
                    Color(2.0f, 2.0f, 2.0f)
                }.thrownError {
                    hasClass(IllegalArgumentException::class)
                    hasMessage("component value must be in [0.0, 1.0]")
                }
            }
        }

        context("when added to another vector") {

            it("should return the resulting vector") {
                val result = Color(0.5f, 0.5f, 0.5f) + Color(0.3f, 0.3f, 0.3f)

                assertThat(result.red).isCloseTo(0.8f, 0.0000001f)
                assertThat(result.green).isCloseTo(0.8f, 0.0000001f)
                assertThat(result.blue).isCloseTo(0.8f, 0.0000001f)
            }

            it("should clamp components by dividing resulting components by the max one") {
                val result = Color(0.2f, 1.0f, 1.0f) + Color(0.3f, 0.0f, 1.0f)

                assertThat(result.red).isCloseTo(0.25f, 0.0000001f)
                assertThat(result.green).isCloseTo(0.5f, 0.0000001f)
                assertThat(result.blue).isCloseTo(1f, 0.0000001f)
            }
        }

        context("when multiplied by a scalar value") {


            it("should return the resulting vector") {
                val result = Color(0.4f, 0.4f, 0.4f) * 0.5

                assertThat(result.red).isCloseTo(0.2f, 0.0000001f)
                assertThat(result.green).isCloseTo(0.2f, 0.0000001f)
                assertThat(result.blue).isCloseTo(0.2f, 0.0000001f)
            }

            it("should cap components values when multiplying by a scalar would lead to component higher than 1") {
                val result = Color(1.0f, 0.5f, 0.2f) * 2.0

                assertThat(result.red).isCloseTo(1f, 0.0000001f)
                assertThat(result.green).isCloseTo(0.5f, 0.0000001f)
                assertThat(result.blue).isCloseTo(0.2f, 0.0000001f)
            }
        }

        context("when multiplied by a scalar value left") {

            it("should return the resulting vector") {
                val result = 0.5 * Color(0.4f, 0.4f, 0.4f)

                assertThat(result.red).isCloseTo(0.2f, 0.0000001f)
                assertThat(result.green).isCloseTo(0.2f, 0.0000001f)
                assertThat(result.blue).isCloseTo(0.2f, 0.0000001f)
            }

            it("should cap components values when multiplying by a scalar would lead to component higher than 1") {
                val result = 2.0 * Color(1.0f, 0.5f, 0.2f)

                assertThat(result.red).isCloseTo(1f, 0.0000001f)
                assertThat(result.green).isCloseTo(0.5f, 0.0000001f)
                assertThat(result.blue).isCloseTo(0.2f, 0.0000001f)
            }
        }
    }
})
