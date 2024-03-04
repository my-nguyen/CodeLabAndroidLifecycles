package com.nguyen.codelabandroidlifecycles

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStateViewModel(private val state: SavedStateHandle) : ViewModel() {
    // getLiveData obtains an object that is associated with the key wrapped in a LiveData
    // so it can be observed for changes.
    val name: LiveData<String> = state.getLiveData(NAME_KEY)

    fun saveNewName(newName: String?) {
        // Sets a new value for the object associated to the key. There's no need to set it
        // as a LiveData.
        state[NAME_KEY] = newName
    }

    companion object {
        private const val NAME_KEY = "name"
    }
}