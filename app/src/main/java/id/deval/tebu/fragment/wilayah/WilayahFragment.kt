package id.deval.tebu.fragment.wilayah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentWilayahBinding
import id.deval.tebu.db.Session
import id.deval.tebu.db.models.Wilayah
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.LoginViewModel
import id.deval.tebu.viewmodels.WilayahViewModel
import javax.inject.Inject

@AndroidEntryPoint
class WilayahFragment : Fragment() {
    private val wilayahViewModel : WilayahViewModel by viewModels()
    private val loginViewModel:LoginViewModel by viewModels()
    @Inject lateinit var session: Session
    private lateinit var navController: NavController
    private lateinit var _binding: FragmentWilayahBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWilayahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = HelperView.getMainNavController(requireActivity())
        with(binding){
            mtvWilayahLogout.setOnClickListener {
                loginViewModel.logout(session.id,session.token!!)
                HelperView.logout(navController, session)
            }

            btnWilayahAdd.setOnClickListener {
                navController.navigate(R.id.action_baseFragment_to_addWilayahFragment)
            }

            wilayahViewModel.getAllWilayah(session.token!!).observe(viewLifecycleOwner){
                val wilayahAdapter = WilayahAdapter(it,navController,requireActivity())
                val lm = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
                rvWilayahList.apply{
                    adapter = wilayahAdapter
                    layoutManager = lm
                }
            }
        }
    }

}