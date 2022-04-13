package id.deval.tebu.fragment.laporan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentLaporanBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.LoginViewModel
import javax.inject.Inject

@AndroidEntryPoint
class LaporanFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var _binding : FragmentLaporanBinding
    private val binding get() = _binding
    @Inject lateinit var session: Session

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = HelperView.getMainNavController(requireActivity())
        with(binding){
            mtvLaporanLogout.setOnClickListener {
                loginViewModel.logout(session.id!!,session.token!!)
                HelperView.logout(navController, session)
            }
        }
    }

}