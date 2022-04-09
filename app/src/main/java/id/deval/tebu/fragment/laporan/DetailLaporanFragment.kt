package id.deval.tebu.fragment.laporan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.deval.tebu.databinding.FragmentDetailLaporanBinding

class DetailLaporanFragment : Fragment() {

    private lateinit var _binding : FragmentDetailLaporanBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailLaporanBinding.inflate(inflater,container, false)
        return binding.root
    }

}