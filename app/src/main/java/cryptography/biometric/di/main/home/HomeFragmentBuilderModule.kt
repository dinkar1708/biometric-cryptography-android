package cryptography.biometric.di.main.home

import cryptography.biometric.ui.main.home.HomeFragment
import cryptography.biometric.ui.main.home.HomeScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Build module for home fragment
 */
@Module
abstract class HomeFragmentBuilderModule {

    // adding as sub module of HomeFragment, this view model can not be used other that HomeFragment,
    // if needed to use it has to include in that module
    // HomeViewModelModule - sub component available for home fragment only
    @HomeScope
    @ContributesAndroidInjector(modules = [HomeViewModelModule::class])
    internal abstract fun addHomeFragment(): HomeFragment
}
