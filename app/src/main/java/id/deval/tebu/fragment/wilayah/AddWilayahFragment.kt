package id.deval.tebu.fragment.wilayah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentAddWilayahBinding
import id.deval.tebu.db.Session
import id.deval.tebu.db.response.Wilayah
import id.deval.tebu.viewmodels.RayonViewModel
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.WilayahViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AddWilayahFragment : Fragment() {

    private val wilayahViewModel: WilayahViewModel by viewModels()
    private val rayonViewModel: RayonViewModel by viewModels()

    @Inject
    lateinit var session: Session
    private lateinit var _binding: FragmentAddWilayahBinding
    private val binding get() = _binding
    private lateinit var listRayon: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddWilayahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listRayon = arrayListOf()
        with(binding) {
            ivAddwilayahBack.setOnClickListener {
                findNavController().popBackStack()
            }

            rayonViewModel.getAllRayon(session.token!!).observe(viewLifecycleOwner) {
                it.map {
                    listRayon.add("${it.nama} \\ ${it.lokasi}")
                }
                val adapterRayon = ArrayAdapter(requireContext(), R.layout.list_item, listRayon)
                adapterRayon.notifyDataSetChanged()
                mactvAddwilayahRayon.setAdapter(adapterRayon)
                mactvAddwilayahRayon.setOnItemClickListener { adapterView, view, i, l ->
                    mactvAddwilayahLokasi.setText(it[i].lokasi)
                    mactvAddwilayahRayon.error = null
                }
            }

            btnAddwilayahSave.setOnClickListener {
                val namaWilayah = tietAddwilayahWilayah.text.toString()
                val rayon = mactvAddwilayahRayon.text
                        .toString()
                        .split(" \\")[0]
                        .trim()
                val lokasi = mactvAddwilayahLokasi.text.toString()
                var allow = true

                if (namaWilayah.isEmpty()){
                    allow=false
                    tietAddwilayahWilayah.error = "Silahkan Isi Data"
                }

                if (rayon.isEmpty()){
                    allow=false
                    mactvAddwilayahRayon.error = "Silahkan Isi Data"
                }

                if(allow){
                    val wilayah = Wilayah(null, namaWilayah, rayon, lokasi)
                    wilayahViewModel.addWilayah(session.token!!, wilayah).observe(viewLifecycleOwner) {
                        HelperView.showToast(it.message, requireContext()).show()
                    }
                }
            }
        }
    }

}