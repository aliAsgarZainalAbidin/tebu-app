package id.deval.tebu.fragment.sinder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentSinderBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.BasedFragment
import id.deval.tebu.utils.HelperView
import id.deval.tebu.utils.event.CommonParams
import id.deval.tebu.viewmodels.LoginViewModel
import id.deval.tebu.viewmodels.SinderViewModel
import id.deval.tebu.worker.PeriodicWorker
import org.greenrobot.eventbus.Subscribe
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class SinderFragment : BasedFragment() {

    private val sinderViewModel: SinderViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var session: Session
    private lateinit var navController: NavController
    private lateinit var _binding: FragmentSinderBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSinderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = HelperView.getMainNavController(requireActivity())

        with(binding) {
            btnSinderAdd.setOnClickListener {
                navController.navigate(R.id.action_baseFragment_to_addSinderFragment2)
            }

            mtvSinderLogout.setOnClickListener {
                loginViewModel.logout(session.id!!, session.token!!)
                HelperView.logout(navController, session)
            }

            refreshRecylcerView()
        }

        //REMOVE THIS
//        val data = workDataOf("session" to session.token.toString())
//        val periodicPostWorker = PeriodicWorkRequest
//            .Builder(
//                PeriodicWorker::class.java,
//                15, TimeUnit.MINUTES)
//            .setInputData(data)
//            .build()
//
//        WorkManager
//            .getInstance(requireContext())
//            .enqueueUniquePeriodicWork(
//                "periodicWorker",
//                ExistingPeriodicWorkPolicy.KEEP,
//                periodicPostWorker
//            )
//        Log.d("PERIODIC WORKER", "onViewCreated: ${periodicPostWorker.id}")
    }

    fun refreshRecylcerView() {
        with(binding) {
            sinderViewModel.getAllSinder(session.token.toString()).observe(viewLifecycleOwner) {
                if (it != null) {
                    val sinderAdapter =
                        SinderAdapter(it.data.sinder, navController, requireActivity())
                    val lm =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    rvSinderList.apply {
                        adapter = sinderAdapter
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
        sinderViewModel.deleteSinder(session.token!!, commonParams.id!!)
            .observe(viewLifecycleOwner) {
                HelperView.showToast(it.message, requireContext()).show()
                refreshRecylcerView()
            }
    }
}