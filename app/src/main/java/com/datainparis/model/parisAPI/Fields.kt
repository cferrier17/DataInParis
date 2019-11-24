package com.example.model.ParisAPI

import java.util.HashMap

class Fields {

    var codeSite: String? = null
    var packetsout: Int? = null
    var deviceOperatingSystemNameVersion: String? = null
    var deviceConstructorName: String? = null
    var userlanguage: String? = null
    var duration: Int? = null
    var devicePortalFormat: String? = null
    var geoPoint2d: List<Double>? = null
    var datetime: String? = null
    var deviceBrowserNameVersion: String? = null
    var endtimeOrDash: String? = null
    var incomingnetworklabel: String? = null
    var donneeSortanteGigaoctet: Double? = null
    var donneeEntranteGo: Double? = null
    var incomingzonelabel: String? = null
    var geoShape: GeoShape? = null
    var tempsDeSessionsEnMinutes: Double? = null
    var packetsin: Int? = null
    var bytesout: Int? = null
    var bytesin: Int? = null
    private val additionalProperties = HashMap<String, Any>()

    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
