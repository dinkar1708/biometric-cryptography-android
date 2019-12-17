package cryptography.biometric.di

import androidx.lifecycle.ViewModel
import cryptography.biometric.ui.biometric.BiometricCryptographyPaymentFragment
import cryptography.biometric.ui.biometric.BiometricCryptographyPaymentViewModel
import cryptography.biometric.ui.home.HomeFragment
import cryptography.biometric.ui.home.HomeFragmentViewModel
import cryptography.biometric.ui.payment_verification.PaymentVerificationFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Dagger module for fragments
 */
@Module
abstract class FragmentModule {

    // home fragment
    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    internal abstract fun bindHomeFragmentViewModel(viewModel: HomeFragmentViewModel): ViewModel

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun addHomeFragment(): HomeFragment


    // biometric fragment module
    @Binds
    @IntoMap
    @ViewModelKey(BiometricCryptographyPaymentViewModel::class)
    internal abstract fun bindBiometricCryptographyPaymentViewModel(viewModel: BiometricCryptographyPaymentViewModel): ViewModel

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun addBiometricCryptographyPayment(): BiometricCryptographyPaymentFragment


    // payment verification module
    // home fragment
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun addPaymentVerificationFragment(): PaymentVerificationFragment


}
