package com.example.rickandmortyappcompose.data.repositories

import com.example.rickandmortyappcompose.data.Result
import com.example.rickandmortyappcompose.data.source.remote.RickAndMortyApi
import com.example.rickandmortyappcompose.data.source.remote.dto.toCharacter
import com.example.rickandmortyappcompose.data.source.remote.dto.toListCharacters
import com.example.rickandmortyappcompose.domain.model.Character
import com.example.rickandmortyappcompose.domain.model.Characters
import com.example.rickandmortyappcompose.domain.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {
    override fun getCharacters(page: Int): Flow<Result<List<Characters>>> = flow {
        emit(Result.Loading())
        try {
            val response = api.getCharacters(page).toListCharacters()
            emit(Result.Success(response))
        } catch (e: HttpException) {
            emit(
                Result.Error(
                    message = "Algo deu errado! Tente novamente mais tarde.",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Result.Error(
                    message = "Erro ao conectar com o servidor! Tente novamente mais tarde.",
                    data = null
                )
            )
        }
    }

    override suspend fun getCharacter(id: Int): Result<Character> {
        val response = try {
            api.getCharacter(id)
        } catch (e: Exception) {
            return Result.Error(
                message = "Um erro desconhecido ocorreu. Tente novamente mais tarde."
            )
        }
        return Result.Success(response.toCharacter())
    }
}
