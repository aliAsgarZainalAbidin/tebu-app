package id.deval.tebu.fragment.kebun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.constraintlayout.solver.widgets.Helper
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentKebunBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.KebunViewModel
import id.deval.tebu.viewmodels.LoginViewModel
import id.deval.tebu.viewmodels.SinderViewModel
import javax.inject.Inject


@AndroidEntryPoint
class KebunFragment : Fragment() {

    private val loginViewModel : LoginViewModel by viewModels()
    private val kebunViewModel : KebunViewModel by viewModels()
    private lateinit var navController: NavController
    @Inject lateinit var session : Session
    private lateinit var _binding: FragmentKebunBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKebunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = HelperView.getMainNavController(requireActivity())

        with(binding){
            mtvKebunLogout.setOnClickListener {
                loginViewModel.logout(session.id!!,session.token!!)
                HelperView.logout(navController, session)
            }

            btnKebunAdd.setOnClickListener {
                navController.navigate(R.id.action_baseFragment_to_addKebunFragment)
            }

            kebunViewModel.getAllKebun(session.token!!).observe(viewLifecycleOwner){
                val adapterKebun = KebunAdapter(it,navController,requireActivity())
                val lm = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                rvKebunList.apply {
                    adapter = adapterKebun
                    layoutManager = lm
                }
            }

        }
    }

}