package co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.ActivityEndServicesEstheticistBinding
import co.edu.udistrital.espingsw.huellitaspelitos.ui.login.LoginActivity
import co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.estheticist.viewmodel.EndServicesViewModel
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.*

@AndroidEntryPoint
class EndServicesEstheticistActivity : AppCompatActivity() {

    private var _binding: ActivityEndServicesEstheticistBinding? = null
    private val binding get() = _binding!!
    private var PICK_IMAGE_REQUEST: Int = 0
    private lateinit var filePath: Uri
    private lateinit var file: File
    private var photoURL: String = ""
    private lateinit var endServicesViewModel: EndServicesViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityEndServicesEstheticistBinding.inflate(layoutInflater)

        endServicesViewModel = ViewModelProvider(this)[EndServicesViewModel::class.java]

        _binding?.petPhoto?.setOnClickListener {
            getImageFromStorage()
        }

        _binding?.btnSave?.setOnClickListener {
            val result = createPhoto()
            Log.d("endservicesesth", "res: $result")
        }

        endServicesViewModel.visitUpdated.observe(this@EndServicesEstheticistActivity) {
            if(it != null){
                Log.d("msg", "¡Visita terminada exitosamente!")
                Snackbar.make(binding.root, "¡Visita terminada exitosamente! ¡Buen trabajo!",
                    Snackbar.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            } else {
                Snackbar.make(binding.root, "Error al terminar la visita.",
                    Snackbar.LENGTH_LONG).show()
            }
        }

        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === PICK_IMAGE_REQUEST && resultCode === RESULT_OK && data!!.data != null) {

            filePath = data?.data!!

            _binding?.petPhoto?.setImageURI(filePath)

            val inputStream: InputStream? = this?.contentResolver?.openInputStream(filePath)

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
    private fun createPhoto(): Int{
        var toReturn = 0

        var basicAWSCredentials = BasicAWSCredentials(
            Constants.ACCESS_ID,
            Constants.SECRET_KEY)
        var s3Client = AmazonS3Client(basicAWSCredentials, Region.getRegion(Regions.US_EAST_1))

        val uniqueFileName = UUID.randomUUID().toString() + ".img"

        val trans = TransferUtility.builder().context(applicationContext).s3Client(s3Client).build()
        val observer: TransferObserver = trans.upload(
            Constants.BUCKET_NAME,
            uniqueFileName, file)
        observer.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState) {
                if (state == TransferState.COMPLETED) {
                    photoURL = uniqueFileName
                    Log.d("msg","success")
                    createVisitRemoteCall()
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
    private fun createVisitRemoteCall(){
        val visit = endServicesViewModel.getVisitFromCache()
        if (visit != null) {
            val initialDescription = _binding?.petAdditionalObservations?.text.toString()
            endServicesViewModel.updateVisit(initialDescription, photoURL, visit)
        }
    }
}