package com.example.model.ParisAPI

import java.util.HashMap

class WifiResponse {

    var nhits: Int? = null
    var parameters: Parameters? = null
    var records: List<Record>? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
