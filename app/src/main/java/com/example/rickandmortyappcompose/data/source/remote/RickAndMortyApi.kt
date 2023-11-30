package com.example.rickandmortyappcompose.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.rickandmortyappcompose.data.source.remote.dto.CharactersDto
import com.example.rickandmortyappcompose.data.source.remote.dto.CharacterDto
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersDto

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): CharacterDto
}