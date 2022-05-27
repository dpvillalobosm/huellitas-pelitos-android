package co.edu.udistrital.espingsw.huellitaspelitos.ui.notifications

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentNotificationOptionsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationOptionsFragment : Fragment() {

    private lateinit var notificationsOptionsViewModel: NotificationsOptionsViewModel
    private var _binding: FragmentNotificationOptionsBinding? = null
    private val binding get() = _binding!!
    private var userId = 0
    private var petId = 0
    private var serviceId = 0
    private var uuid: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userId = it.getInt(Constants.USER_ID)
            petId = it.getInt(Constants.PET_ID)
            serviceId = it.getInt(Constants.SERVICE_ID)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationOptionsBinding.inflate(inflater, container, false)

        notificationsOptionsViewModel =
            ViewModelProvider(this)[NotificationsOptionsViewModel::class.java]

        val user = notificationsOptionsViewModel.getLoggedInUser()

        val emailTextView = binding.notificationEmail
        val addressTextView = binding.notificationAddress
        val phoneTextView = binding.notificationPhone

        if (user != null) {
            emailTextView.setText(user.email, TextView.BufferType.EDITABLE)
            addressTextView.setText(user.address, TextView.BufferType.EDITABLE)
            phoneTextView.setText(user.phone.toString(), TextView.BufferType.EDITABLE)
        }

        val continueButton = binding.notificationContinue

        continueButton.setOnClickListener {
            //Persistir información de la visita,
            createVisit(serviceId, userId, petId)
        }

        notificationsOptionsViewModel.goToVisitCreated.observe(viewLifecycleOwner, Observer {
            if(it){
                uuid = notificationsOptionsViewModel.uuid.toString()

                val bundle = Bundle()
                bundle.putString(Constants.UUID, uuid)
                findNavController().navigate(R.id.action_notification_to_visit_created, bundle)
            } else {
                Snackbar.make(binding.root, "Error al crear la visita",
                    Snackbar.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createVisit(serviceId: Int, userId: Int, petId: Int){
        notificationsOptionsViewModel.createVisit(serviceId, userId, petId)
    }
}