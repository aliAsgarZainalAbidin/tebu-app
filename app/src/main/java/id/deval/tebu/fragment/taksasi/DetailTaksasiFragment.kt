package id.deval.tebu.fragment.taksasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentDetailTaksasiBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.Constanta
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.TaksasiViewModel
import javax.inject.Inject

@AndroidEntryPoint
class DetailTaksasiFragment : Fragment() {

    private val taksasiViewModel : TaksasiViewModel by viewModels()
    @Inject lateinit var session : Session
    private lateinit var _binding : FragmentDetailTaksasiBinding
    private lateinit var navController: NavController
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentDetailTaksasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = HelperView.getMainNavController(requireActivity())
        val id = arguments?.getString(Constanta.ID_ITEM_ARGS)
        with(binding){
            taksasiViewModel.getTaksasiById(session.token!!,id!!).observe(viewLifecycleOwner){
                val taksasi = it[0]
                mtvTaksasiGreeting.text = "Mandor : ${taksasi.mandor}"
                mtvDetailtaksasiKebun.text = taksasi.namaKebun
                mtvDetailtaksasiPtk.text = taksasi.petak
                mtvDetailtaksasiLuas.text = taksasi.luas
                mtvDetailtaksasiJenis.text = taksasi.jenisTebu
                mtvDetailtaksasiKtg.text = taksasi.kategori
                mtvDetailtaksasiFaktor.text = taksasi.faktorLeng
                mtvDetailtaksasiJmlBpm.text = taksasi.batangPerMeter
                mtvDetailtaksasiJmlBpr.text = taksasi.batangPerRow
                mtvDetailtaksasiJmlBph.text = taksasi.batangPerHa
                mtvDetailtaksasiIni.text = taksasi.tinggiIni
                mtvDetailtaksasiTebang.text = taksasi.tinggiTebang
                mtvDetailtaksasiDiameter.text = taksasi.diameterBatang
                mtvDetailtaksasiBerat.text = taksasi.beratPerMeter
                mtvDetailtaksasiHit.text = taksasi.hit
                mtvDetailtaksasiPandangan.text = taksasi.pandangan
                mtvDetailtaksasiPerhit.text = taksasi.perHit
                mtvDetailtaksasiKui.text = taksasi.kui
            }
        }
    }

}