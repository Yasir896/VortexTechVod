package com.techlads.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techlads.myapplication.api.MediaHandler
import com.techlads.myapplication.data.GenericMedia


/**
 * Created by Yasir on 11/13/2020.
 */

class GenericViewModel: ViewModel() {
    private var mediaHandler: MediaHandler? = null
//    private val media: MutableLiveData<ArrayList<GenericMedia>> by lazy {
//        MutableLiveData<ArrayList<GenericMedia>>().also {
//            loadMovies()
//        }
//    }
//
//    fun getMovies(): LiveData<ArrayList<GenericMedia>> {
//        return media
//    }

    suspend fun loadMovies(url: String): MutableLiveData<ArrayList<GenericMedia>>? {

        //              mediaHandler = MediaHandler()
//            movies.value = mediaHandler?.getMovies()?.value


        var movies: MutableLiveData<ArrayList<GenericMedia>> = MutableLiveData()
//        viewModelScope.launch {
//              mediaHandler = MediaHandler()
//            movies.value = mediaHandler?.getMovies()?.value
//        }
        return MediaHandler().getMedia(url)
    }
}