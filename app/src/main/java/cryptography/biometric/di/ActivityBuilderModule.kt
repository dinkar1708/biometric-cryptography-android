package cryptography.biometric.di

import cryptography.biometric.di.main.biometric.BiometricFragmentBuilderModule
import cryptography.biometric.di.main.home.HomeFragmentBuilderModule
import cryptography.biometric.di.main.payment.PaymentFragmentBuilderModule
import cryptography.biometric.di.splash.SplashModule
import cryptography.biometric.ui.main.MainActivity
import cryptography.biometric.ui.main.MainScope
import cryptography.biometric.ui.splash.SplashActivity
import cryptography.biometric.ui.splash.SplashScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * sub component
 * can se in generated code
 * kapt
debug
databinding
biometric
biometrickit
data
databinding
di
home
ActivityBuilderModule_BindMainActivity$app_debug

@Subcomponent
public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
@Subcomponent.Factory
interface Factory extends AndroidInjector.Factory<MainActivity> {}
}

 */
@Module
abstract class ActivityBuilderModule {

    @MainScope
    // Specified modules should be available only in main activity scope
    @ContributesAndroidInjector(
        modules = [HomeFragmentBuilderModule::class,
            BiometricFragmentBuilderModule::class,
            PaymentFragmentBuilderModule::class]
    )
    // sub component of system
    internal abstract fun bindMainActivity(): MainActivity

    // SplashModule module should be available in SplashScope
    @SplashScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    // sub component of system
    internal abstract fun bindSplashActivity(): SplashActivity
}