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
                mtvRvitemPtk.text = data.petak
                mtvRvitemLuas.text = data.luas
                mtvRvitemJenis.text = data.jenis
                mtvRvitemKtg.text = data.kategori
                mtvRvitemFaktor.text = data.faktorLeng
                mtvRvitemJmlBpm.text = data.batangPerMeter
                mtvRvitemJmlBpr.text = data.batangPerRow
                mtvRvitemJmlBph.text = data.batangPerHa
                mtvRvitemIni.text = data.tinggiIni
                mtvRvitemTebang.text = data.tinggiTebang
                mtvRvitemDiameter.text = data.diameterBatang
                mtvRvitemBerat.text = data.beratPerMeter
                mtvRvitemHit.text = data.hit
                mtvRvitemPandangan.text = data.pandangan
                mtvRvitemPerhit.text = data.perHit
                mtvRvitemKui.text = data.kui

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