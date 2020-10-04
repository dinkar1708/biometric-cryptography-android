package cryptography.biometric.ui.main

import android.os.Bundle
import android.view.MenuItem
import cryptography.biometric.R
import cryptography.biometric.common.BaseActivity
import cryptography.biometric.ui.splash.SplashModel
import timber.log.Timber
import javax.inject.Inject

/**
 * Single activity based application
 */
class MainActivity : BaseActivity() {
//    @Inject
//    lateinit var splashModel: SplashModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Timber.d("splashModel id---  $splashModel")

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
