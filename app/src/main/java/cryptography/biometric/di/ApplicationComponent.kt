package cryptography.biometric.di

import android.content.Context
import cryptography.biometric.CryptoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Application component for dagger, scoped application wide, until android application runs
 * Singleton - scope - single scope for application
 */
@Singleton
@Component(
    modules = [
        //  Contains bindings to ensure the usability of {@code dagger.android} framework classes. This
        // * module should be installed in the component that is used to inject the {@link
        AndroidInjectionModule::class,
        // Helps view model to create external dependency in constructor
        ViewModelFactoryModule::class,
        // modules available for application
        ApplicationModule::class,
        // Create sub component in application
        // ex - MainActivity and SplashActivity components
        ActivityBuilderModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<CryptoApplication> {

    /**
     * dagger component factory
     */
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}
