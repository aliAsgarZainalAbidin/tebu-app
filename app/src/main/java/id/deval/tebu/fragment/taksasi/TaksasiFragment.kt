package id.deval.tebu.fragment.taksasi

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
import id.deval.tebu.databinding.FragmentTaksasiBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.LoginViewModel
import id.deval.tebu.viewmodels.TaksasiViewModel
import javax.inject.Inject

@AndroidEntryPoint
class TaksasiFragment : Fragment() {

    private val taksasiViewModel : TaksasiViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var navController: NavController
    @Inject lateinit var session : Session
    private lateinit var _binding : FragmentTaksasiBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaksasiBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = HelperView.getMainNavController(requireActivity())

        with(binding){
            taksasiViewModel.getTaksasiByUser(session.token!!).observe(viewLifecycleOwner){
                if (it != null){
                    val taksasiAdapter = TaksasiAdapter(it.data.taksasi, navController, requireActivity())
                    val lm = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
                    rvTaksasiList.apply{
                        adapter = taksasiAdapter
                        layoutManager = lm
                    }

                    mtvTaksasiNamaSinder.text = session.nama
                    mtvTaksasiTaksasi.text = session.wilayah
                    mtvTaksasiLokasi.text = session.lokasi
                } else {
                    HelperView.showToast("Silahkan Login Kembali", requireContext()).show()
                    HelperView.logout(navController, session)
                }
            }

            mtvTaksasiLogout.setOnClickListener {
                loginViewModel.logout(session.id!!, session.token!!)
                HelperView.logout(navController, session)
            }
        }
    }

}