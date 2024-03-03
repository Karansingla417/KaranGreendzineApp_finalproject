package com.example.KaranGreendzineApp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.KaranGreendzineApp.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            fun showLoginSuccessDialog() {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Login Successful")
                alertDialog.setMessage("Welcome to the next screen!")

                alertDialog.setPositiveButton("OK") { _, _ ->
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                alertDialog.setCancelable(false)
                alertDialog.show()
            }

            fun showLoginFailDialog() {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Login Failed")
                alertDialog.setMessage("Wrong credentials! Please try again.")

                alertDialog.setPositiveButton("OK", null) // No need to specify OnClickListener to stay on the same screen

                alertDialog.setCancelable(true)
                alertDialog.show()
            }

            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()
            if(authenticateUser(email,password)){
                CoroutineScope(Dispatchers.Main).launch {
                    showLoginSuccessDialog()
                }
            }else{
                showLoginFailDialog()
            }
        }
    }

    private fun authenticateUser(email: String, password: String): Boolean {
        val json: String? = loadJsonFromAsset(this, "users.json")
        val gson = Gson()
        val type = object : TypeToken<List<Users>>() {}.type
        val users: List<Users> = gson.fromJson(json, type)

        for (user in users) {
            if (user.email == email && user.password == password) {
                return true
            }
        }
        return false
    }

    private fun loadJsonFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}