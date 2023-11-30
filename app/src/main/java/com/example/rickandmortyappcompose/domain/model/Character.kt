package com.example.rickandmortyappcompose.domain.model

import com.example.rickandmortyappcompose.data.source.remote.dto.Location
import com.example.rickandmortyappcompose.data.source.remote.dto.Origin

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String
)
