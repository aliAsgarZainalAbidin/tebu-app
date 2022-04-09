package id.deval.tebu.fragment.wilayah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.deval.tebu.databinding.FragmentAddWilayahBinding

class AddWilayahFragment : Fragment() {

    private lateinit var _binding : FragmentAddWilayahBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddWilayahBinding.inflate(inflater, container, false)
        return binding.root
    }

}