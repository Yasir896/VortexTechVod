package com.techlads.myapplication.ui

import android.content.Context
import android.content.Intent
import android.view.View
import com.techlads.myapplication.R
import com.techlads.myapplication.api.MediaHandler
import com.techlads.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_header.*

class MainActivity : BaseActivity(), View.OnClickListener {


    companion object {

        fun start(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
    var handler: MediaHandler? = null


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun setEventListeners() {
        homeIv?.setOnClickListener(this)
        moviesBtn?.setOnClickListener(this)
        videoSongsBtn?.setOnClickListener(this)
        talkShowsBtn?.setOnClickListener(this)
        islamicBtn?.setOnClickListener(this)
        kidsBtn?.setOnClickListener(this)
        audioSongsBtn.setOnClickListener(this)
    }

    override fun setup() {
    }

    override fun isHome(): Boolean {
        return true
    }

    override fun onClick(v: View?) {

        when (v) {
            homeIv -> {
                startActivity(InitialSetupActivity.start(this))
            }
            moviesBtn -> {
                startActivity(MoviesActivity.start(this))
            }
            videoSongsBtn -> {
                startActivity(VideoSongsActivity.start(this))
            }
            talkShowsBtn -> {
                startActivity(TalkShowsActivity.start(this))
            }
            islamicBtn -> {
                startActivity(IslamicActivity.start(this))
            }
            kidsBtn -> {
                startActivity(TalkShowsActivity.start(this))
            }
            audioSongsBtn -> {
                startActivity(AudioSongsActivity.start(this))
            }
        }
    }

}