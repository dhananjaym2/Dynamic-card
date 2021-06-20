package dj.dynamic.card.retrofit

import dj.dynamic.card.model.api.CardGroupApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("04a04703-5557-4c84-a127-8c55335bb3b4")
    fun getCardGroupListData() : Call<CardGroupApiResponse>
}