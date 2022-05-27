package co.edu.udistrital.espingsw.huellitaspelitos.ui.pet

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentPetBinding

/**
 * [RecyclerView.Adapter] that can display a [PetDto].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPetRecyclerViewAdapter(
    private val values: List<PetDto>, private val navController: NavController
) : RecyclerView.Adapter<MyPetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentPetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        val petNameTextView = holder.contentView
        val petName = item.name
        petNameTextView.text = petName
        petNameTextView.setOnClickListener {
            item.id?.let { it1 -> goToPetDetail(it1) }
        }

        val deleteButton = holder.deleteButton
        deleteButton.setOnClickListener {
            item.id?.let { it1 -> deletePet(it1) }
        }
    }


    private fun goToPetDetail(petId: Int){
        val bundle = Bundle()
        bundle.putInt(Constants.PET_ID, petId)

        navController.navigate(R.id.action_pet_list_to_pet_details, bundle)
    }

    private fun deletePet(petId: Int) {
        Log.d("msg", "deleting pet: $petId")
        val bundle = Bundle()
        bundle.putInt(Constants.PET_ID, petId)

        navController.navigate(R.id.action_pet_list_to_confirm_delete, bundle)
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentPetBinding) : RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.txtPetNameList
        val editButton: Button = binding.btnEditPet
        val deleteButton: Button = binding.btnDeletePet

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}