package com.techlads.myapplication.ui

import android.content.Context
import android.content.Intent
import android.view.View
import com.techlads.myapplication.R
import com.techlads.myapplication.base.BaseActivity
import com.techlads.myapplication.data.GenericMedia
import com.techlads.myapplication.utils.setLayoutManager
import kotlinx.android.synthetic.main.activity_talk_shows.*
import kotlinx.android.synthetic.main.base_header.*
import java.util.ArrayList

class TalkShowsActivity : BaseActivity(), GenericMediaAdapter.OnRecyclerItemClicked, View.OnClickListener {

    companion object {
        fun start(context: Context) : Intent {
            return Intent(context , TalkShowsActivity::class.java)
        }
    }

    var adapter: GenericMediaAdapter? = null

    override fun getLayout(): Int {
        return R.layout.activity_talk_shows
    }

    override fun setEventListeners() {
        backBtn?.setOnClickListener(this)
    }

    override fun setup() {
        setTitle("Popular")
        setDescription("Talk Shows")
        setupRv()
    }

    private fun setupRv() {
        adapter = GenericMediaAdapter(R.layout.one_o_one_card_layout, this)
        talkShowsRv?.setLayoutManager()
        talkShowsRv?.adapter = adapter

        adapter?.update(makeMediaList())

    }

    private fun makeMediaList(): ArrayList<GenericMedia> {
        val list = arrayListOf<GenericMedia>()

        for (i in 0 until 20) {
            list.add(GenericMedia(title = "Movie - $i", imageUrl = getUrl(i), streamUrl = ""))
        }

        return list
    }

    private fun getUrl(i: Int): String {

        return if (i % 2 == 0)
            "https://m.media-amazon.com/images/M/MV5BOTk5ODg0OTU5M15BMl5BanBnXkFtZTgwMDQ3MDY3NjM@._V1_UY209_CR0,0,140,209_AL_.jpg"
        else
            "https://m.media-amazon.com/images/M/MV5BMjAwODg3OTAxMl5BMl5BanBnXkFtZTcwMjg2NjYyMw@@._V1_UX182_CR0,0,182,268_AL_.jpg"
    }

    override fun onItemClicked(media: GenericMedia?, position: Int) {
        // do nothing
    }

    override fun onClick(view: View?) {
        if (view == backBtn){
            finish()
        }
    }

}