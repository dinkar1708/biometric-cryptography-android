package cryptography.biometric.di

import android.content.Context
import cryptography.biometric.CryptoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Application component for dagger
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        FragmentModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CryptoApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}
