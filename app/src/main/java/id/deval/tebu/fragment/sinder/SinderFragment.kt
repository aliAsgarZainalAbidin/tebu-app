package id.deval.tebu.fragment.sinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.deval.tebu.databinding.FragmentSinderBinding

class SinderFragment : Fragment() {

    private lateinit var _binding : FragmentSinderBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSinderBinding.inflate(inflater, container, false)
        return binding.root
    }
}