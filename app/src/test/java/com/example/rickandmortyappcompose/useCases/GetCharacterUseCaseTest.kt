package com.example.rickandmortyappcompose.useCases

import com.example.rickandmortyappcompose.data.Result
import com.example.rickandmortyappcompose.data.source.remote.dto.Location
import com.example.rickandmortyappcompose.data.source.remote.dto.Origin
import com.example.rickandmortyappcompose.domain.model.Character
import com.example.rickandmortyappcompose.domain.repositories.CharacterRepository
import com.example.rickandmortyappcompose.domain.useCases.GetCharacterUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharacterUseCaseTest {

    @Test
    fun when_invoke_use_case_with_character_id_verify_repository_is_called(): Unit = runBlocking {
        // Given
        val characterId = 1
        val expectedCharacter = Character(
            id = 1,
            name = "Rick Sanchez",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            location = Location(
                name = "Earth (Replacement Dimension)",
                url = "https://rickandmortyapi.com/api/location/20"
            ),
            origin = Origin(
                name = "Earth (C-137)",
                url = "https://rickandmortyapi.com/api/location/1"
            ),
            species = "Human",
            status = "Alive",
        )
        val expectedResult = Result.Success(expectedCharacter)

        val repository: CharacterRepository = mockk {
            coEvery { getCharacter(characterId) } returns expectedResult
        }

        val getCharacterUseCase = GetCharacterUseCase(repository)

        // When
        val result = getCharacterUseCase.invoke(characterId)

        // Then
        Truth.assertThat(result).isEqualTo(expectedResult)
    }
}