package com.example.rickandmortyappcompose.domain.repositories

import com.example.rickandmortyappcompose.data.Result
import com.example.rickandmortyappcompose.domain.model.Character
import com.example.rickandmortyappcompose.domain.model.Characters
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(page: Int): Flow<Result<List<Characters>>>

    suspend fun getCharacter(id: Int): Result<Character>
}