package co.edu.udistrital.espingsw.huellitaspelitos.ui.userregistry

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.UserDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.ActivityUserRegistryBinding
import co.edu.udistrital.espingsw.huellitaspelitos.ui.login.LoginActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal

@AndroidEntryPoint
class UserRegistryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserRegistryBinding
    private var documentType = 0
    private var pushyToken: String = ""
    private lateinit var userRegistryViewModel: UserRegistryViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserRegistryBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        pushyToken = intent.getStringExtra(Constants.PUSHY_TOKEN).toString()

        userRegistryViewModel = ViewModelProvider(this)[UserRegistryViewModel::class.java]

        val actv = binding.actvDocumentList
        val register = binding.btnRegister

        val nameField = binding.edtPersonName
        val documentNumberField = binding.edtDocumentNumber
        val loginField = binding.edtLogin
        val passwordField = binding.edtPassword
        val emailField = binding.edtEmail
        val phoneField = binding.edtPhone
        val addressField = binding.edtAddress

        val items = listOf("Cédula de Ciudadanía", "Cédula de Extranjería", "Pasaporte", "NIT")
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, items)
        actv?.setAdapter(adapter)
        actv.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            documentType = position
        }

        register.setOnClickListener {
            userRegistry(nameField.text.toString(), documentNumberField.text.toString(),
                documentType, loginField.text.toString(), passwordField.text.toString(),
                emailField.text.toString(), phoneField.text.toString(),
                addressField.text.toString(), pushyToken)
        }



        userRegistryViewModel.goToUserCreated.observe(this@UserRegistryActivity, Observer {
            if(it){
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            } else {
                Snackbar.make(view, "Error al crear el usuario",
                    Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun userRegistry(name: String, documentNumber: String, documentType: Int,
                             login: String, password: String, email: String, phone: String,
                             address: String, token: String){

        val userRegistryDTO = UserRegistryDTO(name, documentNumber, documentType, login, password,
            email, phone, address, token)

        userRegistryViewModel.createUserAndClient(userRegistryDTO)
    }


}