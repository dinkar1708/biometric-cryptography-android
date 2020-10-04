package cryptography.biometric.ui.main.biometric.data

import cryptography.biometric.data.api.ApiBaseResponse


data class VerifySignatureResponse(
    var response: String

) : ApiBaseResponse("", false)
