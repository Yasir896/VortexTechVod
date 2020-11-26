package com.techlads.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.techlads.myapplication.base.BaseActivity
import com.techlads.myapplication.data.GenericMedia
import com.techlads.myapplication.ui.GenericMediaAdapter
import com.techlads.myapplication.ui.GenericViewModel
import com.techlads.myapplication.ui.PlayerActivity
import com.techlads.myapplication.ui.TalkShowsActivity
import com.techlads.myapplication.utils.setLayoutManager
import kotlinx.android.synthetic.main.activity_kids.*
import kotlinx.android.synthetic.main.activity_talk_shows.*
import kotlinx.android.synthetic.main.base_header.*
import kotlinx.coroutines.launch
import java.util.ArrayList

class KidsActivity : BaseActivity(), GenericMediaAdapter.OnRecyclerItemClicked, View.OnClickListener {

    var viewModel: GenericViewModel? = null
    val MOVIE_URL: String = "kids/"

    companion object {
        fun start(context: Context) : Intent {
            return Intent(context , KidsActivity::class.java)
        }
    }

    var adapter: GenericMediaAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_kids
    }

    override fun setEventListeners() {
        backBtn?.setOnClickListener(this)
    }

    override fun setup() {
        setTitle("Kids")
        setDescription("Animated")
        setupRv()

        viewModel = ViewModelProvider(this) [GenericViewModel::class.java]
        lifecycleScope.launch {
            loadViewModel()
        }

    }

    private fun setupRv() {
        adapter = GenericMediaAdapter(R.layout.one_o_three_card_layout, this)
        kidsMediaRv?.setLayoutManager()
        kidsMediaRv?.adapter = adapter

        //adapter?.update()
    }

    private suspend fun loadViewModel() {
        viewModel?.loadMovies(MOVIE_URL)?.observe(this, Observer<ArrayList<GenericMedia>> {
            adapter?.update(it)
        })
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