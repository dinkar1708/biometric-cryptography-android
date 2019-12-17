package cryptography.biometric

import cryptography.biometric.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

/**
 * Application class - initialize the values only once when application is getting started for first
 * time
 */
open class CryptoApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        // initialize the logger
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        Timber.d("CryptoApplication.......onCreate........")
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }
}