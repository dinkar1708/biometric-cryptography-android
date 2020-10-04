package cryptography.biometric.di.splash

import cryptography.biometric.ui.splash.SplashModel
import cryptography.biometric.ui.splash.SplashScope
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

// if provide invalid scope will get error, thats how correct scope works
//    @VerificationScope
//    /biometric-cryptography-android/app/build/tmp/kapt3/stubs/debug/cryptography/biometric/di/ApplicationComponent.java:12: error: [Dagger/IncompatiblyScopedBindings] cryptography.biometric.di.ActivityBuilderModule_BindSplashActivity$app_debug.SplashActivitySubcomponent scoped with @cryptography.biometric.ui.splash.SplashScope may not reference bindings with different scopes:

    @SplashScope
    @Provides
    fun provideSplashTime(): SplashModel {
        return SplashModel(1 * 1000)
    }
}