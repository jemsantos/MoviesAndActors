package br.com.cotemig.jose.matheus.moviesandactors.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.cotemig.jose.matheus.moviesandactors.R
import br.com.cotemig.jose.matheus.moviesandactors.models.Account
import br.com.cotemig.jose.matheus.moviesandactors.services.RetrofitInitializer
import com.google.android.material.textfield.TextInputEditText

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener {
            register()
        }
    }

    fun register() {
        var name = findViewById<EditText>(R.id.name)
        var email = findViewById<EditText>(R.id.email)
        var password = findViewById<EditText>(R.id.password)

        var account = Account()

        account.email = email.text.toString()
        account.password = password.text.toString()
        account.name = name.text.toString()

        var s = RetrofitInitializer().serviceAccount()
        var call = s.register(account)

        call.enqueue(object : Callback<Account> {
            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {
                response?.let {
                    if(it.code() == 200) {
                        Toast.makeText(this@RegisterActivity,"Cadastro realizado", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@RegisterActivity,"Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<Account>?, t: Throwable?) {
                Toast.makeText(this@RegisterActivity,"Ops deu erro", Toast.LENGTH_LONG).show()
            }
        })
    }
}