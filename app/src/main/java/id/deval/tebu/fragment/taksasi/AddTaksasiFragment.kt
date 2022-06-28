package id.deval.tebu.fragment.taksasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentAddTaksasiBinding
import id.deval.tebu.db.Session
import id.deval.tebu.db.request.TaksasiRequest
import id.deval.tebu.db.response.Taksasi
import id.deval.tebu.utils.Constanta
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.TaksasiViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AddTaksasiFragment : Fragment() {

    private val taksasiViewModel: TaksasiViewModel by viewModels()
    @Inject
    lateinit var session: Session
    private lateinit var navController: NavController
    private lateinit var _binding: FragmentAddTaksasiBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaksasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = HelperView.getMainNavController(requireActivity())
        val id = arguments?.getString(Constanta.ID_ITEM_ARGS)

        with(binding) {
            taksasiViewModel.getTaksasiById(session.token!!, id!!).observe(viewLifecycleOwner) {
                val taksasi = it.data.taksasi[0]
                tietAddtaksasiNama.setText(taksasi.namaKebun)
                tietAddtaksasiLuas.setText(taksasi.luas)
                tietAddtaksasiMandor.setText(taksasi.mandor)
                tietAddtaksasiFaktor.setText(taksasi.faktorLeng)
                tietAddtaksasiJmlh.setText(taksasi.batangPerMeter)
                tietAddtaksasiIni.setText(taksasi.tinggiIni)
                tietAddtaksasiTebang.setText(taksasi.tinggiTebang)
                tietAddtaksasiDiameter.setText(taksasi.diameterBatang)
                tietAddtaksasiBerat.setText(taksasi.beratPerMeter)
                tietAddtaksasiPandangan.setText(taksasi.pandangan)
            }

            btnAddtaksasiSave.setOnClickListener {
                val namaKebun = tietAddtaksasiNama.text.toString()
                val luas = tietAddtaksasiLuas.text.toString()
                val mandor = tietAddtaksasiMandor.text.toString()
                val faktor = tietAddtaksasiFaktor.text.toString()
                val jmlh = tietAddtaksasiJmlh.text.toString()
                val ini = tietAddtaksasiIni.text.toString()
                val tebang = tietAddtaksasiTebang.text.toString()
                val diameter = tietAddtaksasiDiameter.text.toString()
                val berat = tietAddtaksasiBerat.text.toString()
                val pandangan = tietAddtaksasiPandangan.text.toString()
                val row = jmlh.toDouble().times(100).toString()
                val perHa = row.toDouble().times(faktor.toDouble()).toString()
                val hit = (perHa.toDouble() * tebang.toDouble() * berat.toDouble() / 100) / 10
                val perHit = (hit + pandangan.toDouble()) / 2
                val ton = (perHit * luas.toDouble())

                val taksasiUser = TaksasiRequest(
                    id,
                    luas,
                    mandor,
                    faktor,
                    jmlh,
                    row,
                    perHa,
                    ini,
                    tebang,
                    diameter,
                    berat,
                    hit.toString(),
                    pandangan,
                    perHit.toString(),
                    ton.toString()
                )
                taksasiViewModel.updateTaksasiUser(session.token!!, id, taksasiUser)
                    .observe(viewLifecycleOwner) {
                        findNavController().popBackStack()
                    }
            }
        }
    }

}