package cryptography.biometric.di.main.home

import cryptography.biometric.di.main.biometric.BiometricModuleBinds
import cryptography.biometric.ui.main.biometric.BiometricCryptographyPaymentFragment
import cryptography.biometric.ui.main.biometric.BiometricScope
import cryptography.biometric.ui.main.home.HomeFragment
import cryptography.biometric.ui.main.home.HomeScope
import cryptography.biometric.ui.main.verification.PaymentVerificationFragment
import cryptography.biometric.ui.main.verification.VerificationScope
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
//    @HomeScope
    @ContributesAndroidInjector()
    internal abstract fun addHomeFragment(): HomeFragment

//    @BiometricScope
    @ContributesAndroidInjector()
    internal abstract fun addBiometricCryptographyPayment(): BiometricCryptographyPaymentFragment

//    @VerificationScope
    @ContributesAndroidInjector()
    internal abstract fun addPaymentVerificationFragment(): PaymentVerificationFragment

}
