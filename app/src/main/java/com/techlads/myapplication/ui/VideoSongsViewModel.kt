package com.techlads.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techlads.myapplication.api.MediaHandler
import com.techlads.myapplication.data.GenericMedia

/**
 * Created by Yasir on 11/14/2020.
 */

class VideoSongsViewModel: ViewModel() {

    suspend fun loadSongs(): MutableLiveData<ArrayList<GenericMedia>>? {
        return MediaHandler().getVideoSongs()
    }

}