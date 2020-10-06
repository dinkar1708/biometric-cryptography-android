## Requirements-
Cryptography key example.

Biometric Reusable module -
/src/main/java/cryptography/biometric/biometrickit

Authentication, Sign and Signature verification fragment-
/src/main/java/cryptography/biometric/ui/biometric/BiometricCryptographyPaymentFragment.kt

Dependency injection -
src/main/java/cryptography/biometric/di

Data source classes-
/src/main/java/cryptography/biometric/data/source
 
Design MVVM

Dependency Injection Design

------------ApplicationComponent - Singleton (CryptographyRepository)-------------------------------------------------
------------AndroidInjectionModule Module,  ViewModelFactoryModule Module, ApplicationModule Module , ActivityBuilderModule Module ---------------
------------------------------SplashActivitySubcomponent------------------------MainActivitySubcomponent---------------------------------
SplashActivity--------------------------------------    ---------------------MainActivity--------------------------------------------
----------------------------------------------------    ---HomeFragmentSubcomponent---------------BiometricCryptographyPaymentFragmentSubcomponent------------PaymentVerificationFragmentSubcomponent----------




## Technology used -
Android KTX, Architecture components help you design robust, testable and maintainable apps.Navigation
Handle everything needed for in-app navigation
ViewModel
Manage UI-related data in a lifecycle-conscious way
Data Binding
Decoratively bind observable data to UI elements
Write more concise, idiomatic Kotlin code
Room
Fluent SQLite database access
Lifecycle
Manage your activity and fragment lifecycle
