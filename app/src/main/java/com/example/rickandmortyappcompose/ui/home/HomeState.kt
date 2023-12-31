package com.example.rickandmortyappcompose.ui.home

import com.example.rickandmortyappcompose.domain.model.Characters

data class HomeState(
    val characters: List<Characters> = emptyList(),
    val showPrevious: Boolean = false,
    val showNext: Boolean = false,
    val isLoading: Boolean = false
)
