package co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.ActivityVisitCreatedByPetOwnerBinding
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.viewmodel.VisitCreatedByPetOwnerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VisitCreatedByPetOwnerActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityVisitCreatedByPetOwnerBinding
    private val binding get() = _binding!!
    private var visitId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityVisitCreatedByPetOwnerBinding.inflate(layoutInflater)

        visitId = intent.getIntExtra(Constants.VISIT_ID, 0)


        val viewModel = ViewModelProvider(this)[VisitCreatedByPetOwnerViewModel::class.java]

        visitId?.let { viewModel.getDataToShow(it) }

        val ownerName = "Obteniendo dueño..."
        val petName = "Obteniendo mascota..."
        val uuid = "Confirmando..."

        val strFormatted =
            this.getString(R.string.visit_estheticist_created, ownerName, petName)

        _binding.txtInfo.text = strFormatted
        _binding.txtVerificationCode.text = uuid

        viewModel.dataToUI.observe(this@VisitCreatedByPetOwnerActivity) { data ->
            if (data != null) {
                val ownerName = data[1]
                val petName = data[0]
                val uuid = data[2]

                val strFormatted =
                    this.getString(R.string.visit_estheticist_created, ownerName, petName)

                _binding.txtInfo.text = strFormatted

                _binding.txtVerificationCode.text = uuid
            } else {
                val ownerName = "Dueño"
                val petName = "Mascota"
                val uuid = "Confirmando..."

                val strFormatted =
                    this.getString(R.string.visit_estheticist_created, ownerName, petName)

                _binding.txtInfo.text = strFormatted
                _binding.txtVerificationCode.text = uuid
            }
        }


        _binding.startVisit.setOnClickListener {
            goToStartServices()
        }

        setContentView(binding.root)
    }

    private fun goToStartServices(){
        startActivity(Intent(applicationContext, StartServicesActivity::class.java).apply {
            putExtra(Constants.VISIT_ID, visitId)
        })
        finish()
    }
}