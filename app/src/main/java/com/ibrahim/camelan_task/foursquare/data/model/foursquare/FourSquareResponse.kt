package com.ibrahim.camelan_task.foursquare.data.model.foursquare

import com.ibrahim.camelan_task.foursquare.data.model.foursquare.Meta
import com.ibrahim.camelan_task.foursquare.data.model.foursquare.Response

data class FourSquareResponse(
    val meta: Meta,
    val response: Response
)