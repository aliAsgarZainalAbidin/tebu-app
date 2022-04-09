package id.deval.tebu.fragment.kebun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.deval.tebu.databinding.FragmentAddKebunBinding

class AddKebunFragment : Fragment() {

    private lateinit var _binding : FragmentAddKebunBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddKebunBinding.inflate(inflater, container, false)
        return binding.root
    }

}