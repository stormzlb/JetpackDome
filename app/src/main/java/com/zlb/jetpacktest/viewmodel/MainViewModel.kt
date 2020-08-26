package com.zlb.jetpacktest.viewmodel

import androidx.lifecycle.ViewModel

/**
 * 专门用存放与节目相关的数据，只要是界面上的看得到数据，它的相关的变量都应该存放在ViewModel中
 */

class MainViewModel(countReserved:Int) : ViewModel() {

    var count = countReserved;

}