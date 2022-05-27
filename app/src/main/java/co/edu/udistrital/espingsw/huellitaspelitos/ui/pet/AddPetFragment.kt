package co.edu.udistrital.espingsw.huellitaspelitos.ui.pet

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentAddPetBinding
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.*

@AndroidEntryPoint
class AddPetFragment : Fragment() {

    private var _binding: FragmentAddPetBinding? = null
    private val binding get() = _binding!!
    private var isMale: Boolean = false
    private var isFemale: Boolean = false
    private var isSterilized: Boolean = false
    private var isVaccined: Boolean = false
    private var userId: Int = 0
    private var petId: Int = 0
    private lateinit var navController: NavController
    private lateinit var addPetViewModel: AddPetViewModel
    private var PICK_IMAGE_REQUEST: Int = 0
    private lateinit var filePath: Uri
    private lateinit var file: File
    private var photoURL: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            userId = it.getInt(Constants.USER_ID)
            petId = it.getInt(Constants.PET_ID)
        }
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentAddPetBinding.inflate(inflater, container, false)
        navController = findNavController()
        addPetViewModel = ViewModelProvider(this)[AddPetViewModel::class.java]

        _binding?.btnSave?.setOnClickListener {
            val result = createPet()
            if (result == 1) {
                Snackbar.make(it, "Mascota creada exitosamente.",
                    Snackbar.LENGTH_LONG)
            } else {
                Snackbar.make(it, "Error al crear a la mascota.",
                    Snackbar.LENGTH_LONG)
            }

        }

        _binding?.petPhoto?.setOnClickListener {
            getImageFromStorage()
        }

        _binding?.petIsMale?.setOnClickListener {
            onRadioButtonClicked(it)
        }

        _binding?.petIsFemale?.setOnClickListener {
            onRadioButtonClicked(it)
        }

        _binding?.petIsSterilized?.setOnClickListener {
            onCheckboxClicked(it)
        }

        _binding?.petIsVaccined?.setOnClickListener {
            onCheckboxClicked(it)
        }

        addPetViewModel.petCreated.observe(viewLifecycleOwner, Observer {
            //Tomar el objeto e ir a la vista inicial
            if(it != null){
                findNavController().navigate(R.id.action_add_pet_to_home)
                Log.d("msg", "Mascota Creada!")
                Snackbar.make(binding.scrollViewAddPet, "Mascota creada exitosamente.",
                    Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(binding.scrollViewAddPet, "Error al crear a la mascota.",
                    Snackbar.LENGTH_LONG).show()
            }

        })

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === PICK_IMAGE_REQUEST && resultCode === RESULT_OK && data!!.data != null) {

            filePath = data?.data!!

            _binding?.petPhoto?.setImageURI(filePath)

            val inputStream: InputStream? = activity?.contentResolver?.openInputStream(filePath)

            file = File.createTempFile("image",filePath?.lastPathSegment)

            val outStream: OutputStream = FileOutputStream(file)

            outStream.write(inputStream!!.readBytes())
        }
    }

    private fun getImageFromStorage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_OPEN_DOCUMENT

        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createPet(): Int{
        var toReturn = 0

        var basicAWSCredentials = BasicAWSCredentials(Constants.ACCESS_ID,
            Constants.SECRET_KEY)
        var s3Client = AmazonS3Client(basicAWSCredentials, Region.getRegion(Regions.US_EAST_1))

        val uniqueFileName = UUID.randomUUID().toString() + ".img"

        val trans = TransferUtility.builder().context(context).s3Client(s3Client).build()
        val observer: TransferObserver = trans.upload(Constants.BUCKET_NAME,
            uniqueFileName, file)
        observer.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState) {
                if (state == TransferState.COMPLETED) {
                    photoURL = uniqueFileName
                    Log.d("msg","success")
                    createPetRemoteCall()
                    toReturn = 1
                } else if (state == TransferState.FAILED) {
                    Log.d("msg","fail")
                }
            }
            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {

                if(bytesCurrent == bytesTotal){
                    Log.d("msg", "File upload to AWS is being done.")
                }
            }
            override fun onError(id: Int, ex: Exception) {
                Log.d("error",ex.toString())
            }
        })

        return toReturn
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createPetRemoteCall(){
        val petName = _binding?.edtPetName?.text.toString()
        val petBirthDate = _binding?.edtPetBirthdate?.getDate()
        val petGender = isMale or isFemale
        val petWeight = _binding?.petWeight?.text.toString()
        val isPetSterilized = isSterilized
        val isPetVaccined = isVaccined
        val petObservations = _binding?.petAdditionalObservations?.text.toString()

        if (petBirthDate != null) {
            addPetViewModel.createPet(petName, petBirthDate, petGender, petWeight, isPetSterilized,
                isPetVaccined, petObservations, photoURL, userId)
        }

    }

    private fun DatePicker.getDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return calendar.time
    }

    private fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.pet_is_male ->
                    if (checked) {
                        isMale = true
                    }
                R.id.pet_is_female ->
                    if (checked) {
                        isFemale = true
                    }
            }
        }
    }

    private fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.pet_is_sterilized -> {
                    isSterilized = checked
                }
                R.id.pet_is_vaccined -> {
                    isVaccined = checked
                }
            }
        }
    }

}