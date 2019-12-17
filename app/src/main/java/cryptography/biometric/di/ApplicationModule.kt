package cryptography.biometric.di

import android.content.Context
import androidx.room.Room
import cryptography.biometric.biometrickit.BiometricDialog
import cryptography.biometric.biometrickit.BiometricDialogInterface
import cryptography.biometric.biometrickit.cryptography.CryptographyProvider
import cryptography.biometric.biometrickit.cryptography.CryptographyTechnique
import cryptography.biometric.data.api.RetrofitAPIService
import cryptography.biometric.data.db.CryptoDatabase
import cryptography.biometric.data.source.CryptographyDafaultRepository
import cryptography.biometric.data.source.CryptographyDataSource
import cryptography.biometric.data.source.CryptographyRepository
import cryptography.biometric.data.source.remote.CryptographyRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * application module for dagger
 */
@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CryptographyRemoteDataSource

    //
    @JvmStatic
    @Singleton
    @CryptographyRemoteDataSource
    @Provides
    fun provideCryptographyRemoteDataSource(ni: RetrofitAPIService): CryptographyDataSource {
        return CryptographyRemoteDataSource(ni)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): CryptoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CryptoDatabase::class.java,
            "crypto-db.db"
        ).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitAPIService.getRetrofit()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): RetrofitAPIService {
        return retrofit.create(RetrofitAPIService::class.java)
    }
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: CryptographyDafaultRepository): CryptographyRepository

    @Singleton
    @Binds
    abstract fun bindBiometricDialog(biometricDialog: BiometricDialog): BiometricDialogInterface

    @Singleton
    @Binds
    abstract fun bindCryptographyTechnique(cryptographyTechnique: CryptographyTechnique): CryptographyProvider

}