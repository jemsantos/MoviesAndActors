package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Account
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            login()
        }

        var buttonSignUp = findViewById<Button>(R.id.buttonSignUp)
        buttonSignUp.setOnClickListener {
            register()
        }
    }

    fun register()
    {
        var intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun login() {
        var email = findViewById<TextInputEditText>(R.id.email)
        var password = findViewById<TextInputEditText>(R.id.password)

        var account = Account()


        if (email.text.toString().isEmpty() or password.text.toString().isEmpty()) {
            Toast.makeText(this@LoginActivity,"Os campos Usuário e senha são obrigatórios", Toast.LENGTH_LONG).show()
        } else {
            account.email = email.text.toString()
            account.password = password.text.toString()

            val s = RetrofitInitializer().serviceAccount()
            val call = s.auth(account)

            call.enqueue(object : retrofit2.Callback<Account> {
                override fun onResponse(call: Call<Account>, response: Response<Account>) {
                    if (response.code() == 200) {
                        response.body()?.let {
                            var intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("account", it)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        Toast.makeText(
                                this@LoginActivity,
                                "Usuário e/ou senha inválidos",
                                Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Account>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Ooopssss!!!", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}