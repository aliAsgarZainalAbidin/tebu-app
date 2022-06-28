package id.deval.tebu.fragment.rayon

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentRayonBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.HelperView
import id.deval.tebu.utils.event.CommonParams
import id.deval.tebu.viewmodels.LoginViewModel
import id.deval.tebu.viewmodels.RayonViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

@AndroidEntryPoint
class RayonFragment : Fragment() {

    private val bus = EventBus.getDefault()
    private val loginViewModel: LoginViewModel by viewModels()
    private val rayonViewModel: RayonViewModel by viewModels()
    private lateinit var _binding: FragmentRayonBinding
    private val binding get() = _binding
    private lateinit var navController: NavController
    @Inject
    lateinit var session: Session

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRayonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        bus.register(this)
    }

    override fun onPause() {
        super.onPause()
        bus.unregister(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = HelperView.getMainNavController(requireActivity())

        with(binding) {
            Log.d(TAG, "onViewCreated: ${session.token}")
            mtvRayonLogout.setOnClickListener {
                loginViewModel.logout(session.id!!, session.token!!)
                HelperView.logout(navController, session)
            }

            btnRayonAdd.setOnClickListener {
                navController.navigate(R.id.action_baseFragment_to_addRayonFragment)
            }

            refreshDataRecycler()
        }
    }

    fun refreshDataRecycler(){
        with(binding){
            rayonViewModel.getAllRayon(session.token!!).observe(viewLifecycleOwner) {
                if (it != null) {
                    val adapterRayon = RayonAdapter(it.data.rayon, navController, requireActivity())
                    adapterRayon.notifyDataSetChanged()
                    val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    rvRayonList.apply {
                        adapter = adapterRayon
                        layoutManager = lm
                    }
                } else {
                    HelperView.showToast("Silahkan Login Kembali", requireContext()).show()
                    HelperView.logout(navController, session)
                }

            }
        }
    }

    @Subscribe
    fun iconDeleteListener(commonParams: CommonParams) {
        rayonViewModel.deleteRayon(session.token!!, commonParams.id!!).observe(viewLifecycleOwner) {
            HelperView.showToast(it.message,requireContext()).show()
            refreshDataRecycler()
        }
    }

}