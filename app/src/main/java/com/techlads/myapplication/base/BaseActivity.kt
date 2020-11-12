package com.techlads.myapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.techlads.myapplication.R
import com.techlads.myapplication.utils.inflate
import kotlinx.android.synthetic.main.base_header.*
import kotlinx.android.synthetic.main.header_home_layout.*
import kotlinx.android.synthetic.main.header_title_layout.*

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
        timeTv?.text = "07 : 00 PM"
        dateTv?.text = "Sunday, November 1,2020"
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