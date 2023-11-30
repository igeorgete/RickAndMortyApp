package com.example.rickandmortyappcompose.useCases

import com.example.rickandmortyappcompose.data.Result
import com.example.rickandmortyappcompose.domain.model.Characters
import com.example.rickandmortyappcompose.domain.repositories.CharacterRepository
import com.example.rickandmortyappcompose.domain.useCases.GetCharactersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharactersUseCaseTest {

    private val repository: CharacterRepository = mockk()
    private val subjectUseCase: GetCharactersUseCase = GetCharactersUseCase(repository)

    @Test
    fun when_invoke_with_page_should_return_characters_from_repository(): Unit = runBlocking {
        // Given
        val page = 1
        val expectedCharacters = listOf(
            Characters(
                id = 1, name = "Rick Sanchez", specie = "Human", image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            )
        )
        val expectedResult = Result.Success(expectedCharacters)


        coEvery { repository.getCharacters(page) } returns flowOf(expectedResult)

        // When
        subjectUseCase.invoke(page)

        // Then
        coVerify(exactly = 1) { repository.getCharacters(page) }

    }
}

