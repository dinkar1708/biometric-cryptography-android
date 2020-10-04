package cryptography.biometric.di.main.biometric

import androidx.lifecycle.ViewModel
import cryptography.biometric.di.ViewModelKey
import cryptography.biometric.ui.main.biometric.BiometricCryptographyPaymentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Dagger module for biometric
 */
@Module
abstract class BiometricViewModelModule {

    //    // biometric fragment module
    @Binds
    @IntoMap
    @ViewModelKey(BiometricCryptographyPaymentViewModel::class)
    internal abstract fun bindBiometricCryptographyPaymentViewModel(viewModel: BiometricCryptographyPaymentViewModel): ViewModel
}
