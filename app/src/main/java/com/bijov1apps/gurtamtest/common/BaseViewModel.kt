package com.bijov1apps.gurtamtest.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bijov1apps.gurtamtest.common.livedata.LiveEvent
import com.bijov1apps.gurtamtest.common.livedata.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<State : ViewState, Action : ViewAction>(
    initState: State
) : ViewModel() {

    private val viewStateFlow = MutableStateFlow(initState)
    val viewStateLiveData: LiveData<State> = viewStateFlow.asLiveData()

    private val _viewActionLiveData: MutableLiveData<Action> = LiveEvent<Action>()
    val viewActionLiveData: LiveData<Action> = _viewActionLiveData.asLiveData()

    protected fun reduceState(reducer: (oldState: State) -> State) {
        val oldState = viewStateLiveData.value!!
        viewStateFlow.value = reducer(oldState)
    }

    fun sendAction(action: Action) {
        _viewActionLiveData.value = action
    }
}