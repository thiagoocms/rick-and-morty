package com.example.desafio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.config.RetrofitConfig
import com.example.desafio.model.Person
import com.example.desafio.model.Responce
import com.example.desafio.service.PersonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonViewModel : ViewModel() {

    private var list = MutableLiveData<ArrayList<Person>>()
    private var page: Int = 1
    private val personService by lazy {
        RetrofitConfig.instance.create(PersonService::class.java)
    }

    fun getList(): LiveData<ArrayList<Person>> {
        return list
    }

    fun loadItems() {
        val call = personService.getPersons(page)
        call.enqueue(object : Callback<Responce> {
            override fun onResponse(call: Call<Responce>, response: Response<Responce>) {
                list.value = response.body()!!.results
            }

            override fun onFailure(call: Call<Responce>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun addPage() {
        page++
    }
}