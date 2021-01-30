package me.kaungmyatmin.carsdb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)
        title = "Home"
        toolBar.setOnClickListener {
            logout()
        }
    }
    private fun logout(){
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent(this,SplashActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

}