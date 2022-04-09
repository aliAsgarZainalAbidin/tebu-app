package id.deval.tebu.fragment.rayon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentRayonBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.LoginViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RayonFragment : Fragment() {

    private val loginViewModel : LoginViewModel by viewModels()
    private lateinit var _binding: FragmentRayonBinding
    private val binding get() = _binding
    private lateinit var navController: NavController
    @Inject lateinit var session: Session

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRayonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = activity?.findNavController(R.id.fcv_main_host)!!
//        navController = childFragmentManager.findFragmentById(R.id.fcv_base_host)?.findNavController() ?: findNavController()
        with(binding){
            mtvRayonLogout.setOnClickListener {
                loginViewModel.logout(session.id,session.token!!)
                HelperView.logout(navController, session)
            }
        }
    }

}