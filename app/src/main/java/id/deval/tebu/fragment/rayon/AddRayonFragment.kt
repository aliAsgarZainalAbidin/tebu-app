package id.deval.tebu.fragment.rayon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentAddRayonBinding

class AddRayonFragment : Fragment() {

    private lateinit var _binding: FragmentAddRayonBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRayonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemsLokasi = listOf("Takalar", "Jeneponto")

        with(binding) {
            val adapterLokasi = ArrayAdapter(requireContext(), R.layout.list_item, itemsLokasi)
            tietAddrayonLokasi.setAdapter(adapterLokasi)

            btnAddrayonSave.setOnClickListener {

            }
        }
    }


}