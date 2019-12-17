package cryptography.biometric.ui.biometric.data

import cryptography.biometric.data.api.ApiBaseResponse


data class VerifySignatureResponse(
    var response: String

) : ApiBaseResponse("", false)
