package dj.dynamic.card.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dj.dynamic.card.model.api.CardGroupApiResponse
import dj.dynamic.card.retrofit.RetrofitClient
import dj.dynamic.card.view.adapter.VerticalRecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Main activity repository as a singleton
 */
object MainActivityRepository {

    private val logTag: String = MainActivityRepository::class.java.simpleName

    val apiResponseMutableLiveData = MutableLiveData<CardGroupApiResponse>()

    /**
     * Call the remote server API to fetch the data asynchronously.
     */
    fun getCardGroupList(): MutableLiveData<CardGroupApiResponse> {

        val call = RetrofitClient.apiInterface.getCardGroupListData()

        call.enqueue(object : Callback<CardGroupApiResponse> {
            override fun onFailure(call: Call<CardGroupApiResponse>, t: Throwable) {
                Log.e(logTag, t.message.toString())
                throw t
            }

            override fun onResponse(
                call: Call<CardGroupApiResponse>,
                response: Response<CardGroupApiResponse>
            ) {
                apiResponseMutableLiveData.value = response.body()
            }
        })

        return apiResponseMutableLiveData
    }
}