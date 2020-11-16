package com.techlads.myapplication.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.techlads.myapplication.R
import com.techlads.myapplication.base.BaseActivity
import com.techlads.myapplication.data.GenericMedia
import com.techlads.myapplication.utils.setLayoutManager
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.android.synthetic.main.base_header.*
import kotlinx.coroutines.launch
import java.util.ArrayList

class MoviesActivity : BaseActivity(), GenericMediaAdapter.OnRecyclerItemClicked, View.OnClickListener{

    var viewModel: GenericViewModel? = null
    val MOVIE_URL: String = "movies/"

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
        backBtn?.setOnClickListener(this)
    }

    override fun setup() {
        setTitle("Box Office")
        setDescription("Hit Movies")
        setupRv()
        viewModel = ViewModelProvider(this) [GenericViewModel::class.java]

//        coroutineScope {  }

        lifecycleScope.launch {
            loadViewModel()
        }
    }

    private suspend fun loadViewModel() {
        viewModel?.loadMovies(MOVIE_URL)?.observe(this, Observer<ArrayList<GenericMedia>> {
            adapter?.update(it)
        })
    }

    private fun setupRv() {
        adapter = GenericMediaAdapter(R.layout.one_o_three_card_layout, this)
        moviesRv?.setLayoutManager()
        moviesRv?.adapter = adapter
    }

    override fun onItemClicked(media: GenericMedia?, position: Int) {
        startActivity(PlayerActivity.start(this).putExtra("URL_STRING", media?.streamUrl))
    }

    override fun onClick(view: View?) {
        if (view == backBtn){
            finish()
        }
    }

}