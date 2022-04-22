package id.deval.tebu.fragment.laporan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.databinding.FragmentDetailLaporanBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.Constanta
import id.deval.tebu.viewmodels.LaporanViewModel
import javax.inject.Inject

@AndroidEntryPoint
class DetailLaporanFragment : Fragment() {

    private val laporanViewModel: LaporanViewModel by viewModels()
    @Inject lateinit var session: Session
    private lateinit var _binding : FragmentDetailLaporanBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailLaporanBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(Constanta.ID_ITEM_ARGS)

        with(binding){
            laporanViewModel.getKebunBySinder(session.token!!, id.toString()).observe(viewLifecycleOwner){
                mtvLaporanNamaSinder.text = it[0].nama
                mtvLaporanWilayah.text = it[0].wilayah
                mtvLaporanLokasi.text = it[0].lokasi

                val detailAdapter = DetailLaporanAdapter(it, findNavController(), requireActivity())
                detailAdapter.notifyDataSetChanged()
                val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                rvLaporanList.apply{
                    adapter = detailAdapter
                    layoutManager = lm
                }
            }
        }

    }

}