package com.techlads.myapplication.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.techlads.myapplication.R
import com.techlads.myapplication.base.BaseActivity
import com.techlads.myapplication.data.GenericMedia
import com.techlads.myapplication.utils.setLayoutManager
import kotlinx.android.synthetic.main.activity_audio_songs.*
import kotlinx.coroutines.launch
import java.util.ArrayList

class AudioSongsActivity : BaseActivity(), GenericMediaAdapter.OnRecyclerItemClicked {

    var viewModel: GenericViewModel? = null
    val SONGS_URL: String = "audiosongs/"

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
        viewModel =  ViewModelProvider(this) [GenericViewModel::class.java]

        lifecycleScope.launch {
            loadMusic()
        }
    }

    private suspend fun loadMusic() {
        viewModel?.loadMovies(SONGS_URL)?.observe(this, Observer<ArrayList<GenericMedia>> {
            adapter?.update(it)
        })
    }

    private fun setupRv() {
        adapter = GenericMediaAdapter(R.layout.one_o_three_card_layout, this)
        audioSongsRv?.setLayoutManager()
        audioSongsRv?.adapter = adapter

        //adapter?.update(makeMediaList())
    }


    override fun onItemClicked(media: GenericMedia?, position: Int) {
        startActivity(PlayerActivity.start(this).putExtra("URL_STRING", media?.streamUrl))
    }
}