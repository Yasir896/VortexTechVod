package com.techlads.myapplication.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.ads.AdsMediaSource
import com.google.android.exoplayer2.upstream.DataSource

import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.techlads.myapplication.R
import com.techlads.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : BaseActivity() {


    var content_url: String = ""
    companion object {
        fun start(context: Context) : Intent {
            return Intent(context , PlayerActivity::class.java)
        }
    }

    private var player: SimpleExoPlayer? = null
    private var adsLoader: ImaAdsLoader? = null

    override fun getLayout(): Int {
        return R.layout.activity_player
    }

    override fun setEventListeners() {}

    override fun setup() {
        content_url = intent?.getStringExtra("URL_STRING")!!
        adsLoader = ImaAdsLoader(this, Uri.parse(getString(R.string.ad_tag_url)))

    }

    private fun releasePlayer() {
        adsLoader?.setPlayer(null)
        playerView?.setPlayer(null)
        player?.release()
        player = null
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun initializePlayer() {
        // Create a SimpleExoPlayer and set is as the player for content and ads.
        player = SimpleExoPlayer.Builder(this).build()
        playerView?.player = player
        playerView.keepScreenOn = true
        adsLoader?.setPlayer(player)
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(this, Util.getUserAgent(this, getString(R.string.app_name)))
        val mediaSourceFactory = ProgressiveMediaSource.Factory(dataSourceFactory)

        // Create the MediaSource for the content you wish to play.
        val mediaSource: MediaSource = mediaSourceFactory.createMediaSource(Uri.parse(content_url))

        // Create the AdsMediaSource using the AdsLoader and the MediaSource.
        //val adsMediaSource = AdsMediaSource(mediaSource, dataSourceFactory, adsLoader, playerView)

        // Prepare the content and ad to be played with the SimpleExoPlayer.
        player?.prepare(mediaSource)

        // Set PlayWhenReady. If true, content and ads autoplay.
        player?.playWhenReady = false
    }

    override fun onStart() {
        super.onStart()
        //
        if (Util.SDK_INT > 23) {
            initializePlayer()
            playerView?.onResume()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer()
            playerView?.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            playerView?.onPause()
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            playerView?.onPause()
            releasePlayer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adsLoader?.release()
    }

}