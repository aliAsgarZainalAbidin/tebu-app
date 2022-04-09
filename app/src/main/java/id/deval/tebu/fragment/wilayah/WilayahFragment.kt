package id.deval.tebu.fragment.wilayah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.databinding.FragmentWilayahBinding
import id.deval.tebu.db.models.Wilayah
import id.deval.tebu.viewmodels.WilayahViewModel

@AndroidEntryPoint
class WilayahFragment : Fragment() {
    private val wilayahViewModel : id.deval.tebu.viewmodels.WilayahViewModel by viewModels()

    private lateinit var _binding: FragmentWilayahBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWilayahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWilayahAdd.setOnClickListener {
            val wilayah = Wilayah(null,"r1w1","Rayon 1 A", "Rayon 1","Takalar")
            wilayahViewModel.save(wilayah)
        }
    }

}