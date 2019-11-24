package com.example.model.ParisAPI

import java.util.HashMap

class Geometry {

    var type: String? = null
    var coordinates: List<Double>? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
