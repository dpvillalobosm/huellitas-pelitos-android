package co.edu.udistrital.espingsw.huellitaspelitos.ui.petgroomingservices

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.ServiceDto
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class ServicesFragment : Fragment() {

    private var columnCount = 1
    private var petId = 0
    private var userId = 0
    private var servicesList = arrayListOf<ServiceDto>()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            petId = it.getInt(Constants.PET_ID)
            userId = it.getInt(Constants.USER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_services_list, container, false)

        navController = findNavController()
        Log.d("ServicesFragment", "ID of Pet: $petId")

        val servicesViewModel = ViewModelProvider(this)[ServicesViewModel::class.java]

        servicesViewModel.services.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                servicesList = it as ArrayList<ServiceDto>

                if (view is RecyclerView) {
                    with(view) {
                        layoutManager = when {
                            columnCount <= 1 -> LinearLayoutManager(context)
                            else -> GridLayoutManager(context, columnCount)
                        }
                        adapter = MyServicesRecyclerViewAdapter(servicesList, navController, petId,
                            userId)
                    }
                }
            } else {
                //Hacer algo con la lista vacía
            }
        })

        return view
    }

    override fun onStart() {
        super.onStart()

        val servicesViewModel = ViewModelProvider(this)[ServicesViewModel::class.java]
        servicesViewModel.getAllServices()
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ServicesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}