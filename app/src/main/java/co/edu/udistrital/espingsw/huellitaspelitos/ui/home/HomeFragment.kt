package co.edu.udistrital.espingsw.huellitaspelitos.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import co.edu.udistrital.espingsw.huellitaspelitos.R
import co.edu.udistrital.espingsw.huellitaspelitos.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val images = intArrayOf(R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4,
        R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {
            Log.d(TAG, it)
        }

        val mViewPager = binding.viewPager
        val mViewPagerAdapter = context?.let { ViewPagerAdapter(it, images) }

        mViewPager.adapter = mViewPagerAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}