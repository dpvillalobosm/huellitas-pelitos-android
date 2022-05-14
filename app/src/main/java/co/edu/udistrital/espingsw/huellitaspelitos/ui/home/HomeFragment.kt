package co.edu.udistrital.espingsw.huellitaspelitos.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import co.edu.udistrital.espingsw.huellitaspelitos.data.restapi.dto.PetDto
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val imagesURLs = arrayListOf<String>()
    private var userId: Int = 0
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        navController = findNavController()

        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        userId = homeViewModel.getUser()

        homeViewModel.pets.observe(viewLifecycleOwner) { petList ->
            val mViewPager = binding.viewPager
            val mViewPagerAdapter = context?.let { ViewPagerAdapter(it,
                petList as ArrayList<PetDto>, navController) }
            mViewPagerAdapter?.notifyDataSetChanged()
            mViewPager.adapter = mViewPagerAdapter
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.getPets(userId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}