package cryptography.biometric.di.main.biometric

import cryptography.biometric.biometrickit.BiometricDialog
import cryptography.biometric.biometrickit.BiometricDialogInterface
import cryptography.biometric.biometrickit.cryptography.CryptographyProvider
import cryptography.biometric.biometrickit.cryptography.CryptographyTechnique
import cryptography.biometric.ui.main.biometric.BiometricScope
import dagger.Binds
import dagger.Module

@Module
abstract class BiometricModuleBinds {

    @BiometricScope
    @Binds
    abstract fun bindBiometricDialog(biometricDialog: BiometricDialog): BiometricDialogInterface

    @BiometricScope
    @Binds
    abstract fun bindCryptographyTechnique(cryptographyTechnique: CryptographyTechnique): CryptographyProvider

}
