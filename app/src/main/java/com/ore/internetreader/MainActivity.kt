package com.ore.internetreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        actionBar?.title = "iTunes Store Top 100 Books Nigeria"
        main_recycler_view.layoutManager = LinearLayoutManager(this)

        fetchJSON()
    }

    fun fetchJSON() {
        val url = "https://rss.itunes.apple.com/api/v1/ng/books/top-free/all/100/explicit.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val feed = gson.fromJson(body, AppleFeed::class.java)

                runOnUiThread {
                    main_recycler_view.adapter = MainRecyclerViewAdapter(feed, this@MainActivity)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to Execute ${e.printStackTrace()}")
            }
        })
    }

}