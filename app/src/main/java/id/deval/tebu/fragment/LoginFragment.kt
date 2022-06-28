package id.deval.tebu.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentLoginBinding
import id.deval.tebu.db.request.LoginRequest
import id.deval.tebu.db.Session
import id.deval.tebu.db.response.User
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.LoginViewModel
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var userLogin: User
    private var enable = true
    private val binding get() = _binding

    @Inject
    lateinit var session: Session

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        Log.d("TAG", "onViewCreated: ${session.pref.all}")
        with(binding) {
            if (!session.role.isNullOrEmpty()) {
                navigateToMainMenu(session.role!!)
            }

            btnLoginLogin.setOnClickListener {
                val username = tietLoginEmail.text.toString().trim()
                val password = tietLoginPassword.text.toString().trim()
                if (username.isEmpty() || password.isEmpty()){
                    enable = false
                    HelperView.showToast("Silahkan Isi Username/Password terlebih dahulu", requireContext()).show()
                }
                if (enable){
                    val loginRequest = LoginRequest(username, password)
                    loginViewModel.login(loginRequest).observe(viewLifecycleOwner) {
                        if (it != null){
                            userLogin =it
                            session.createLoginSession(userLogin)
                            navigateToMainMenu(userLogin.role)
                            Log.d(TAG, "onViewCreated: ${it.token}")
                        } else {
                            HelperView.showToast("Failed to Login", requireContext()).show()
                        }
                    }
                }
            }
        }
    }

    fun navigateToMainMenu(role:String) {
        when (role) {
            "admin" -> {
                navController.navigate(R.id.action_loginFragment_to_baseFragment)
            }
            "sinder" -> {
                navController.navigate(R.id.action_loginFragment_to_taksasiFragment)
            }
        }
    }

}