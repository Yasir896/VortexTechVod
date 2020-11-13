package com.techlads.myapplication.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.techlads.myapplication.R
import com.techlads.myapplication.base.BaseActivity
import com.techlads.myapplication.data.GenericMedia
import com.techlads.myapplication.utils.setLayoutManager
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class MoviesActivity : BaseActivity(), GenericMediaAdapter.OnRecyclerItemClicked {

    var viewModel: MoviesViewModel? = null

    companion object {
        fun start(context: Context) : Intent {
            return Intent(context , MoviesActivity::class.java)
        }
    }

    var adapter: GenericMediaAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_movies
    }

    override fun setEventListeners() {

    }

    override fun setup() {
        setTitle("Box Office")
        setDescription("Hit Movies")
        setupRv()
        viewModel = ViewModelProvider(this) [MoviesViewModel::class.java]

//        coroutineScope {  }

        lifecycleScope.launch {
            loadViewModel()
        }
    }

    private suspend fun loadViewModel() {
        viewModel?.loadMovies()?.observe(this, Observer<ArrayList<GenericMedia>> {
            Log.e("In Observer ", it.toString())
            adapter?.update(it)
        })
    }

    private fun setupRv() {
        adapter = GenericMediaAdapter(R.layout.one_o_three_card_layout, this)
        moviesRv?.setLayoutManager()
        moviesRv?.adapter = adapter
        //adapter?.update(movies)

    }

    private fun makeMediaList(): ArrayList<GenericMedia> {
        val list = arrayListOf<GenericMedia>()

        for (i in 0 until 20) {
            list.add(GenericMedia(title = "Movie - $i", imageUrl = getUrl(i), streamUrl = "http://ad.vortextech.org/movies/Spies.In.Disguise.mp4" ))
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
        startActivity(PlayerActivity.start(this).putExtra("URL_STRING", media?.streamUrl))
    }

}