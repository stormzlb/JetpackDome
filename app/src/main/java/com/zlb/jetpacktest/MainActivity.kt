package com.zlb.jetpacktest

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.zlb.jetpacktest.lifecycles.MyObserver
import com.zlb.jetpacktest.viewmodel.MainViewModel
import com.zlb.jetpacktest.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycle.addObserver(MyObserver(lifecycle))

    //    lifecycle.currentState

        sp=getPreferences(Context.MODE_PRIVATE)
        val countReserved=sp.getInt("count_reserved",0)
        viewModel=ViewModelProviders.of(this,MainViewModelFactory(countReserved)).get(MainViewModel::class.java)

        pushOneBtn.setOnClickListener{
            viewModel.count++
            refreshCounter()
        }

        refreshCounter()

    }


    @SuppressLint("CommitPrefEdits")
    override fun onPause() {
        super.onPause()
        sp.edit().putInt("count_reserved", viewModel.count)
    }

    private fun refreshCounter() {
        infoText.text=viewModel.count.toString()
    }
}

