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

    // ContributesAndroidInjector - creates sub component for HomeFragment named HomeFragmentSubcomponent
    // HomeViewModelModule - sub component available for home fragment only because of HomeScope
    @HomeScope
    @ContributesAndroidInjector(modules = [HomeViewModelModule::class])
    internal abstract fun addHomeFragment(): HomeFragment
}
