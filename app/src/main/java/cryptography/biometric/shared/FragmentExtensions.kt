package cryptography.biometric.shared

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerFragment

/**
 * Base class for all fragments
 * Also contains the extension functions for fragments
 */
abstract class BaseFragment : DaggerFragment()

/**
 * Set the tittle bar for fragment
 * Enable the back button
 */
fun Fragment.setTitle(title: Int, isEnableBackButton: Boolean = true) {
    (activity as AppCompatActivity).supportActionBar?.let {
        it.setTitle(title)
        it.setDisplayHomeAsUpEnabled(isEnableBackButton)
    }
}

fun Fragment.showToast(text: String) {
    activity?.let {
        Toast.makeText(it, text, Toast.LENGTH_LONG).show()
    }
}

fun Fragment.showToastOnUi(text: String) {
    activity?.let {
        it.runOnUiThread {
            Toast.makeText(it, text, Toast.LENGTH_SHORT).show()
        }
    }
}