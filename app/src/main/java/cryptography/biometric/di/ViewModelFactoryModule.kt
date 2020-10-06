package cryptography.biometric.di

import androidx.lifecycle.ViewModelProvider
import cryptography.biometric.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

/**
 * Provides the object for ViewModelProviderFactory
 */
@Module
abstract class ViewModelFactoryModule {
    /**
     * Binds also create object similar to Provide only difference is when we dont have method body use Binds
     * Binds create object of ViewModelProviderFactory
     */
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}