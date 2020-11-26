package com.techlads.myapplication.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.techlads.myapplication.KidsActivity
import com.techlads.myapplication.R
import com.techlads.myapplication.base.BaseActivity
import com.techlads.myapplication.data.GenericMedia
import com.techlads.myapplication.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_header.*
import kotlinx.android.synthetic.main.custome_message_box.view.*
import kotlinx.android.synthetic.main.header_home_layout.*
import java.util.*


class MainActivity : BaseActivity(), View.OnClickListener, GenericMediaAdapter.OnRecyclerItemClicked {

    var sharedPreference: SharedPreference? = null
    var clickCount = 0

    companion object {

        fun start(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
    var adapter: GenericMediaAdapter? = null


    override fun getLayout(): Int {
        return R.layout.activity_main

    }

    override fun setEventListeners() {
        homeIv?.setOnClickListener(this)
    }

    override fun setup() {
        sharedPreference =
            SharedPreference(this)

        var isFirstRun =  sharedPreference?.getValueBoolien(FIRST_RUN, true)

        if (isFirstRun!!){
            startActivity(InitialSetupActivity.start(this))
        }
        setRouteName()
        setupRv()
    }

    private fun setRouteName() {
        var routeName = sharedPreference?.getValueString(ROUTE_NAME)

        if (routeName.equals(""))
            return
        else
            routeTv.text = routeName
    }

    private fun setupRv() {
        adapter = GenericMediaAdapter(R.layout.one_o_three_card_layout, this)
        categoriesRV?.setLayoutManager()
        categoriesRV?.adapter = adapter

        adapter?.update(makeMediaList())

    }

    private fun makeMediaList(): ArrayList<GenericMedia> {
        val list = arrayListOf<GenericMedia>()

        list.add(GenericMedia(title = "Movies", imageUrl = "", streamUrl = ""))
        list.add(GenericMedia(title = "Video Songs", imageUrl = "", streamUrl = ""))
        list.add(GenericMedia(title = "Audio Songs", imageUrl = "", streamUrl = ""))
        list.add(GenericMedia(title = "Talk Shows", imageUrl = "", streamUrl = ""))
        list.add(GenericMedia(title = "Kids", imageUrl = "", streamUrl = ""))
        list.add(GenericMedia(title = "Islamic", imageUrl = "", streamUrl = ""))

        return list
    }


    override fun isHome(): Boolean {
        return true
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    override fun onClick(v: View?) {

        when (v) {
            homeIv -> {
                clickCount++
                if(clickCount==5)
                {
                    //first time clicked to do this
                    showMessageBox()
                    homeIv.isEnabled = false
                    Log.e("button count: ", clickCount.toString())
                }
            }
        }
    }

    fun showMessageBox() {
        clickCount = 0
        //Inflate the dialog as custom view
        val messageBoxView = LayoutInflater.from(this).inflate(R.layout.custome_message_box, null)
        //AlertDialogBuilder
        val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView).setCancelable(false)
        //setting text values
        //show dialog
        val  messageBoxInstance = messageBoxBuilder.show()

        messageBoxView.logoutBtn.setOnClickListener {
            if (password == "") {
                messageBoxView.exitPasswordET.error = "Field can not be empty."
            } else {
                logoutApplication(password, messageBoxView.exitPasswordET)
            }

        }

        messageBoxView.cancelBtn.setOnClickListener {
            messageBoxInstance.dismiss()
            homeIv.isEnabled = true
        }
    }


    private fun logoutApplication(password: String, exitPasswordET: EditText?) {
        var exitPass = sharedPreference?.getValueString(PASSWORD_TEXT)

        if (exitPass != null){
             if (exitPass.length == password.length && exitPass == password){
                 this.finish()
             }
        } else {
            exitPasswordET?.error = "unable to logout application."
        }

    }
    override fun onItemClicked(media: GenericMedia?, position: Int) {
            when(position) {
                0 -> {
                    startActivity(MoviesActivity.start(this))
                }
                1 -> {
                    startActivity(VideoSongsActivity.start(this))
                }
                2 -> {
                    startActivity(AudioSongsActivity.start(this))
                }
                3 -> {
                    startActivity(TalkShowsActivity.start(this))
                }
                4 -> {
                    startActivity(KidsActivity.start(this))
                }
                5 -> {
                    startActivity(IslamicActivity.start(this))
                }
            }
    }

}