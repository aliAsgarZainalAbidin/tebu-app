package id.deval.tebu.fragment.sinder

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.deval.tebu.R
import id.deval.tebu.databinding.RvItemSinderBinding
import id.deval.tebu.db.response.User

class SinderAdapter(
    private val listSinder: ArrayList<User>,
    private val navController: NavController,
    private val activity: Activity
) : RecyclerView.Adapter<SinderAdapter.SinderViewHolder>() {
    class SinderViewHolder(private val binding: RvItemSinderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: User) {
            with(binding){
                mtvRvitemName.text = ": ${data.nama}"
                mtvRvitemUsername.text = ": ${data.username}"
                mtvRvitemPassword.text = ": ${data.password}"
                mtvRvitemTelepon.text = ": ${data.telepon}"
                mtvRvitemAlamat.text = ": ${data.alamat}"
                mtvRvitemWilayah.text = ": ${data.wilayah}"
                mtvRvitemLokasi.text = ": ${data.lokasi}"

                ivRvitemIcon.setOnClickListener {
                    if (ivRvitemIcon.rotation != 0F){
                        clRvitemContainer.visibility = View.VISIBLE
                        ivRvitemIcon.rotation = 0F
                    }  else {
                        clRvitemContainer.visibility = View.GONE
                        ivRvitemIcon.rotation = 180F
                    }
                }
                ivRvitemDelete.setOnClickListener {  }
                ivRvitemEdit.setOnClickListener {  }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinderViewHolder {
        val view = RvItemSinderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SinderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SinderViewHolder, position: Int) {
        holder.bind(listSinder[position])
    }

    override fun getItemCount(): Int {
        return listSinder.size
    }
}