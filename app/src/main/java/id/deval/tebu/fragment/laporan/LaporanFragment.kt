package id.deval.tebu.fragment.laporan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.deval.tebu.databinding.FragmentLaporanBinding

class LaporanFragment : Fragment() {

    private lateinit var _binding : FragmentLaporanBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

}