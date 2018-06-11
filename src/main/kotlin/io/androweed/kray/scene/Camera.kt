package io.androweed.kray.scene

import io.androweed.kray.core.Vector

class Camera(up: Vector, direction: Vector){
    val up: Vector = up.normalize()
    val direction: Vector = direction.normalize()

    val right = this.up cross this.direction
}