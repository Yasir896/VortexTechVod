package com.techlads.myapplication.ui

import android.content.Context
import android.content.Intent
import android.view.View
import com.techlads.myapplication.R
import com.techlads.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_initial_setup.*


/**
 * Created by Yasir on 11/2/2020.
 */

class InitialSetupActivity : BaseActivity(), View.OnClickListener {

    companion object {

        fun start(context: Context) : Intent {
            return Intent(context , InitialSetupActivity::class.java)
        }
    }



    override fun getLayout(): Int {
        return R.layout.activity_initial_setup
    }

    override fun setEventListeners() {
        submitBtn?.setOnClickListener(this)
    }

    override fun setup() {
        setTitle("Initial Setup")
        setDescription("Initial Setup")
    }

    override fun onClick(view: View?) {
        if (view == submitBtn){
            saveConfiguration()
        }
    }

    private fun saveConfiguration() {
        var companyName = companyNameET?.text.toString()
        var routeName = routeNameET?.text.toString()
        var busNumber = busNumberET.text.toString()
        var seatNumber = seatNumberET.text.toString()
        var password = passwordTextET.text.toString()

        if (companyName == "") {
            companyNameET.error = "All Fields are required."
        } else if (routeName == "") {
            routeNameET.error = "All Fields are required."
        }else if (busNumber == "") {
            busNumberET.error = "All Fields are required."
        } else if (seatNumber == "") {
            seatNumberET.error = "All Fields are required."
        } else if (password == "") {
            passwordTextET.error = "All Fields are required."
        } else {

        }
    }
}