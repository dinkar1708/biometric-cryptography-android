package cryptography.biometric.di

import cryptography.biometric.MainActivity
import cryptography.biometric.di.home.HomeFragmentBuilderModule
import cryptography.biometric.di.home.HomeScope
import cryptography.biometric.di.home.HomeViewModelModule
import cryptography.biometric.ui.splash.SplashActivity
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

    @HomeScope
    // Specified modules should be available only in main activity scope
    @ContributesAndroidInjector(modules = [HomeFragmentBuilderModule::class, HomeViewModelModule::class])
    // sub component of system
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector()// define module here
    // sub component of system
    internal abstract fun bindSplashActivity(): SplashActivity
}