package com.nguyen.codelabandroidlifecycles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeekBarViewModel : ViewModel() {
    var seekbarValue = MutableLiveData<Int>()
}