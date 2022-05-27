package co.edu.udistrital.espingsw.huellitaspelitos.ui.logout

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentLogoutBinding
import co.edu.udistrital.espingsw.huellitaspelitos.ui.login.LoginActivity

class LogoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentLogoutBinding.inflate(inflater, container, false)

        val buttonLogout = binding.btnLogout

        buttonLogout.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()
        }

        return binding.root
    }

}