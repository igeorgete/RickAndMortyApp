package com.example.rickandmortyappcompose.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.rickandmortyappcompose.MainDispatcherRule
import com.example.rickandmortyappcompose.data.Result
import com.example.rickandmortyappcompose.data.source.remote.dto.Location
import com.example.rickandmortyappcompose.data.source.remote.dto.Origin
import com.example.rickandmortyappcompose.domain.model.Character
import com.example.rickandmortyappcompose.domain.useCases.GetCharacterUseCase
import com.example.rickandmortyappcompose.ui.detail.DetailViewModel
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getCharacterUseCase: GetCharacterUseCase = mockk()

    private val savedStateHandle: SavedStateHandle = mockk()

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        coEvery { savedStateHandle.get<Int>("id") } returns 1
        viewModel = DetailViewModel(getCharacterUseCase, savedStateHandle)
    }

    @Test
    fun when_getCharacterUseCase_returns_success_verify_state_is_updated(): Unit = runBlocking {
        // Given
        val expectedResult = Result.Success(
            Character(
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
        )
        coEvery { getCharacterUseCase(1) } returns expectedResult

        // When
        viewModel.getCharacter()

        // Then
        Truth.assertThat(viewModel.state.character).isEqualTo(expectedResult.data)
        Truth.assertThat(viewModel.state.isLoading).isFalse()
    }

    @Test
    fun when_getCharacterUseCase_returns_error_verify_state_is_updated(): Unit = runBlocking {
        // Given
        val expectedResult = Result.Error<Character>(message = "Erro desconhecido", data = null)
        coEvery { getCharacterUseCase(1) } returns expectedResult

        // When
        viewModel.getCharacter()

        // Then
        Truth.assertThat(viewModel.state.isLoading).isFalse()
    }

    @Test
    fun when_getCharacterUseCase_returns_loading_verify_state_is_updated(): Unit = runBlocking {
        // Given
        val expectedResult = Result.Loading<Character>(data = null)
        coEvery { getCharacterUseCase(1) } returns expectedResult

        // When
        viewModel.getCharacter()

        // Then
        Truth.assertThat(viewModel.state.isLoading).isTrue()
    }
}
