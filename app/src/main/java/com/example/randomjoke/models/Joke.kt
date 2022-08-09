package com.example.randomjoke.models

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("joke")
    var joke: String
)
