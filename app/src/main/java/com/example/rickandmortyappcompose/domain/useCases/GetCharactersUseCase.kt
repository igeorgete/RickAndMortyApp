package com.example.rickandmortyappcompose.domain.useCases

import com.example.rickandmortyappcompose.data.Result
import com.example.rickandmortyappcompose.domain.model.Characters
import com.example.rickandmortyappcompose.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(page: Int): Flow<Result<List<Characters>>> {
        return repository.getCharacters(page)
    }
}