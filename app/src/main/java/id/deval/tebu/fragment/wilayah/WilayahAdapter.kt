package id.deval.tebu.fragment.wilayah

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.deval.tebu.databinding.RvItemWilayahBinding
import id.deval.tebu.db.response.Wilayah
import id.deval.tebu.utils.HelperView

class WilayahAdapter(
    private val listWilayah: ArrayList<Wilayah>,
    private val navController: NavController,
    private val activity: Activity
) : RecyclerView.Adapter<WilayahAdapter.WilayahViewHolder>() {
    class WilayahViewHolder(private val binding: RvItemWilayahBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Wilayah) {
            with(binding){
                mtvRvitemName.text = data.id.toString()
                mtvRvitemNamaWilayah.text = data.wilayah
                mtvRvitemNamaRayon.text = data.rayon
                mtvRvitemLokasi.text = data.lokasi

                ivRvitemIcon.setOnClickListener {
                    HelperView.expandListItemRecycler(ivRvitemIcon,clRvitemContainer)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WilayahViewHolder {
        val view = RvItemWilayahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WilayahViewHolder(view)
    }

    override fun onBindViewHolder(holder: WilayahViewHolder, position: Int) {
        holder.bind(listWilayah[position])
    }

    override fun getItemCount(): Int {
        return listWilayah.size
    }
}