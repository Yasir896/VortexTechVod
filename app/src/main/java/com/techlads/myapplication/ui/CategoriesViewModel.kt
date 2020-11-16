package com.techlads.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techlads.myapplication.api.MediaHandler
import com.techlads.myapplication.data.GenericMedia


/**
 * Created by Yasir on 11/15/2020.
 */

class CategoriesViewModel: ViewModel() {

    suspend fun getCategories(): MutableLiveData<ArrayList<GenericMedia>>? {
        return MediaHandler().getCategories()
    }

}