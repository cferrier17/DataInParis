package com.example.model.ParisAPI

import java.util.HashMap

class Record {

    var datasetid: String? = null
    var recordid: String? = null
    var fields: Fields? = null
    var geometry: Geometry? = null
    var recordTimestamp: String? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
