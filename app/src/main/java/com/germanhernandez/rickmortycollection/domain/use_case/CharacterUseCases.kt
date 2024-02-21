package com.germanhernandez.rickmortycollection.domain.use_case

import com.germanhernandez.rickmortycollection.domain.use_case.character.AddFavouriteCharacterUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.DeleteFavouriteCharacterUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetAllCharactersUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetCharacterByIdUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetFavouriteCharacterByIdUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetFavouriteCharactersUseCase

data class CharacterUseCases(
    val addFavouriteCharacterUseCase: AddFavouriteCharacterUseCase,
    val deleteFavouriteCharacterUseCase: DeleteFavouriteCharacterUseCase,
    val getAllCharactersUseCase: GetAllCharactersUseCase,
    val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    val getFavouriteCharacterByIdUseCase: GetFavouriteCharacterByIdUseCase,
    val getFavouriteCharacterUseCase: GetFavouriteCharactersUseCase
)