package id.deval.tebu.fragment.kebun

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.deval.tebu.R
import id.deval.tebu.databinding.RvItemKebunBinding
import id.deval.tebu.db.response.Kebun
import id.deval.tebu.utils.Constanta
import id.deval.tebu.utils.HelperView
import id.deval.tebu.utils.event.CommonParams
import org.greenrobot.eventbus.EventBus

class KebunAdapter(
    private var listKebun : ArrayList<Kebun>,
    private var navController: NavController,
    private var activity:Activity
) : RecyclerView.Adapter<KebunAdapter.KebunViewHolder>() {

    private val bus = EventBus.getDefault()

    class KebunViewHolder(private val binding: RvItemKebunBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Kebun,navController: NavController, bus: EventBus){
            with(binding){
                with(data){
                    mtvRvitemName.text = namaKebun
                    mtvRvitemLuas.text = luas
                    mtvRvitemPetak.text = petak
                    mtvRvitemJenis.text = jenisTebu
                    mtvRvitemKategori.text = kategori
                    mtvRvitemSinder.text = namaSinder
                    mtvRvitemWilayah.text = wilayah

                    ivRvitemIcon.setOnClickListener {
                        HelperView.expandListItemRecycler(ivRvitemIcon,clRvitemContainer)
                    }

                    ivRvitemEdit.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putString(Constanta.ID_ITEM_ARGS,data.id)
                        navController.navigate(R.id.action_baseFragment_to_addKebunFragment, bundle)
                    }

                    ivRvitemDelete.setOnClickListener {
                        bus.post(CommonParams(data.id))
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KebunViewHolder {
        val view = RvItemKebunBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return KebunViewHolder(view)
    }

    override fun onBindViewHolder(holder: KebunViewHolder, position: Int) {
        holder.bind(listKebun[position],navController,bus)
    }

    override fun getItemCount(): Int {
        return listKebun.size
    }
}