package com.ore.internetreader

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ore.internetreader.databinding.InternetItemBinding
import com.squareup.picasso.Picasso

class MainRecyclerViewAdapter(private val feed: AppleFeed, private var context: Context) :
    RecyclerView.Adapter<CustomViewHolder>() {

    private lateinit var binding: InternetItemBinding

    override fun getItemCount(): Int {
        return feed.feed.results.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        binding = InternetItemBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val result = feed.feed.results[position]
        holder.bind(result)
        binding.internetGenreName.text = "in ${result.genres[0].name}"

        binding.internetButtonBuy.setOnClickListener {
            val url = result.url
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(holder.itemView.context, i, null)
        }

        binding.internetButtonView.setOnClickListener {
            val url = result.url
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(holder.itemView.context, i, null)
        }

        val bookThumbnail = binding.internetImage
        Picasso.get().load(result.artworkUrl100).placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background).into(bookThumbnail)
    }
}

class CustomViewHolder(private val binding: InternetItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Result) {
        binding.result = item
    }
}