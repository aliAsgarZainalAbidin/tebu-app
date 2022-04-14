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
import id.deval.tebu.databinding.FragmentAddTaksasiBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.Constanta
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.TaksasiViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AddTaksasiFragment : Fragment() {

    private val taksasiViewModel : TaksasiViewModel by viewModels()
    @Inject lateinit var session: Session
    private lateinit var navController: NavController
    private lateinit var _binding : FragmentAddTaksasiBinding
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

        with(binding){
            taksasiViewModel.getTaksasiById(session.token!!,id!!).observe(viewLifecycleOwner){
                val taksasi = it[0]
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

            }
        }
    }

}