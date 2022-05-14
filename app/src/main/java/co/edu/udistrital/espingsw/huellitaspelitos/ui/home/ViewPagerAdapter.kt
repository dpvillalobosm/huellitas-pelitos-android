package co.edu.udistrital.espingsw.huellitaspelitos.ui.home

import android.content.Context
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
import com.bumptech.glide.Glide
import java.util.*

internal class ViewPagerAdapter(
    var context: Context,
    var pets: ArrayList<PetDto>,
    val navController: NavController
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

        // setting the image in the imageView
        Glide.with(context).load(pets[position].photo).fitCenter().into(imageView)

        //Go to specific info for pet selected
        imageView.setOnClickListener {

        }

        // Adding the View
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}