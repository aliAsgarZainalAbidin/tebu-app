package id.deval.tebu.fragment.taksasi

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.deval.tebu.R
import id.deval.tebu.databinding.RvItemRincianTaksasiBinding
import id.deval.tebu.db.response.Taksasi
import id.deval.tebu.utils.Constanta
import id.deval.tebu.utils.HelperView

class TaksasiAdapter(
    private val listTaksasi : ArrayList<Taksasi>,
    private val navController: NavController,
    private val activity:Activity
) : RecyclerView.Adapter<TaksasiAdapter.TaksasiViewHolder>() {

    class TaksasiViewHolder(private val binding: RvItemRincianTaksasiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taksasi: Taksasi, navController: NavController){
            with(binding){
                mtvRvitemName.text = taksasi.namaKebun
                mtvRvitemLuas.text = taksasi.luas
                mtvRvitemPetak.text = taksasi.petak
                mtvRvitemJenis.text = taksasi.jenisTebu
                mtvRvitemKategori.text = taksasi.kategori

                ivRvitemDelete.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString(Constanta.ID_ITEM_ARGS, taksasi.id)
                    bundle.putString(Constanta.PETAK, taksasi.petak)
                    bundle.putString(Constanta.JENIS, taksasi.jenisTebu)
                    bundle.putString(Constanta.KATEGORI, taksasi.kategori)
                    navController.navigate(R.id.action_taksasiFragment_to_detailTaksasiFragment,bundle)
                }

                ivRvitemIcon.setOnClickListener {
                    HelperView.expandListItemRecycler(ivRvitemIcon, clRvitemContainer)
                }

                ivRvitemEdit.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString(Constanta.ID_ITEM_ARGS, taksasi.id)
                    navController.navigate(R.id.action_taksasiFragment_to_addTaksasiFragment,bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaksasiViewHolder {
        val view = RvItemRincianTaksasiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaksasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaksasiViewHolder, position: Int) {
        holder.bind(listTaksasi[position],navController)
    }

    override fun getItemCount(): Int {
        return listTaksasi.size
    }
}