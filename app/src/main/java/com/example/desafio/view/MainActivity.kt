package com.example.desafio.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimation()
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            finish()
        }, 1000)
    }

    private fun setAnimation() {
        binding.imageView.animation = AnimationUtils.loadAnimation(
            this@MainActivity,
            androidx.appcompat.R.anim.abc_popup_enter
        )
    }
}