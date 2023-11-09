package com.example.desafio.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio.databinding.ActivityCardDetailBinding
import com.example.desafio.model.Person
import com.squareup.picasso.Picasso
import org.joda.time.DateTime
import java.text.SimpleDateFormat

class CardDetailActivity : AppCompatActivity() {

    private val binding: ActivityCardDetailBinding by lazy {
        ActivityCardDetailBinding.inflate(layoutInflater)
    }
    private var person: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimation()
        person = intent.getSerializableExtra("person") as Person
        setPerson()
        onClick()
        setContentView(binding.root)
    }

    private fun onClick() {
        binding.arrowBack.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun setPerson() {
        val df = SimpleDateFormat("MM/dd/yyyy")
        Picasso.with(this@CardDetailActivity).load(person!!.image).into(binding.profileImage)
        binding.name.text = person!!.name
        binding.gender.text = "gender: " + person!!.gender.uppercase()
        binding.species.text = "species: " + person!!.species.uppercase()
        binding.status.text = "status: " + person!!.status.uppercase()
        binding.created.text = "created: " + df.format(DateTime(person!!.created).toDate())
    }

    private fun setAnimation() {
        binding.logo.animation = AnimationUtils.loadAnimation(
            this@CardDetailActivity,
            com.google.android.material.R.anim.abc_slide_in_top
        )
        binding.arrowBack.animation = AnimationUtils.loadAnimation(
            this@CardDetailActivity,
            com.google.android.material.R.anim.abc_slide_in_top
        )
        binding.profileImage.animation = AnimationUtils.loadAnimation(
            this@CardDetailActivity,
            com.google.android.material.R.anim.abc_slide_in_bottom
        )
        binding.box.animation = AnimationUtils.loadAnimation(
            this@CardDetailActivity,
            com.google.android.material.R.anim.abc_slide_in_bottom
        )
        binding.name.animation = AnimationUtils.loadAnimation(
            this@CardDetailActivity,
            com.google.android.material.R.anim.abc_slide_in_bottom
        )
        binding.gender.animation = AnimationUtils.loadAnimation(
            this@CardDetailActivity,
            com.google.android.material.R.anim.abc_slide_in_bottom
        )
        binding.species.animation = AnimationUtils.loadAnimation(
            this@CardDetailActivity,
            com.google.android.material.R.anim.abc_slide_in_bottom
        )
        binding.status.animation = AnimationUtils.loadAnimation(
            this@CardDetailActivity,
            com.google.android.material.R.anim.abc_slide_in_bottom
        )
        binding.created.animation = AnimationUtils.loadAnimation(
            this@CardDetailActivity,
            com.google.android.material.R.anim.abc_slide_in_bottom
        )
    }
}