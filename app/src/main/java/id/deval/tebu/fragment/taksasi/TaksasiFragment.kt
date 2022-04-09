package id.deval.tebu.fragment.taksasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentTaksasiBinding

class TaksasiFragment : Fragment() {

    private lateinit var _binding : FragmentTaksasiBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaksasiBinding.inflate(inflater,container,false)
        return binding.root
    }

}