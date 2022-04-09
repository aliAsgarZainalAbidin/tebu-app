package id.deval.tebu.fragment.taksasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentDetailTaksasiBinding

class DetailTaksasiFragment : Fragment() {

    private lateinit var _binding : FragmentDetailTaksasiBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentDetailTaksasiBinding.inflate(inflater, container, false)
        return binding.root
    }

}