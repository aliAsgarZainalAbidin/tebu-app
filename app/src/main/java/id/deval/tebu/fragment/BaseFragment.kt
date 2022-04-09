package id.deval.tebu.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationBarView
import id.deval.tebu.R
import id.deval.tebu.activity.MainActivity
import id.deval.tebu.databinding.FragmentBaseBinding

class BaseFragment : Fragment() {

    private lateinit var _binding: FragmentBaseBinding
    private val binding get() = _binding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = childFragmentManager.findFragmentById(R.id.fcv_base_host)?.findNavController() ?: findNavController()
        Log.d("TAG", "onViewCreated: ${navController.graph}")

        navController.popBackStack(navController.currentDestination?.id ?: R.id.rayonFragment, true)
        binding.bnvBaseContainer.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.rayonFragment -> {
                    navController.navigate(R.id.rayonFragment)
                    true
                }
                R.id.wilayahFragment -> {
                    navController.navigate(R.id.wilayahFragment)
                    true
                }
                R.id.laporanFragment -> {
                    navController.navigate(R.id.laporanFragment)
                    true
                }
                R.id.sinderFragment -> {
                    navController.navigate(R.id.sinderFragment)
                    true
                }
                R.id.kebunFragment -> {
                    navController.navigate(R.id.kebunFragment)
                    true
                }
                else -> false
            }
        }
    }
}