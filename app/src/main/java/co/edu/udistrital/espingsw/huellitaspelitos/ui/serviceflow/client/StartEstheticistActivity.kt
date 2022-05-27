package co.edu.udistrital.espingsw.huellitaspelitos.ui.serviceflow.client

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.ActivityStartEstheticistBinding
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.bumptech.glide.Glide
import java.io.File
import java.lang.Exception

class StartEstheticistActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityStartEstheticistBinding
    private val binding get() = _binding!!
    private var photo: String? = null
    private var visitId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        photo = intent.getStringExtra(Constants.PHOTO)
        visitId = intent.getIntExtra(Constants.VISIT_ID, 0)

        _binding = ActivityStartEstheticistBinding.inflate(layoutInflater)

        val petPhotoImageView = _binding.petPhoto

        val context = applicationContext

        photo?.let {
            getPhotoFromRemote(context, petPhotoImageView, it)
        }

        setContentView(binding.root)
    }

    private fun getPhotoFromRemote(context: Context, imageView: ImageView, petPhotoURL: String){
        //Connection to AWS S3
        var basicAWSCredentials = BasicAWSCredentials(Constants.ACCESS_ID,
            Constants.SECRET_KEY)
        var s3Client = AmazonS3Client(basicAWSCredentials, Region.getRegion(Regions.US_EAST_1))

        var file = File.createTempFile("image", petPhotoURL)

        val trans = TransferUtility.builder().context(context).s3Client(s3Client).build()
        val observer: TransferObserver = trans.download(Constants.BUCKET_NAME, petPhotoURL, file)
        observer.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState?) {
                if (state == TransferState.COMPLETED) {
                    // setting the image in the imageView
                    Glide.with(context).load(file).fitCenter().into(imageView)
                    Log.d("msg","success")
                } else if (state == TransferState.FAILED) {
                    Log.d("msg","fail")
                }
            }

            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {
                if(bytesCurrent == bytesTotal){
                    Log.d("msg", "bytescurrent equals bytestotal")
                }
            }

            override fun onError(id: Int, ex: Exception?) {
                Log.d("error",ex.toString())
            }
        })
    }

}