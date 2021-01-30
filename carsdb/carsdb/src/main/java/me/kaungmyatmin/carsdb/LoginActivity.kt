package me.kaungmyatmin.carsdb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SubscriptionPlan
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvRegister.setOnClickListener {
            val intent = RegisterActivity.newIntent(this)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            when {
                email.isBlank() -> {
                    containerEmail.error = "User Name is required"
                }
                password.isBlank() -> {
                    containerPassword.error = "Password is required"
                }
                else -> {
                    val auth = FirebaseAuth.getInstance()
                    auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task->
                            if(task.isSuccessful){
                                val intent = MainActivity.newIntent(this)
                                startActivity(intent)
                            }else{
                                val message = task.exception?.message?:"Unknown"
                                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                            }
                        }
                }
            }
        }
    }

}
