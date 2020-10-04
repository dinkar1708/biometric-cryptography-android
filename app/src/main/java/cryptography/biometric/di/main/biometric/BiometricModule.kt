package cryptography.biometric.di.main.biometric

import cryptography.biometric.biometrickit.BiometricDialog
import cryptography.biometric.biometrickit.cryptography.CryptographyTechnique
import cryptography.biometric.ui.main.biometric.BiometricScope
import dagger.Module
import dagger.Provides

@Module
class BiometricModule {

    @BiometricScope
    @Provides
    fun bindBiometricDialog(): BiometricDialog {
        return BiometricDialog()
    }

    @BiometricScope
    @Provides
    fun bindCryptographyTechnique(): CryptographyTechnique {
        return CryptographyTechnique()
    }

}
