package com.example.randomjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.randomjoke.api.ApiInterface
import com.example.randomjoke.api.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var txtData: TextView
    private lateinit var newJokeBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtData = findViewById(R.id.txtData)
        newJokeBtn = findViewById(R.id.newJoke)

        getUserList()
        newJokeBtn.setOnClickListener {
            getUserList()
        }
    }

    private fun getUserList() {

        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getJoke()
                if (response.isSuccessful) {
                    txtData.text = response.body()?.joke.toString()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }
}