package co.edu.udistrital.espingsw.huellitaspelitos.ui.pet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentConfirmDeletePetBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmDeletePetFragment : Fragment() {

    private lateinit var viewModel: ConfirmDeletePetViewModel
    private lateinit var navController: NavController
    private var _binding: FragmentConfirmDeletePetBinding? = null
    private val binding get() = _binding!!
    private var petId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            petId = it.getInt(Constants.PET_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConfirmDeletePetBinding.inflate(inflater, container, false)

        val view = binding.root

        viewModel = ViewModelProvider(this)[ConfirmDeletePetViewModel::class.java]

        navController = findNavController()

        viewModel.resultDelete.observe(viewLifecycleOwner) {
            if (it == 1) {
                Snackbar.make(
                    view, "Eliminación correcta de la mascota",
                    Snackbar.LENGTH_SHORT
                ).show()
                navController.navigate(R.id.action_confirm_delete_pets_to_home)
            } else {
                Snackbar.make(
                    view, "Fallo en la eliminación de la mascota. Intentar nuevamente",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        _binding?.btnPetToDelete?.setOnClickListener {
            deletePet(petId)
        }

        return view
    }

    private fun deletePet(petId: Int){
        viewModel.deletePet(petId)
    }

}