package com.example.currencyapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.example.currencyapp.model.ConvertResponseModel
import com.example.currencyapp.model.CurrencyResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class VM @Inject constructor(private val repo: Repo) : ViewModel() {


    val loadingState = MutableLiveData<Boolean>()
    private val _currencyResponse = MutableLiveData<Event<CurrencyResponseModel>>()
    val currencyResponse = _currencyResponse
    private val _convertResponse = MutableLiveData<Event<ConvertResponseModel>>()
    val convertResponse = _convertResponse

    fun fetchCurrency() = viewModelScope.launch {
        try {
            setIsLoading(true)
            _currencyResponse.value = Event(repo.fetchCurrency())
        } catch (e: IOException) {
            setIsLoading(false)
        } finally {
            setIsLoading(false)
        }
    }

    fun convert(to: String, from: String, amount: String) = viewModelScope.launch {
        try {
            setIsLoading(true)
            _convertResponse.value = Event(repo.fetchCurrency(to, from, amount))
        } catch (e: IOException) {
            setIsLoading(false)
        } finally {
            setIsLoading(false)
        }
    }

    private fun setIsLoading(loading: Boolean) {
        loadingState.value = loading
    }
}