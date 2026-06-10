# Android Cryptography Biometric Module

This Android project showcases a comprehensive example of integrating cryptography, biometrics, and the MVVM architecture.

# Screens

<img width="300" height="600" alt="Screenshot_1781066563" src="https://github.com/user-attachments/assets/fd9484a6-5277-4d33-867c-430d6bb51537" />

<img width="300" height="700" alt="Screenshot_1781066564" src="https://github.com/user-attachments/assets/e47d393a-bbef-4887-a754-4107d00e3cdd" />
<img width="300" height="700" alt="Screenshot_1781066581" src="https://github.com/user-attachments/assets/d7897d78-2505-4bc9-abc0-961c5d9351f8" />

## Features

### Cryptography Key Example

Explore the cryptography key example in the following package:

```
/src/main/java/cryptography/biometric/biometrickit
```

### Biometric Reusable Module

A reusable biometric module is located at:

```
/src/main/java/cryptography/biometric/biometrickit
```

### Authentication, Sign, and Signature Verification Fragment

The fragment handling authentication, sign, and signature verification is available at:

```
/src/main/java/cryptography/biometric/ui/biometric/BiometricCryptographyPaymentFragment.kt
```

## Dependency Injection

Dependency injection is handled in the following package:

```
/src/main/java/cryptography/biometric/di
```

## Data Source Classes

Data source classes are implemented in:

```
/src/main/java/cryptography/biometric/data/source
```

## Design Patterns

### MVVM Architecture

The project adheres to the MVVM (Model-View-ViewModel) architectural pattern, providing a clear separation of concerns and promoting maintainability.

### Dependency Injection Design

The application utilizes Dagger 2 for dependency injection. Key components include:

- **ApplicationComponent:** Singleton for `CryptographyRepository`.
- **AndroidInjectionModule Module:** Part of Dagger Android for DI support.
- **ViewModelFactoryModule Module:** Module for ViewModelFactory.
- **ApplicationModule Module:** Module for application-wide dependencies.
- **ActivityBuilderModule Module:** Module for activity-level dependencies.

Subcomponents for specific features include:

- **SplashActivitySubcomponent**
- **MainActivitySubcomponent**
- **HomeFragmentSubcomponent**
- **BiometricCryptographyPaymentFragmentSubcomponent**
- **PaymentVerificationFragmentSubcomponent**

## Technology Used

- **Android KTX:** Kotlin extensions for Android development.
- **Architecture Components:** Robust, testable, and maintainable app architecture.
    - **Navigation:** Handle in-app navigation.
    - **ViewModel:** Manage UI-related data in a lifecycle-conscious way.
    - **Data Binding:** Decoratively bind observable data to UI elements.
    - **Room:** Fluent SQLite database access.
    - **Lifecycle:** Manage activity and fragment lifecycle.

## Getting Started

To get started with this project, clone the repository and open it in Android Studio. 
Explore the various components, follow the package structure,
and leverage the implemented design patterns in your own projects.
