package com.example.desafio.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio.databinding.CardBinding
import com.example.desafio.listener.OnClickListener
import com.example.desafio.model.Person
import com.squareup.picasso.Picasso

class Adapter(private val data: ArrayList<Person>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var onClickListener: OnClickListener<Person>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: CardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Person) {
            binding.image.setImageResource(0)
            Picasso.with(binding.root.context).load(item.image).into(binding.image)
            binding.name.text = item.name
            binding.species.text = item.species.lowercase()
            binding.card.setOnClickListener {
                onClickListener!!.onClick(item, adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(onClickListener: OnClickListener<Person>) {
        this.onClickListener = onClickListener
    }

    fun addItems(newItems: ArrayList<Person>) {
        val sizeCurrent = data.size
        data.addAll(newItems)
        notifyItemRangeInserted(sizeCurrent, newItems.size)
    }
}