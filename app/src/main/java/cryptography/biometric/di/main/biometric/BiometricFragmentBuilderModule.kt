package cryptography.biometric.di.main.biometric

import cryptography.biometric.ui.main.biometric.BiometricCryptographyPaymentFragment
import cryptography.biometric.ui.main.biometric.BiometricScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Build module for home fragment
 */
@Module
abstract class BiometricFragmentBuilderModule {

    @BiometricScope
    @ContributesAndroidInjector(modules = [BiometricModule::class, BiometricViewModelModule::class])
    internal abstract fun addBiometricCryptographyPayment(): BiometricCryptographyPaymentFragment

}
