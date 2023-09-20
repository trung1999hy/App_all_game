package com.example.manygameinapplication.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.manygameinapplication.model.GameModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {
    private var apiService: ApiService? = null

    init {
        apiService = RetrofitClient.create()
    }

    private fun <T> makeApiCall(apiCall: Call<T>?): LiveData<T> {
        val data = MutableLiveData<T>()

        apiCall?.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {}
        })

        return data
    }

    fun getRacingGame(): LiveData<ArrayList<GameModel>> {
        return makeApiCall(apiService?.getRacingData())
    }

    fun getAdventureGame(): LiveData<ArrayList<GameModel>> {
        return makeApiCall(apiService?.getAdventureData())
    }

    fun getArcadeGame(): LiveData<ArrayList<GameModel>> {
        return makeApiCall(apiService?.getArcadeData())
    }

    fun getPuzzleGame(): LiveData<ArrayList<GameModel>> {
        return makeApiCall(apiService?.getPuzzleData())
    }

    fun getSportsGame(): LiveData<ArrayList<GameModel>> {
        return makeApiCall(apiService?.getSportsData())
    }
}