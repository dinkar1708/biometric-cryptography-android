package cryptography.biometric.di.main.payment

import cryptography.biometric.ui.main.verification.PaymentVerificationFragment
import cryptography.biometric.ui.main.verification.VerificationScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Build module for home fragment
 */
@Module
abstract class PaymentFragmentBuilderModule {

    @VerificationScope
    // without any module
    @ContributesAndroidInjector()
    internal abstract fun addPaymentVerificationFragment(): PaymentVerificationFragment

}
