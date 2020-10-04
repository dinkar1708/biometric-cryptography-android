package cryptography.biometric.di;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/*
 key for view models - mapping to classes with key
     @CryptoViewModelFactory
     private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
     key - Class<out ViewModel>
     value - view models Provider<ViewModel>

 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}


