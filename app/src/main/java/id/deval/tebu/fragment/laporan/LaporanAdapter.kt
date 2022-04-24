package id.deval.tebu.fragment.laporan

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.deval.tebu.R
import id.deval.tebu.databinding.RvItemLaporanBinding
import id.deval.tebu.db.response.User
import id.deval.tebu.utils.Constanta
import id.deval.tebu.utils.HelperView

class LaporanAdapter(
    private var listUser : ArrayList<User>,
    private val navController: NavController,
    private val activity: Activity
) : RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder>() {
    class LaporanViewHolder(private val binding: RvItemLaporanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, navController: NavController){
            with(binding){
                mtvRvitemName.text = user.nama
                mtvRvitemKebun.text = user.wilayah
                mtvRvitemPtk.text = user.lokasi

                ivRvitemIcon.setOnClickListener {
                    HelperView.expandListItemRecycler(ivRvitemIcon, clRvitemContainer)
                }

                clRvitemContainermain.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString(Constanta.ID_ITEM_ARGS, user.id)
                    navController.navigate(R.id.action_baseFragment_to_detailLaporanFragment, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanViewHolder {
        val view = RvItemLaporanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LaporanViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaporanViewHolder, position: Int) {
        holder.bind(listUser[position], navController)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}