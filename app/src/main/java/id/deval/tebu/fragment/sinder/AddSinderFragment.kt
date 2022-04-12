package id.deval.tebu.fragment.sinder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentAddSinderBinding
import id.deval.tebu.db.Session
import id.deval.tebu.db.request.SinderRequest
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.SinderViewModel
import id.deval.tebu.viewmodels.WilayahViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AddSinderFragment : Fragment() {

    private val sinderViewModel: SinderViewModel by viewModels()
    private val wilayahViewModel: WilayahViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var listWilayah: ArrayList<String>
    private lateinit var _binding: FragmentAddSinderBinding
    @Inject
    lateinit var session: Session
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
        navController = findNavController()
        listWilayah = arrayListOf()

        with(binding) {
            btnAddsinderSave.setOnClickListener {
                val namaSinder = tietAddsinderNama.text.toString()
                val username = tietAddsinderUsername.text.toString()
                val password = tietAddsinderPassword.text.toString()
                val telepon = tietAddsinderTelepon.text.toString()
                val alamat = tietAddsinderAlamat.text.toString()
                val wilayah = mactvAddsinderWilayah.text.toString()
                    .split("\\")[2]
                    .trim()
                val lokasi = mactvAddsinderLokasi.text.toString()
                var allow = true
                if (namaSinder.isEmpty()) {
                    allow = false
                    tietAddsinderNama.error = "Silahkan isi data"
                }

                if (username.isEmpty()) {
                    allow = false
                    tietAddsinderUsername.error = "Silahkan isi data"
                }
                if (password.isEmpty()||password.length>=8) {
                    allow = false
                    tietAddsinderPassword.error = "Silahkan isi data minimal 8 karakter"
                }

                if (telepon.isEmpty()) {
                    allow = false
                    tietAddsinderTelepon.error = "Silahkan isi data"
                }
                if (alamat.isEmpty()) {
                    allow = false
                    tietAddsinderAlamat.error = "Silahkan isi data"
                }
                if (wilayah.isEmpty()) {
                    allow = false
                    mactvAddsinderWilayah.error = "Silahkan isi data"
                }
                if (allow) {
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

                    sinderViewModel.addSinder(sinder, session.token.toString())
                        .observe(viewLifecycleOwner) {
                            HelperView.showToast(it.message, requireContext()).show()
                        }
                }
            }

            wilayahViewModel.getAllWilayah(session.token.toString()).observe(viewLifecycleOwner) {
                it.map {
                    listWilayah.add("${it.wilayah}\\${it.rayon}\\${it.lokasi}")
                }
                val adapterWilayah = ArrayAdapter(requireContext(), R.layout.list_item, listWilayah)
                mactvAddsinderWilayah.setAdapter(adapterWilayah)
                mactvAddsinderWilayah.setOnItemClickListener { adapterView, view, i, l ->
                    mactvAddsinderLokasi.setText(it[i].lokasi)
                    mactvAddsinderWilayah.error = null
                }
            }


        }
    }

}