package co.edu.udistrital.espingsw.huellitaspelitos.ui.visit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import co.edu.udistrital.espingsw.huellitaspelitos.data.util.Constants
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentVisitCreatedBinding

class VisitCreatedFragment : Fragment() {

    private lateinit var viewModel: VisitCreatedViewModel
    private var _binding: FragmentVisitCreatedBinding? = null
    private val binding get() = _binding!!
    private lateinit var uuid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            uuid = it?.getString(Constants.UUID).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVisitCreatedBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[VisitCreatedViewModel::class.java]

        val confirmationCode = _binding?.txtVerificationCode

        confirmationCode?.text = uuid

        return binding.root
    }

}