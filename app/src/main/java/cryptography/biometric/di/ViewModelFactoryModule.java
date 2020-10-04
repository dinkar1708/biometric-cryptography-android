package cryptography.biometric.di;

import androidx.lifecycle.ViewModelProvider;

import cryptography.biometric.viewmodels.ViewModelProviderFactory;
import dagger.Binds;
import dagger.Module;


@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);
}
