package com.techlads.myapplication.ui

import android.content.Context
import android.content.Intent
import com.techlads.myapplication.R
import com.techlads.myapplication.base.BaseActivity


/**
 * Created by Yasir on 11/2/2020.
 */

class InitialSetupActivity : BaseActivity() {

    companion object {

        fun start(context: Context) : Intent {
            return Intent(context , InitialSetupActivity::class.java)
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_initial_setup
    }

    override fun setEventListeners() {

    }

    override fun setup() {

        setTitle("Initial Setup")
        setDescription("Initial Setup")
    }
}