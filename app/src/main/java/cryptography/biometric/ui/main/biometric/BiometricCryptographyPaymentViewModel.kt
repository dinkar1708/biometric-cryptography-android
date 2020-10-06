package cryptography.biometric.ui.main.biometric

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cryptography.biometric.data.DataResult
import cryptography.biometric.data.api.ApiState
import cryptography.biometric.data.source.CryptographyRepository
import cryptography.biometric.ui.main.biometric.data.VerifySignatureRequest
import cryptography.biometric.ui.main.biometric.data.VerifySignatureResponse
import kotlinx.coroutines.launch
import java.security.PublicKey
import javax.inject.Inject

class BiometricCryptographyPaymentViewModel @Inject constructor(private val repository: CryptographyRepository) :
    ViewModel() {

    private val _resultLiveData =
        MutableLiveData<ApiState<VerifySignatureResponse>>()

    fun getResultLiveDataState(): MutableLiveData<ApiState<VerifySignatureResponse>> =
        _resultLiveData

    /**
     * store public key
     */
    fun storePublicKey(request: PublicKey) {
        viewModelScope.launch {
            repository.sendPublicKey(request)
        }
    }

    /**
     * verify the message
     */
    fun verifyMessage(request: VerifySignatureRequest) {
        // show progress
        _resultLiveData.postValue(
            ApiState(
                loading = true
            )
        )
        // calling from view model scope
        viewModelScope.launch {
            // suspend function call
            val result = repository.verifySignature(
                request
            )
            // check api result and update the live data
            if (result is DataResult.Success) {
                // api parsed successfully
                _resultLiveData.postValue(
                    if (result.data.status) {
                        ApiState(
                            successObject = result.data
                        )
                    } else {
                        ApiState(
                            error = result.data.description
                        )
                    }
                )
            } else {

                _resultLiveData.postValue(
                    ApiState(
                        error = result.toString()
                    )
                )
            }
        }
    }
}