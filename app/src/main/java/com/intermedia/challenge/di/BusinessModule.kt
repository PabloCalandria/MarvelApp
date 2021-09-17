package com.intermedia.challenge.di

import com.intermedia.challenge.ui.characters.CharactersViewModel
import com.intermedia.challenge.data.repositories.CharactersRepository
import com.intermedia.challenge.data.repositories.EventsRepository
import com.intermedia.challenge.ui.events.EventsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val businessModule = module {

    viewModel { CharactersViewModel(get()) }

    viewModel { EventsViewModel(get()) }

    single { CharactersRepository(get()) }

    single { EventsRepository(get()) }
}