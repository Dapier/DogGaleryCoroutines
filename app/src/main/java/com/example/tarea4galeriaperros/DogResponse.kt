package com.example.tarea4galeriaperros

import com.google.gson.annotations.SerializedName

class DogResponse (@SerializedName(value = "status") var status: String,
                   @SerializedName(value = "message") var images: List<String>)