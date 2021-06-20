package dj.dynamic.card.viewmodel

import dj.dynamic.card.model.api.CardGroupApiResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dj.dynamic.card.repository.MainActivityRepository

class MainActivityViewModel: ViewModel() {
    var servicesLiveData: MutableLiveData<CardGroupApiResponse>? = null

    /**
     * Fetch the data asynchronously.
     */
    fun fetchCardGroupListData(): LiveData<CardGroupApiResponse>? {
        servicesLiveData = MainActivityRepository.getCardGroupList()
        return servicesLiveData
    }
}