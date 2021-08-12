package com.yurry.stockbitmini.di

import com.yurry.stockbitmini.ui.chat.ChatViewModel
import com.yurry.stockbitmini.ui.login.LoginViewModel
import com.yurry.stockbitmini.ui.logout.LogoutViewModel
import com.yurry.stockbitmini.ui.main.MainViewModel
import com.yurry.stockbitmini.ui.portofolio.PortfolioViewModel
import com.yurry.stockbitmini.ui.search.SearchViewModel
import com.yurry.stockbitmini.ui.splash.SplashViewModel
import com.yurry.stockbitmini.ui.stream.StreamViewModel
import com.yurry.stockbitmini.ui.watchlist.WatchlistViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val streamViewModelModule = module {
    viewModel { StreamViewModel() }
}

private val searchViewModelModule = module {
    viewModel { SearchViewModel() }
}

private val chatViewModelModule = module {
    viewModel { ChatViewModel() }
}

private val portfolioViewModelModule = module {
    viewModel { PortfolioViewModel() }
}

private val watchlistViewModelModule = module {
    viewModel { WatchlistViewModel(get()) }
}

private val loginViewModelModule = module {
    viewModel { LoginViewModel(get()) }
}

private val splashViewModelModule = module {
    viewModel { SplashViewModel(get()) }
}

private val profileViewModelModule = module {
    viewModel { MainViewModel(get()) }
}

private val logoutViewModelModule = module {
    viewModel { LogoutViewModel(get()) }
}

val viewModelModuleList = mutableListOf(
    streamViewModelModule,
    searchViewModelModule,
    chatViewModelModule,
    portfolioViewModelModule,
    watchlistViewModelModule,
    loginViewModelModule,
    splashViewModelModule,
    profileViewModelModule,
    logoutViewModelModule
)