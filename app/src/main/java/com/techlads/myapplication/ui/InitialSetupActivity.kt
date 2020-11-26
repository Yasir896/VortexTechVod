package com.techlads.myapplication.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.techlads.myapplication.R
import com.techlads.myapplication.utils.SharedPreference
import com.techlads.myapplication.base.BaseActivity
import com.techlads.myapplication.utils.*
import kotlinx.android.synthetic.main.activity_initial_setup.*


/**
 * Created by Yasir on 11/2/2020.
 */

class InitialSetupActivity : BaseActivity(), View.OnClickListener {

    var sharedPreference: SharedPreference? = null

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
        sharedPreference = SharedPreference(this)

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
            sharedPreference?.save(COMPANY_NAME, companyName)
            sharedPreference?.save(ROUTE_NAME, routeName)
            sharedPreference?.save(BUS_NUMBER, busNumber)
            sharedPreference?.save(SEAT_NUMBER, seatNumber)
            sharedPreference?.save(PASSWORD_TEXT, password)
            sharedPreference?.save(FIRST_RUN, false)
            Toast.makeText(this,"Data Stored",Toast.LENGTH_SHORT).show()

            startActivity(MainActivity.start(this))
        }
    }



    fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
        val key = pair.first
        val value = pair.second
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitive types can be stored in SharedPreferences")
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}