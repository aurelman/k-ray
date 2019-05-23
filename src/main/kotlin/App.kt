package io.androweed.kray

import io.androweed.kray.scene.read.SceneReader

fun main(args: Array<String>) {
    val input = ("""
            {
                "lights":[
                    { "name": "bob" },
                    { "name": "bob2"}
                ]
            }
        """)
    SceneReader.read(input)
}


