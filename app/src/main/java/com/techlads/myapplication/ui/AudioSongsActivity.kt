package com.techlads.myapplication.ui

import android.content.Context
import android.content.Intent
import com.techlads.myapplication.R
import com.techlads.myapplication.base.BaseActivity
import com.techlads.myapplication.data.GenericMedia
import com.techlads.myapplication.utils.setLayoutManager
import kotlinx.android.synthetic.main.activity_audio_songs.*
import java.util.ArrayList

class AudioSongsActivity : BaseActivity(), GenericMediaAdapter.OnRecyclerItemClicked {

    companion object {
        fun start(context: Context) : Intent {
            return Intent(context , AudioSongsActivity::class.java)
        }
    }

    var adapter: GenericMediaAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_audio_songs
    }

    override fun setEventListeners() { }

    override fun setup() {
        setTitle("New & Old")
        setDescription("Audio Songs")
        setupRv()
    }

    private fun setupRv() {
        adapter = GenericMediaAdapter(R.layout.one_o_three_card_layout, this)
        audioSongsRv?.setLayoutManager()
        audioSongsRv?.adapter = adapter

        adapter?.update(makeMediaList())
    }

    private fun makeMediaList(): ArrayList<GenericMedia> {
        val list = arrayListOf<GenericMedia>()

        for (i in 0 until 20) {
            list.add(GenericMedia(title = "Movie - $i", imageUrl = getUrl(i), streamUrl = "" ))
        }

        return list
    }

    private fun getUrl(i: Int): String {

        return if (i % 2 == 0)
//            "https://m.media-amazon.com/images/M/MV5BOTk5ODg0OTU5M15BMl5BanBnXkFtZTgwMDQ3MDY3NjM@._V1_QL50_SY1000_CR0,0,674,1000_AL_.jpg"
            "https://m.media-amazon.com/images/M/MV5BOTk5ODg0OTU5M15BMl5BanBnXkFtZTgwMDQ3MDY3NjM@._V1_UY209_CR0,0,140,209_AL_.jpg"
        else
//            "https://m.media-amazon.com/images/M/MV5BMjAwODg3OTAxMl5BMl5BanBnXkFtZTcwMjg2NjYyMw@@.jpg"
            "https://m.media-amazon.com/images/M/MV5BMjAwODg3OTAxMl5BMl5BanBnXkFtZTcwMjg2NjYyMw@@._V1_UX182_CR0,0,182,268_AL_.jpg"
    }

    override fun onItemClicked(media: GenericMedia?, position: Int) {
        //do nothing
    }
}