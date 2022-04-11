package id.deval.tebu.fragment.sinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentSinderBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.LoginViewModel
import id.deval.tebu.viewmodels.SinderViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SinderFragment : Fragment() {

    private val sinderViewModel: SinderViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    @Inject
    lateinit var session: Session
    private lateinit var navController: NavController
    private lateinit var _binding: FragmentSinderBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSinderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = HelperView.getMainNavController(requireActivity())

        with(binding) {
            btnSinderAdd.setOnClickListener {
                navController.navigate(R.id.action_baseFragment_to_addSinderFragment2)
            }

            mtvSinderLogout.setOnClickListener {
                loginViewModel.logout(session.id, session.token!!)
                HelperView.logout(navController, session)
            }

            sinderViewModel.getAllSinder(session.token.toString()).observe(viewLifecycleOwner) {
                val sinderAdapter = SinderAdapter(it, navController, requireActivity())
                val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                rvSinderList.apply {
                    adapter = sinderAdapter
                    layoutManager = lm
                }
            }
        }
    }
}