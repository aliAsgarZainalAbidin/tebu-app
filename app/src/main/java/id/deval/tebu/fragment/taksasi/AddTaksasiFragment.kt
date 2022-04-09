package id.deval.tebu.fragment.taksasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentAddTaksasiBinding

class AddTaksasiFragment : Fragment() {

    private lateinit var _binding : FragmentAddTaksasiBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentAddTaksasiBinding.inflate(inflater, container, false)
        return binding.root
    }

}