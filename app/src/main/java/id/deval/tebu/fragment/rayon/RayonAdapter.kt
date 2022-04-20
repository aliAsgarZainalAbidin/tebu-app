package id.deval.tebu.fragment.rayon

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.deval.tebu.R
import id.deval.tebu.databinding.RvItemRayonBinding
import id.deval.tebu.db.request.RayonRequest
import id.deval.tebu.utils.Constanta
import id.deval.tebu.utils.HelperView
import id.deval.tebu.utils.event.CommonParams
import org.greenrobot.eventbus.EventBus

class RayonAdapter(
    private val listRayon: ArrayList<RayonRequest>,
    private val navController: NavController,
    private val activity: Activity
) : RecyclerView.Adapter<RayonAdapter.RayonViewHolder>() {

    private val bus = EventBus.getDefault()

    class RayonViewHolder(private val binding: RvItemRayonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RayonRequest, navController: NavController, bus: EventBus) {
            with(binding) {
                mtvRvitemName.text = data.nama
                mtvRvitemLokasi.text = data.lokasi

                ivRvitemIcon.setOnClickListener {
                    HelperView.expandListItemRecycler(ivRvitemIcon, clRvitemContainer)
                }
                ivRvitemEdit.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString(Constanta.ID_ITEM_ARGS,data.id)
                    navController.navigate(R.id.action_baseFragment_to_addRayonFragment,bundle)
                }
                ivRvitemDelete.setOnClickListener {
                    bus.post(CommonParams(data.id))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RayonViewHolder {
        val view = RvItemRayonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RayonViewHolder(view)
    }

    override fun onBindViewHolder(holder: RayonViewHolder, position: Int) {
        holder.bind(listRayon[position],navController, bus)
    }

    override fun getItemCount(): Int {
        return listRayon.size
    }
}