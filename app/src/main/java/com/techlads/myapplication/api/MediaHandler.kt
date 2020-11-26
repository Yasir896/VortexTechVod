package com.techlads.myapplication.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.techlads.myapplication.data.Details
import com.techlads.myapplication.data.GenericMedia
import com.techlads.myapplication.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


/**
 * Created by Yasir on 11/13/2020.
 */

class MediaHandler {

    val BASE_URL: String = "http://ad.vortextech.org/"

    companion object {
        val MOVIE = "movie.mp4"
        val TRACK = "track.mp4"
        val DETAILS = "details.xml"
        val THUMB = "thumb.jpeg"
        val LARGE_IMAGE = "large_image.jpeg"
    }

    suspend fun getMedia(dir: String): MutableLiveData<ArrayList<GenericMedia>> {
        val listOfMovies: MutableLiveData<ArrayList<GenericMedia>> = MutableLiveData()
        val list = arrayListOf<GenericMedia>()

        val parentUrl = BASE_URL + dir

        withContext(Dispatchers.IO) {
            val document: Document = Jsoup.connect(parentUrl).get()
            document.select("a").forEach {
                Log.e("Link", it.text())

                /*if (it.text().endsWith(".mp4")) {
                    var url = BASE_URL + url + it.text()
                    var title = it.text().replace(".mp4", "")
                    list.add(GenericMedia(title = title, imageUrl = "", streamUrl = url ))
                } else if (it.text().endsWith(".mp3")) {
                    var url = BASE_URL + "videosongs/" + it.text()
                    var title = it.text().replace(".mp3", "")
                    list.add(GenericMedia(title = title, imageUrl = "", streamUrl = url ))
                }*/

                if (it.text().endsWith("/")) {
                    try {

                        val childUrl = parentUrl + it.text()
                        //Details
                        val document: Document =
                            Jsoup.connect(childUrl).ignoreContentType(true).get()

                        val movie = processChild(document, childUrl)
                        list.add(movie)

                    } catch (e: Exception) {
                        Log.e("Exception", e.toString())
                    }
                }
            }
            listOfMovies.postValue(list)
        }
        return listOfMovies
    }

    private fun processChild(document: Document, url: String): Movie {

        val movie = Movie("", "", null)

        document.select("a").forEach {
            when (it.text()) {

                MOVIE -> {
                    movie.streamUrl = url + it.text()
                }

                TRACK -> {
                    movie.streamUrl = url + it.text()
                }

                DETAILS -> {
                    movie.details = readDetails(url + DETAILS)
                    movie.title = movie.details?.title
                }

                THUMB -> {
//                    movie.imageUrl TODO: MAKE THUMB
                }

                LARGE_IMAGE -> {
                    movie.imageUrl = url + LARGE_IMAGE
                }
            }
        }
        return movie
    }

    private fun readDetails(url: String): Details? {
        val doc = Jsoup.connect(url).get()
        return Details(doc.getElementsByTag("title").text(), doc.getElementsByTag("description").text(), doc.getElementsByTag("year").text() )
    }

    suspend fun getVideoSongs(): MutableLiveData<ArrayList<GenericMedia>> {
        val listOfMovies: MutableLiveData<ArrayList<GenericMedia>> = MutableLiveData()
        val list = arrayListOf<GenericMedia>()
        withContext(Dispatchers.IO) {
            val document: Document = Jsoup.connect(BASE_URL + "videosongs/").get()
            document.select("a").forEach {
                Log.e("Link", it.text())

                if (it.text().endsWith(".mp4")) {
                    var url = BASE_URL + "videosongs/" + it.text()
                    var title = it.text().replace(".mp4", "")
                    list.add(GenericMedia(title = title, imageUrl = "", streamUrl = url))
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