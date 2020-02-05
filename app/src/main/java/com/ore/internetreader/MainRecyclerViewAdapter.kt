package com.ore.internetreader

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.internet_item.view.*

class MainRecyclerViewAdapter(val feed: AppleFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    val titles = listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)

    override fun getItemCount(): Int {
        return feed.feed.results.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowItem = layoutInflater.inflate(R.layout.internet_item, parent, false)
        return CustomViewHolder(rowItem)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val result = feed.feed.results.get(position)
        holder.view.internet_book_title.text = result.name
        holder.view.internet_artist_name.text = "by ${result.artistName}"
        holder.view.internet_genre_name.text = "in ${result.genres[0].name}"
        holder.view.internet_release_date.text = "released ${result.releaseDate}"
        holder.view.internet_book_number.text = "${position + 1}"
        holder.view.internet_button_buy.setOnClickListener {
            val url = result.url
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(holder.itemView.context, i, null)
        }
        holder.view.internet_button_view.setOnClickListener {
            val url = result.url
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(holder.itemView.context, i, null)
        }
        val bookThumbnail = holder.view.internet_image
        Picasso.get().load(result.artworkUrl100).placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background).into(bookThumbnail)
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}