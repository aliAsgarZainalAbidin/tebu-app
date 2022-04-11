package id.deval.tebu.fragment.rayon

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.deval.tebu.databinding.RvItemRayonBinding
import id.deval.tebu.db.request.RayonRequest
import id.deval.tebu.utils.HelperView

class RayonAdapter(
    private val listRayon: ArrayList<RayonRequest>,
    private val navController: NavController,
    private val activity: Activity
) : RecyclerView.Adapter<RayonAdapter.RayonViewHolder>() {

    class RayonViewHolder(private val binding: RvItemRayonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RayonRequest) {
            with(binding) {
                mtvRvitemName.text = data.nama
                mtvRvitemLokasi.text = data.lokasi

                ivRvitemIcon.setOnClickListener {
                    HelperView.expandListItemRecycler(ivRvitemIcon, clRvitemContainer)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RayonViewHolder {
        val view = RvItemRayonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RayonViewHolder(view)
    }

    override fun onBindViewHolder(holder: RayonViewHolder, position: Int) {
        holder.bind(listRayon[position])
    }

    override fun getItemCount(): Int {
        return listRayon.size
    }
}