package com.example.rickandmortyappcompose.viewmodel

import com.example.rickandmortyappcompose.MainDispatcherRule
import com.example.rickandmortyappcompose.data.Result
import com.example.rickandmortyappcompose.domain.model.Characters
import com.example.rickandmortyappcompose.domain.useCases.GetCharactersUseCase
import com.example.rickandmortyappcompose.ui.home.HomeViewModel
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val getCharactersUseCase: GetCharactersUseCase = mockk()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(getCharactersUseCase)
    }

    @Test
    fun when_getCharactersUseCase_returns_success_verify_state_is_updated(): Unit = runBlocking {
            // Given
            val expectedResult =
                Result.Success(
                    listOf(
                        Characters(
                            id = 1,
                            name = "Rick Sanchez",
                            specie = "Human",
                            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                        )
                    )
                )
            coEvery { getCharactersUseCase.invoke(any()) } returns flowOf(expectedResult)

            // When
            viewModel.getCharacters(false)

            // Then
            Truth.assertThat(viewModel.state.characters).isEqualTo(expectedResult.data)
            Truth.assertThat(viewModel.state.isLoading).isFalse()
            Truth.assertThat(viewModel.state.showPrevious).isFalse()
            Truth.assertThat(viewModel.state.showNext).isTrue()


    }

    @Test
    fun when_getCharactersUseCase_returns_error_verify_state_is_updated_and_event_is_emitted() = runBlocking {
        // Given
        val expectedResult = Result.Error<List<Characters>>("Error message", data = null)
        coEvery { getCharactersUseCase(1) } returns flowOf(expectedResult)

        // When
        viewModel.getCharacters(false)

        // Then
        Truth.assertThat(viewModel.state.isLoading).isFalse()
    }

    @Test
    fun when_getCharactersUseCase_returns_loading_verify_state_is_updated() = runBlocking {
        // Given
        val expectedResult = Result.Loading<List<Characters>>(data = null)
        coEvery { getCharactersUseCase(1) } returns flowOf(expectedResult)

        // When
        viewModel.getCharacters(false)

        // Then
        Truth.assertThat(viewModel.state.isLoading).isTrue()
    }
}


