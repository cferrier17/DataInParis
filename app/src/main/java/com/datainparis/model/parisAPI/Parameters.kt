package com.example.model.ParisAPI

import java.util.HashMap

class Parameters {

    var dataset: String? = null
    var timezone: String? = null
    var rows: Int? = null
    var format: String? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
