package com.bijov1apps.gurtamtest.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bijov1apps.gurtamtest.utils.LiveEvent
import com.bijov1apps.gurtamtest.utils.asLiveData

abstract class BaseViewModel<State : ViewState, Action : ViewAction>(
    initState: State
) : ViewModel() {

    private val _viewStateLiveData: MutableLiveData<State> = MutableLiveData()
    val viewStateLiveData: LiveData<State> = _viewStateLiveData.asLiveData()

    private val _viewActionLiveData: MutableLiveData<Action> = LiveEvent<Action>()
    val viewActionLiveData: LiveData<Action> = _viewActionLiveData.asLiveData()

    init {
        initState.let(_viewStateLiveData::setValue)
    }

    protected fun reduceState(reducer: (oldState: State?) -> State) {
        val oldState = viewStateLiveData.value
        _viewStateLiveData.value = reducer(oldState)
    }

    fun sendAction(action: Action) {
        _viewActionLiveData.value = action
    }
}