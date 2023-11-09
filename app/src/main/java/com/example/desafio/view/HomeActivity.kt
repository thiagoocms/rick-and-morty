package com.example.desafio.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio.databinding.ActivityHomeBinding
import com.example.desafio.listener.OnClickListener
import com.example.desafio.model.Person
import com.example.desafio.viewmodel.PersonViewModel

class HomeActivity : AppCompatActivity() {

    private var list: ArrayList<Person> = ArrayList()
    private var update = false
    private lateinit var adapter: Adapter
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val viewModel: PersonViewModel by lazy {
        ViewModelProvider(this).get(PersonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadItems()
        setContentView(binding.root)
        setPagination()
        observer()
    }

    private fun setAdapter() {
        adapter = Adapter(list)
        binding.rvHomeListPerson.adapter = adapter
        adapter.setOnItemClickListener(object : OnClickListener<Person> {
            override fun onClick(value: Person, position: Int) {
                var intent = Intent(this@HomeActivity, CardDetailActivity::class.java)
                intent.putExtra("person", value)
                startActivity(
                    intent,
                    ActivityOptions.makeSceneTransitionAnimation(this@HomeActivity).toBundle()
                )
            }
        })
        binding.rvHomeListPerson.layoutAnimation = LayoutAnimationController(
            AnimationUtils.loadAnimation(
                this@HomeActivity,
                com.google.android.material.R.anim.abc_fade_in
            )
        )
    }

    private fun setPagination() {
        binding.rvHomeListPerson.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    update = true
                    viewModel.addPage()
                    viewModel.loadItems()
                }
            }
        })
    }

    private fun observer() {
        viewModel.getList().observe(this) { it ->
            if (update) {
                adapter.addItems(it)
                update = false
            } else {
                list = it
                setAdapter()
            }
        }
    }
}