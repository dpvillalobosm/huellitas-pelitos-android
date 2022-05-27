package co.edu.udistrital.espingsw.huellitaspelitos.ui.pet

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentPetBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class PetFragment : Fragment() {

    private var columnCount = 1
    private lateinit var navController: NavController
    private var petList = arrayListOf<PetDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet_list, container, false)

        navController = findNavController()

        val viewModel = ViewModelProvider(this)[PetViewModel::class.java]

        val rV = view.findViewById<RecyclerView>(R.id.rv_list)

        viewModel.pets.observe(viewLifecycleOwner, Observer {
            petList = it as ArrayList<PetDto>

            if (rV is RecyclerView) {
                with(rV) {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context)
                        else -> GridLayoutManager(context, columnCount)
                    }
                    adapter = MyPetRecyclerViewAdapter(petList, navController)
                }
            }
        })

        val btnGoToCreatePet = view.findViewById<Button>(R.id.btn_go_to_add_pet)
        btnGoToCreatePet.setOnClickListener {
            goToCreatePet()
        }

        return view
    }

    fun goToCreatePet(){
        val bundle = Bundle()
        val userId = petList[0].idUser?.id
        if (userId != null) {
            bundle.putInt(Constants.USER_ID, userId)
        }
        navController.navigate(R.id.action_pet_list_to_add_pet, bundle)
    }

    override fun onStart() {
        super.onStart()

        val viewModel = ViewModelProvider(this)[PetViewModel::class.java]

        viewModel.getAllPetsByUser()
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            PetFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}