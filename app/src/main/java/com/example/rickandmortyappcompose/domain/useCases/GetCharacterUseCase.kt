package com.example.rickandmortyappcompose.domain.useCases

import com.example.rickandmortyappcompose.data.Result
import com.example.rickandmortyappcompose.domain.model.Character
import com.example.rickandmortyappcompose.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Result<Character> {
        return repository.getCharacter(id)
    }
}