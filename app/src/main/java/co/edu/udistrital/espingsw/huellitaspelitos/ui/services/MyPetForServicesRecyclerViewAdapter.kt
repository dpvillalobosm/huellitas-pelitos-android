package co.edu.udistrital.espingsw.huellitaspelitos.ui.services

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentPetforservicesBinding

/**
 * [RecyclerView.Adapter] that can display a [PetDto].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPetForServicesRecyclerViewAdapter(
    private val values: List<PetDto>, private val navController: NavController
) : RecyclerView.Adapter<MyPetForServicesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentPetforservicesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.name
        holder.idView.setOnClickListener {
            item.id?.let { it1 -> goToPetServicesList(it1) }
        }

    }

    private fun goToPetServicesList(petId: Int){
        val bundle = Bundle()
        bundle.putInt(Constants.PET_ID, petId)

        navController.navigate(R.id.action_services_petlist_to_services, bundle)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentPetforservicesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber

        override fun toString(): String {
            return super.toString() + " '" + idView.text + "'"
        }
    }

}