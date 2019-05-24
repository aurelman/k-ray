package io.androweed.kray.scene

import io.androweed.kray.core.Vector

class Camera(up: Vector, direction: Vector){
    val up: Vector = up.normalized()
    val direction: Vector = direction.normalized()

    val right = this.up cross this.direction
}