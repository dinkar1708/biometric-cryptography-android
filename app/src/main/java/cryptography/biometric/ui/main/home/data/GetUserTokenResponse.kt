package cryptography.biometric.ui.main.home.data

import cryptography.biometric.data.api.ApiBaseResponse


data class GetUserTokenResponse(
    val token: String,
    val cardLast4Digit: String

) : ApiBaseResponse("", false)
