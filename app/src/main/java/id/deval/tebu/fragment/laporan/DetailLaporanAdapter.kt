package id.deval.tebu.fragment.laporan

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.deval.tebu.databinding.RvItemRincianLaporanBinding
import id.deval.tebu.db.request.TaksasiWithUserRequest
import id.deval.tebu.utils.HelperView

class DetailLaporanAdapter(
    private var listKebun : ArrayList<TaksasiWithUserRequest>,
    private var navController: NavController,
    private var activity : Activity
) : RecyclerView.Adapter<DetailLaporanAdapter.DetailViewHolder>() {
    class DetailViewHolder(val binding: RvItemRincianLaporanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data : TaksasiWithUserRequest){
            with(binding){
                mtvRvitemName.text = data.mandor
                mtvRvitemKebun.text = data.namaKebun
                mtvRvitemPtk.text = "${data.petak} petak"
                mtvRvitemLuas.text = "${data.luas} ha"
                mtvRvitemJenis.text = data.jenis
                mtvRvitemKtg.text = data.kategori
                mtvRvitemFaktor.text = data.faktorLeng
                mtvRvitemJmlBpm.text = "${data.batangPerMeter} batang"
                mtvRvitemJmlBpr.text = "${data.batangPerRow} batang"
                mtvRvitemJmlBph.text = "${data.batangPerHa} batang"
                mtvRvitemIni.text = "${data.tinggiIni} m"
                mtvRvitemTebang.text = "${data.tinggiTebang} m"
                mtvRvitemDiameter.text = "${data.diameterBatang} cm"
                mtvRvitemBerat.text = "${data.beratPerMeter} Kg"
                mtvRvitemHit.text = "${data.hit} ton/ha"
                mtvRvitemPandangan.text = "${data.pandangan} ton/ha"
                mtvRvitemPerhit.text = "${data.perHit} ton"
                mtvRvitemKui.text = "${data.kui} kwintal"

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