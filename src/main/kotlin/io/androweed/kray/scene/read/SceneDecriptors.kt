package io.androweed.kray.scene.read

data class SceneDesc(val lights: List<LightDesc> = emptyList(), val camera: CameraDesc)
class CameraDesc
data class LightDesc(val name: String)
