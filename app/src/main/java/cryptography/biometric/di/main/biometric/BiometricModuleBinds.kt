package cryptography.biometric.di.main.biometric

import cryptography.biometric.biometrickit.BiometricDialog
import cryptography.biometric.biometrickit.cryptography.CryptographyTechnique
import cryptography.biometric.ui.main.MainScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class BiometricModuleBinds {

    @MainScope
    @Provides
    fun bindBiometricDialog(): BiometricDialog {
        return BiometricDialog()
    }

    @MainScope
    @Binds
    fun bindCryptographyTechnique(): CryptographyTechnique {
        return CryptographyTechnique()
    }

}
