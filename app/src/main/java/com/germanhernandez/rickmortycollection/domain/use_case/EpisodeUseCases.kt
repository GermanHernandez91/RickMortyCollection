package com.germanhernandez.rickmortycollection.domain.use_case

import com.germanhernandez.rickmortycollection.domain.use_case.episode.AddFavouriteEpisodeUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.DeleteFavouriteEpisodeUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.GetAllEpisodesUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.GetAllFavouriteEpisodeUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.GetEpisodeByIdUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.GetFavouriteEpisodeByIdUseCase

data class EpisodeUseCases(
    val addFavouriteEpisodeUseCase: AddFavouriteEpisodeUseCase,
    val deleteFavouriteEpisodeUseCase: DeleteFavouriteEpisodeUseCase,
    val getAllEpisodesUseCase: GetAllEpisodesUseCase,
    val getEpisodeByIdUseCase: GetEpisodeByIdUseCase,
    val getFavouriteEpisodeByIdUseCase: GetFavouriteEpisodeByIdUseCase,
    val getAllFavouriteEpisodeUseCase: GetAllFavouriteEpisodeUseCase
)
