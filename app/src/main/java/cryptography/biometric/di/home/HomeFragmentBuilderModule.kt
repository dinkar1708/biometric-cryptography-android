package cryptography.biometric.di.home

import cryptography.biometric.ui.biometric.BiometricCryptographyPaymentFragment
import cryptography.biometric.ui.home.HomeFragment
import cryptography.biometric.ui.verification.PaymentVerificationFragment
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
    @ContributesAndroidInjector()
//    @ContributesAndroidInjector(modules = [ HomeViewModelModule::class])
    internal abstract fun addHomeFragment(): HomeFragment

    @ContributesAndroidInjector()
    internal abstract fun addBiometricCryptographyPayment(): BiometricCryptographyPaymentFragment

    @ContributesAndroidInjector()
    internal abstract fun addPaymentVerificationFragment(): PaymentVerificationFragment

}
