package cryptography.biometric.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.coroutineScope
import cryptography.biometric.R
import cryptography.biometric.common.BaseActivity
import cryptography.biometric.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var splashModel: SplashModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycle.coroutineScope.launch {
            Timber.d("splashModel id $splashModel")
            delay(splashModel.timeMillis)
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}