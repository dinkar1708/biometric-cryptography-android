package cryptography.biometric.ui.main

import android.os.Bundle
import android.view.MenuItem
import cryptography.biometric.R
import cryptography.biometric.common.BaseActivity

/**
 * Single activity based application
 */
class MainActivity : BaseActivity() {
    // below produce error because SplashModel is not scoped only for splash module
    // biometric-cryptography-android/app/build/tmp/kapt3/stubs/debug/cryptography/biometric/di/ApplicationComponent.java:12: error: [Dagger/MissingBinding] cryptography.biometric.ui.splash.SplashModel cannot be provided without an @Inject constructor or an @Provides-annotated method.
    /*
    @Inject
    lateinit var splashModel: SplashModel
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
