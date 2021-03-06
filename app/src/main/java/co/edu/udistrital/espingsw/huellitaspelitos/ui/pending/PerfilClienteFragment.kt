package co.edu.udistrital.espingsw.huellitaspelitos.ui.pending

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.edu.udistrital.espingsw.huellitaspelitos.R

class PerfilClienteFragment : Fragment() {

    companion object {
        fun newInstance() = PerfilClienteFragment()
    }

    private lateinit var viewModel: PerfilClienteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.perfil_cliente_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PerfilClienteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}