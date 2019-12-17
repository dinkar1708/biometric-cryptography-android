package cryptography.biometric.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cryptography.biometric.data.DataResult
import cryptography.biometric.data.api.ApiState
import cryptography.biometric.data.source.CryptographyRepository
import cryptography.biometric.ui.home.data.GetUserTokenRequest
import cryptography.biometric.ui.home.data.GetUserTokenResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(private val repository: CryptographyRepository) :
    ViewModel() {

    private val _resultLiveData =
        MutableLiveData<ApiState<GetUserTokenResponse>>()

    fun getResultLiveDataState(): MutableLiveData<ApiState<GetUserTokenResponse>> =
        _resultLiveData

    /**
     * get user token from server for starting the transaction
     */
    fun getUserToken(request: GetUserTokenRequest) {
        // show progress
        _resultLiveData.postValue(
            ApiState(
                loading = true
            )
        )
        // calling from view model scope
        viewModelScope.launch {
            // suspend function call
            val result = repository.getUserToken(
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
                // this should be actual code
                _resultLiveData.postValue(
                    ApiState(
                        error = result.toString()
                    )
                )
            }
        }
    }
}