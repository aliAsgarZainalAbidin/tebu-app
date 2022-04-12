package id.deval.tebu.fragment.kebun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.databinding.FragmentAddKebunBinding
import id.deval.tebu.db.Session
import id.deval.tebu.db.response.Kebun
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.KebunViewModel
import id.deval.tebu.viewmodels.SinderViewModel
import id.deval.tebu.viewmodels.WilayahViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AddKebunFragment : Fragment() {

    private val kebunViewModel : KebunViewModel by viewModels()
    private val sinderViewModel : SinderViewModel by viewModels()
    private val wilayahViewModel : WilayahViewModel by viewModels()
    @Inject lateinit var session: Session
    private lateinit var _binding : FragmentAddKebunBinding
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
        with(binding){
            btnAddkebunSave.setOnClickListener {
                val namaKebun = tietAddkebunNama.text.toString()
                val luas = tietAddkebunLuas.text.toString()
                val petak = tietAddkebunPetak.text.toString()
                val jenisTebu = tietAddkebunJenis.text.toString()
                val kategori = tietAddkebunKategori.text.toString()
                val namaSinder = mactvAddkebunSinder.text.toString()
                val wilayah = mactvAddkebunWilayah.text.toString()

                val kebun = Kebun(namaKebun, luas, petak, jenisTebu, kategori, namaSinder, wilayah)
                kebunViewModel.addKebun(session.token!!,kebun).observe(viewLifecycleOwner){
                    HelperView.showToast(it.message,requireContext()).show()
                }
            }
        }
    }

}