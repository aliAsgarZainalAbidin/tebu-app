package id.deval.tebu.fragment.kebun

import android.os.Bundle
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
import id.deval.tebu.databinding.FragmentAddKebunBinding
import id.deval.tebu.db.Session
import id.deval.tebu.db.response.Kebun
import id.deval.tebu.utils.Constanta
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.KebunViewModel
import id.deval.tebu.viewmodels.SinderViewModel
import id.deval.tebu.viewmodels.WilayahViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AddKebunFragment : Fragment() {

    private val kebunViewModel: KebunViewModel by viewModels()
    private val sinderViewModel: SinderViewModel by viewModels()
    private val wilayahViewModel: WilayahViewModel by viewModels()
    @Inject
    lateinit var session: Session
    private lateinit var listSinder: ArrayList<String>
    private lateinit var _binding: FragmentAddKebunBinding
    private lateinit var navController: NavController
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddKebunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listSinder = arrayListOf()
        navController = HelperView.getMainNavController(requireActivity())
        val id = arguments?.getString(Constanta.ID_ITEM_ARGS) ?: ""

        with(binding) {
            ivAddkebunBack.setOnClickListener {
                findNavController().popBackStack()
            }

            if (!id.isNullOrEmpty()) {
                kebunViewModel.getKebunById(session.token!!, id).observe(viewLifecycleOwner) {
                    with(it.data.kebun){
                        tietAddkebunNama.setText(this.namaKebun)
                        tietAddkebunLuas.setText(this.luas)
                        tietAddkebunPetak.setText(this.petak)
                        tietAddkebunJenis.setText(this.jenisTebu)
                        tietAddkebunKategori.setText(this.kategori)
                        mactvAddkebunSinder.setText(this.namaSinder, false)
                        mactvAddkebunWilayah.setText(this.wilayah, false)
                    }
                }
            }

            btnAddkebunSave.setOnClickListener {
                val namaKebun = tietAddkebunNama.text.toString()
                val luas = tietAddkebunLuas.text.toString()
                val petak = tietAddkebunPetak.text.toString()
                val jenisTebu = tietAddkebunJenis.text.toString()
                val kategori = tietAddkebunKategori.text.toString()
                val namaSinder = mactvAddkebunSinder.text.toString()
                    .split("\\")[0]
                    .trim()
                val wilayah = mactvAddkebunWilayah.text.toString()
                    .split("\\")[0]
                    .trim()
                var allow = true

                if (namaKebun.isEmpty()) {
                    allow = false
                    tietAddkebunNama.error = "Silahkan Isi Data"
                }
                if (luas.isEmpty()) {
                    allow = false
                    tietAddkebunLuas.error = "Silahkan Isi Data"
                }
                if (petak.isEmpty()) {
                    allow = false
                    tietAddkebunPetak.error = "Silahkan Isi Data"
                }
                if (jenisTebu.isEmpty()) {
                    allow = false
                    tietAddkebunJenis.error = "Silahkan Isi Data"
                }
                if (kategori.isEmpty()) {
                    allow = false
                    tietAddkebunKategori.error = "Silahkan Isi Data"
                }
                if (namaSinder.isEmpty()) {
                    allow = false
                    mactvAddkebunSinder.error = "Silahkan Isi Data"
                }
                if (jenisTebu.isEmpty()) {
                    allow = false
                    tietAddkebunJenis.error = "Silahkan Isi Data"
                }

                if (allow) {
                    val kebun =
                        Kebun(id, namaKebun, luas, petak, jenisTebu, kategori, namaSinder, wilayah)
                    if (id.isNullOrEmpty()) {
                        kebunViewModel.addKebun(session.token!!, kebun)
                            .observe(viewLifecycleOwner) {
                                HelperView.showToast(it.message, requireContext()).show()
                            }
                    } else {
                        kebunViewModel.updateKebun(session.token!!,kebun,id).observe(viewLifecycleOwner){
                            HelperView.showToast("Data Berhasil Terupdate",requireContext()).show()
                            navController.popBackStack()
                        }
                    }
                }

            }

            sinderViewModel.getAllSinder(session.token!!).observe(viewLifecycleOwner) {
                it.data.sinder.map {
                    listSinder.add("${it.nama}\\${it.wilayah}")
                }
                val adapterUser = ArrayAdapter(requireContext(), R.layout.list_item, listSinder)
                mactvAddkebunSinder.setAdapter(adapterUser)
                mactvAddkebunSinder.setOnItemClickListener { adapterView, view, i, l ->
                    mactvAddkebunWilayah.setText(it.data.sinder[i].wilayah)
                    mactvAddkebunSinder.error = null
                }
            }
        }
    }

}