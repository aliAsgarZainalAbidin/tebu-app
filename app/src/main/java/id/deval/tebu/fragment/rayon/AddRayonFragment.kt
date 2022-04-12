package id.deval.tebu.fragment.rayon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentAddRayonBinding
import id.deval.tebu.db.Session
import id.deval.tebu.db.request.RayonRequest
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.RayonViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AddRayonFragment : Fragment() {

    private val rayonViewModel: RayonViewModel by viewModels()
    @Inject lateinit var session : Session
    private lateinit var _binding: FragmentAddRayonBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRayonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemsLokasi = listOf("Takalar", "Jeneponto")

        with(binding) {
            val adapterLokasi = ArrayAdapter(requireContext(), R.layout.list_item, itemsLokasi)
            tietAddrayonLokasi.setAdapter(adapterLokasi)

            btnAddrayonSave.setOnClickListener {
                val namaRayon = tietAddrayonNama.text.toString()
                val lokasi = tietAddrayonLokasi.text.toString()
                var allow = true
                if (namaRayon.isEmpty()){
                    allow = false
                    tietAddrayonNama.error = "Silahkan isi Data"

                }
                if (lokasi.isEmpty()){
                    allow= false
                    tietAddrayonLokasi.error = "Silahkan isi Data"
                }
                if(allow){
                    val rayonRequest = RayonRequest(namaRayon, lokasi)
                    rayonViewModel.addRayon(rayonRequest,session.token!!).observe(viewLifecycleOwner){
                        HelperView.showToast(it.message, requireContext()).show()
                    }
                }
            }
        }
    }


}