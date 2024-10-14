package com.example.marvelheroes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelheroes.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the animation
        val animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim)

        // Start animation using binding
        binding.marvelLogo.startAnimation(animation)

        // Delay for a few seconds, then move to MainActivity
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3 seconds delay
        supportActionBar?.hide()
    }
}
