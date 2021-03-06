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
        val petak = arguments?.getString(Constanta.PETAK)
        val jenis = arguments?.getString(Constanta.JENIS)
        val kategori = arguments?.getString(Constanta.KATEGORI)
        with(binding){
            taksasiViewModel.getTaksasiById(session.token!!,id!!).observe(viewLifecycleOwner){
                val taksasi = it.data.taksasi[0]
                mtvTaksasiGreeting.text = "Mandor : ${taksasi.mandor}"
                mtvDetailtaksasiKebun.text = taksasi.namaKebun
                mtvDetailtaksasiPtk.text = "$petak petak"
                mtvDetailtaksasiLuas.text = "${taksasi.luas} ha"
                mtvDetailtaksasiJenis.text = jenis
                mtvDetailtaksasiKtg.text = kategori
                mtvDetailtaksasiFaktor.text = taksasi.faktorLeng
                mtvDetailtaksasiJmlBpm.text = "${taksasi.batangPerMeter} batang"
                mtvDetailtaksasiJmlBpr.text = "${taksasi.batangPerRow} batang"
                mtvDetailtaksasiJmlBph.text = "${taksasi.batangPerHa} batang"
                mtvDetailtaksasiIni.text = "${taksasi.tinggiIni} m"
                mtvDetailtaksasiTebang.text = "${taksasi.tinggiTebang} m"
                mtvDetailtaksasiDiameter.text = "${taksasi.diameterBatang} cm"
                mtvDetailtaksasiBerat.text = "${taksasi.beratPerMeter} Kg"
                mtvDetailtaksasiHit.text = "${taksasi.hit} ton/ha"
                mtvDetailtaksasiPandangan.text = "${taksasi.pandangan} ton/ha"
                mtvDetailtaksasiPerhit.text = "${taksasi.perHit} ton"
                mtvDetailtaksasiKui.text = "${taksasi.kui} kwintal"
            }
        }
    }

}