package id.deval.tebu.fragment.sinder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.databinding.FragmentAddSinderBinding
import id.deval.tebu.db.Session
import id.deval.tebu.db.request.SinderRequest
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.SinderViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AddSinderFragment : Fragment() {

    private val sinderViewModel: SinderViewModel by viewModels()
    private lateinit var _binding: FragmentAddSinderBinding
    @Inject lateinit var session : Session
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddSinderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnAddsinderSave.setOnClickListener {
                val namaSinder = tietAddsinderNama.text.toString()
                val username = tietAddsinderUsername.text.toString()
                val password = tietAddsinderPassword.text.toString()
                val telepon = tietAddsinderTelepon.text.toString()
                val alamat = tietAddsinderAlamat.text.toString()
                val wilayah = mactvAddsinderWilayah.text.toString()
                val lokasi = mactvAddsinderLokasi.text.toString()

                val sinder =
                    SinderRequest(
                        namaSinder,
                        username,
                        password,
                        telepon,
                        alamat,
                        wilayah,
                        lokasi = lokasi
                    )

                Log.d("TAG", "onViewCreated: $sinder")
                sinderViewModel.addSinder(sinder, session.token.toString()).observe(viewLifecycleOwner) {
                    HelperView.showToast(it.message, requireContext()).show()
                }
            }
        }
    }

}