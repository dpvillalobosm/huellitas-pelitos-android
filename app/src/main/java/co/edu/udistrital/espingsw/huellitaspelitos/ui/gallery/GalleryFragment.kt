package co.edu.udistrital.espingsw.huellitaspelitos.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val user = galleryViewModel.getLoggedInUser()

        val emailTextView = binding.notificationEmail
        val addressTextView = binding.notificationAddress
        val phoneTextView = binding.notificationPhone

        if (user != null) {
            emailTextView.setText(user.email, TextView.BufferType.EDITABLE)
            addressTextView.setText(user.address, TextView.BufferType.EDITABLE)
            phoneTextView.setText(user.phone.toString(), TextView.BufferType.EDITABLE)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}