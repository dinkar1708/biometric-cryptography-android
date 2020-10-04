package cryptography.biometric.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.coroutineScope
import cryptography.biometric.MainActivity
import cryptography.biometric.R
import cryptography.biometric.common.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycle.coroutineScope.launch {
            delay(3 * 1000)
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}