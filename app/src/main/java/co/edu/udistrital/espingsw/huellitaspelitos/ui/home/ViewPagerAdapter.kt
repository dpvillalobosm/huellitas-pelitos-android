package co.edu.udistrital.espingsw.huellitaspelitos.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavController
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.ui.petgroomingservices.ServicesFragment
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
import java.util.*

internal class ViewPagerAdapter(
    var context: Context,
    var pets: ArrayList<PetDto>,
    val navController: NavController,
    val userId: Int
) : PagerAdapter() {
    // Layout Inflater
    var mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        // return the number of images
        return pets.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // inflating the item.xml
        val itemView = mLayoutInflater.inflate(R.layout.item, container, false)

        // referencing the image view from the item.xml file
        val imageView = itemView.findViewById<View>(R.id.imageViewMain) as ImageView

        //Pet selected
        val pet = pets[position]
        //URL of pet photo in AWS S3
        val petPhotoURL = pet.photo

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

        // Adding the View
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}