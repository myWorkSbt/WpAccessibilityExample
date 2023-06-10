package com.example.wpaccessibilityexample.Activity

import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.wpaccessibilityexample.R
import com.example.wpaccessibilityexample.Utils.WpAccessibility

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_home)


        setSkyBlueStatusBar()


        if (!isEnabled()) {
            Log.d("======>", "onCreate:  called ")
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        } else {

            val serviceIntent = Intent(this, WpAccessibility::class.java)
            startService(serviceIntent)
        }


    }


    private fun isEnabled(): Boolean {
        val componentName = ComponentName(this, WpAccessibility::class.java)
        val accessibilitySettings = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        )
        return accessibilitySettings != null && accessibilitySettings.contains(componentName.flattenToString())
    }

    private fun setSkyBlueStatusBar() {
        val view: View = this@HomeActivity.getWindow().getDecorView()
        var flags = view.systemUiVisibility
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        view.systemUiVisibility = flags
        this@HomeActivity.getWindow().setStatusBarColor(Color.parseColor("#9A8ABEC5"))
    }
}