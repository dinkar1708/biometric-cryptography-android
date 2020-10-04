package cryptography.biometric.di.main.home

import androidx.lifecycle.ViewModel
import cryptography.biometric.di.ViewModelKey
import cryptography.biometric.ui.main.biometric.BiometricCryptographyPaymentViewModel
import cryptography.biometric.ui.main.home.HomeFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Dagger module for home fragments
 */
@Module
abstract class HomeViewModelModule {

    // creating view model key entry
    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    internal abstract fun bindHomeFragmentViewModel(viewModel: HomeFragmentViewModel): ViewModel

    //    // biometric fragment module
    @Binds
    @IntoMap
    @ViewModelKey(BiometricCryptographyPaymentViewModel::class)
    internal abstract fun bindBiometricCryptographyPaymentViewModel(viewModel: BiometricCryptographyPaymentViewModel): ViewModel
}
