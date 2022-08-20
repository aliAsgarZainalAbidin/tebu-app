package id.deval.tebu.fragment.laporan

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.deval.tebu.databinding.RvItemRincianLaporanBinding
import id.deval.tebu.db.request.TaksasiWithUserRequest
import id.deval.tebu.utils.HelperView
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class DetailLaporanAdapter(
    private var listKebun : ArrayList<TaksasiWithUserRequest>,
    private var navController: NavController,
    private var activity : Activity
) : RecyclerView.Adapter<DetailLaporanAdapter.DetailViewHolder>() {
    class DetailViewHolder(val binding: RvItemRincianLaporanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data : TaksasiWithUserRequest){
            with(binding){
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                mtvRvitemName.text = data.mandor
                mtvRvitemKebun.text = data.namaKebun
                mtvRvitemPtk.text = "${data.petak} petak"
                mtvRvitemLuas.text = "${data.luas} ha"
                mtvRvitemJenis.text = data.jenis
                mtvRvitemKtg.text = data.kategori
                mtvRvitemFaktor.text = data.faktorLeng
                mtvRvitemJmlBpm.text = "${df.format(data.batangPerMeter?.toDouble())} batang"
                mtvRvitemJmlBpr.text = "${df.format(data.batangPerRow?.toDouble())} batang"
                mtvRvitemJmlBph.text = "${df.format(data.batangPerHa?.toDouble())} batang"
                mtvRvitemIni.text = "${data.tinggiIni} m"
                mtvRvitemTebang.text = "${data.tinggiTebang} m"
                mtvRvitemDiameter.text = "${data.diameterBatang} cm"
                mtvRvitemBerat.text = "${df.format(data.beratPerMeter?.toDouble())} Kg"
                mtvRvitemHit.text = "${df.format(data.hit?.toDouble())} ton/ha"
                mtvRvitemPandangan.text = "${df.format(data.pandangan?.toDouble())} ton/ha"
                mtvRvitemPerhit.text = "${df.format(data.perHit?.toDouble())} ton"
                mtvRvitemKui.text = "${df.format(data.kui?.toDouble())} ton"

                ivRvitemIcon.setOnClickListener {
                    HelperView.expandListItemRecycler(ivRvitemIcon, clRvitemContainer)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = RvItemRincianLaporanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(listKebun[position])
    }

    override fun getItemCount(): Int {
        return listKebun.size
    }
}