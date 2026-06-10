package cryptography.biometric.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
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

        // Apply animations to views
        applyAnimations()

        lifecycle.coroutineScope.launch {
            Timber.d("splashModel id $splashModel")
            delay(splashModel.timeMillis)
            navigateToMain()
        }
    }

    private fun applyAnimations() {
        val logo = findViewById<ImageView>(R.id.ivLogo)
        val appName = findViewById<TextView>(R.id.tvAppName)
        val tagline = findViewById<TextView>(R.id.tvTagline)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val footer = findViewById<TextView>(R.id.tvFooter)

        // Apply scale bounce animation to logo
        val scaleBounce = AnimationUtils.loadAnimation(this, R.anim.scale_bounce)
        logo.startAnimation(scaleBounce)

        // Apply slide up animation to app name
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        slideUp.startOffset = 200
        appName.startAnimation(slideUp)

        // Apply fade in to tagline
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeIn.startOffset = 400
        tagline.startAnimation(fadeIn)

        // Apply fade in to progress bar
        val fadeIn2 = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeIn2.startOffset = 600
        progressBar.startAnimation(fadeIn2)

        // Apply fade in to footer
        val fadeIn3 = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeIn3.startOffset = 800
        footer.startAnimation(fadeIn3)
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}