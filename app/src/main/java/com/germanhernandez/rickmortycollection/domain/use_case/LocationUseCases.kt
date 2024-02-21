package com.germanhernandez.rickmortycollection.domain.use_case

import com.germanhernandez.rickmortycollection.domain.use_case.location.AddFavouriteLocationUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.DeleteFavouriteLocationUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.GetAllFavouriteLocationsUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.GetAllLocationsUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.GetFavouriteLocationByIdUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.GetLocationByIdUseCase

data class LocationUseCases(
    val addFavouriteLocationUseCase: AddFavouriteLocationUseCase,
    val deleteFavouriteLocationUseCase: DeleteFavouriteLocationUseCase,
    val getAllFavouriteLocationsUseCase: GetAllFavouriteLocationsUseCase,
    val getAllLocationsUseCase: GetAllLocationsUseCase,
    val getLocationByIdUseCase: GetLocationByIdUseCase,
    val getFavouriteLocationByIdUseCase: GetFavouriteLocationByIdUseCase
)
