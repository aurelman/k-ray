package io.androweed.kray.scene.read


import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import mu.KotlinLogging
import java.io.File
import java.io.FileReader
import java.io.Reader
import java.io.StringReader

private val logger = KotlinLogging.logger {}

object SceneReader {

    private val mapper = jacksonObjectMapper()

    /**
     * Reads from a string
     */
    fun read(input: String): SceneDesc{
        logger.info { "reading scene from string content" }
        logger.debug { "actual content is :\n$input" }
        return read(StringReader(input))
    }
    /**
     * Reads from a file
     */
    fun read(file: File): SceneDesc {
        logger.info { "reading scene from file ${file.absolutePath}" }
        return read(FileReader(file))
    }

    /**
     * Reads from a generic Reader
     */
    fun read(reader : Reader): SceneDesc {
        logger.info { "actually reading scene" }

        try {
            val sceneSpec = mapper.readValue<SceneDesc>(reader)
            logger.info { "finished reading scene" }
            return validate(sceneSpec)
        } catch (ex: JsonParseException) {
            throw SceneReadingException("unable to parse input", ex)
        } catch (ex: MissingKotlinParameterException) {
            throw SceneReadingException("missing attribute '${ex.parameter.name}'", ex)
        }
    }


    private fun validate(sceneDesc: SceneDesc) : SceneDesc {
        return sceneDesc
    }
}

class SceneReadingException(override val message: String?, override val cause: Throwable?) : Exception(message, cause)
