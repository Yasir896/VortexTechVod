package com.techlads.myapplication.base

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.techlads.myapplication.R
import com.techlads.myapplication.utils.inflate
import kotlinx.android.synthetic.main.base_header.*
import kotlinx.android.synthetic.main.header_home_layout.*
import kotlinx.android.synthetic.main.header_title_layout.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Yasir on 11/3/2020.
*/

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayout())
        initAll()
        setup()
        setEventListeners()
    }


    private fun initAll() {
        if (isHome()){
            headerContentLl?.inflate(R.layout.header_home_layout , true)
            setDateAndTime()
        }else {
            headerContentLl?.inflate(R.layout.header_title_layout , true)
        }
    }

    private fun setDateAndTime() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val date = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val time = DateTimeFormatter.ofPattern("HH:mm a")
            dateTv?.text =  current.format(date)
            timeTv?.text = current.format(time)

        } else {
            var date = Date()
            val dateFormate = SimpleDateFormat("EEEE, MMMM d, yyyy")
            val timeFormate = SimpleDateFormat("HH:mm a")
            dateTv.text = dateFormate.format(date)
            timeTv.text = timeFormate.format(date)
        }
    }

    abstract fun getLayout() : Int
    abstract fun setEventListeners() //Only event listeners
    abstract fun setup() //Only initial level setup

    protected open fun isHome() : Boolean {
        return false
    }

    fun setTitle(text: String) {
       titleNameTv?.text = text
    }

    fun setDescription(text: String) {
       titleDescriptionTv?.text = text
    }

}