package co.edu.udistrital.espingsw.huellitaspelitos.ui.petgroomingservices

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.ServiceDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants

import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentServicesBinding

/**
 * [RecyclerView.Adapter] that can display a [ServiceDto].
 */
class MyServicesRecyclerViewAdapter(
    private val values: List<ServiceDto>, private val navController: NavController,
    private val petId: Int, private val userId: Int
) : RecyclerView.Adapter<MyServicesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentServicesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        val serviceIdTextView = holder.serviceId
        val serviceId = item.id.toString()
        val serviceNameTextView = holder.serviceName
        val serviceName = item.name
        val serviceAmountTextView = holder.serviceAmount
        val serviceAmount = item.baseAmount.toString()

        serviceIdTextView.text = serviceId
        serviceIdTextView.setOnClickListener {
            item.id?.let { it1 -> goToNotificationOptions(it1) }
        }

        serviceNameTextView.text = serviceName
        serviceNameTextView.setOnClickListener {
            item.id?.let { it1 -> goToNotificationOptions(it1) }
        }

        serviceAmountTextView.text = serviceAmount
        serviceAmountTextView.setOnClickListener {
            item.id?.let { it1 -> goToNotificationOptions(it1) }
        }
    }

    fun goToNotificationOptions(serviceId: Int){
        val bundle = Bundle()
        bundle.putInt(Constants.PET_ID, petId)
        bundle.putInt(Constants.USER_ID, userId)
        bundle.putInt(Constants.SERVICE_ID, serviceId)

        navController.navigate(R.id.action_services_to_notifications, bundle)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentServicesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val serviceId = binding.serviceId
        val serviceName = binding.serviceName
        val serviceAmount = binding.serviceBaseamount

        override fun toString(): String {
            return super.toString() + " '" + serviceName.text + "'"
        }
    }

}