package com.techlads.myapplication.api

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.techlads.myapplication.data.GenericMedia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import org.jsoup.Jsoup
import org.jsoup.nodes.Document


/**
 * Created by Yasir on 11/13/2020.
 */

class MediaHandler {

    val BASE_URL: String = "http://ad.vortextech.org/"

    suspend fun getMedia(url: String): MutableLiveData<ArrayList<GenericMedia>> {
        val listOfMovies: MutableLiveData<ArrayList<GenericMedia>> = MutableLiveData()
        val list = arrayListOf<GenericMedia>()
        withContext(Dispatchers.IO) {
            val document: Document = Jsoup.connect(BASE_URL + url).get()
            document.select("a").forEach {
                Log.e("Link", it.text())

                if (it.text().endsWith(".mp4")) {
                    var url = BASE_URL + url + it.text()
                    var title = it.text().replace(".mp4", "")
                    list.add(GenericMedia(title = title, imageUrl = "", streamUrl = url ))
                } else if (it.text().endsWith(".mp3")) {
                    var url = BASE_URL + "videosongs/" + it.text()
                    var title = it.text().replace(".mp3", "")
                    list.add(GenericMedia(title = title, imageUrl = "", streamUrl = url ))
                }
            }
            listOfMovies.postValue(list)
        }
        return listOfMovies
    }

    suspend fun getVideoSongs(): MutableLiveData<ArrayList<GenericMedia>> {
        val listOfMovies: MutableLiveData<ArrayList<GenericMedia>> = MutableLiveData()
        val list = arrayListOf<GenericMedia>()
        withContext(Dispatchers.IO) {
            val document: Document = Jsoup.connect(BASE_URL + "videosongs/").get()
            document.select("a").forEach {
                Log.e("Link", it.text())

                if (it.text().endsWith(".mp4")){
                    var url = BASE_URL + "videosongs/" + it.text()
                    var title = it.text().replace(".mp4", "")
                    list.add(GenericMedia(title = title, imageUrl = "", streamUrl = url ))
                }
            }
            listOfMovies.postValue(list)
        }
        return listOfMovies
    }

    suspend fun getCategories(): MutableLiveData<ArrayList<GenericMedia>> {
        val listOfMovies: MutableLiveData<ArrayList<GenericMedia>> = MutableLiveData()
        val list = arrayListOf<GenericMedia>()
        withContext(Dispatchers.IO) {
            val document: Document = Jsoup.connect(BASE_URL).get()
            document.select("a").forEach {
                Log.e("Link", it.text())

                /*if (it.text().endsWith(".mp4")){
                    var url = BASE_URL + "videosongs/" + it.text()
                    var title = it.text().replace(".mp4", "")
                    list.add(GenericMedia(title = title, imageUrl = "", streamUrl = url ))
                }*/
            }
            listOfMovies.postValue(list)
        }
        return listOfMovies
    }

}