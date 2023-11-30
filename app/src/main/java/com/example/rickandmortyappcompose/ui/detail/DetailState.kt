package com.example.rickandmortyappcompose.ui.detail

import com.example.rickandmortyappcompose.domain.model.Character

data class DetailState(
    val character: Character? = null,
    val isLoading: Boolean = false
)