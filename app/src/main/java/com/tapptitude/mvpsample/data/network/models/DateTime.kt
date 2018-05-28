package com.tapptitude.mvpsample.data.network.models

import com.google.gson.annotations.SerializedName

data class DateTime(@SerializedName("time") val time: String,
                    @SerializedName("date") val date: String,
                    @SerializedName("millis") val millis: Long)