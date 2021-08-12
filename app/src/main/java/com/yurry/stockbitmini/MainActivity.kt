package com.yurry.stockbitmini

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yurry.stockbitmini.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}