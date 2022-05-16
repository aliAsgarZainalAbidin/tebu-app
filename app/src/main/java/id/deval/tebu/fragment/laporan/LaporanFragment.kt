package id.deval.tebu.fragment.laporan

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentLaporanBinding
import id.deval.tebu.db.Session
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.LaporanViewModel
import id.deval.tebu.viewmodels.LoginViewModel
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class LaporanFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val laporanViewModel : LaporanViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var _binding : FragmentLaporanBinding
    private val binding get() = _binding
    @Inject lateinit var session: Session

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

    @androidx.annotation.RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = HelperView.getMainNavController(requireActivity())
        with(binding){
            mtvLaporanLogout.setOnClickListener {
                loginViewModel.logout(session.id!!,session.token!!)
                HelperView.logout(navController, session)
            }

            laporanViewModel.getListUser(session.token!!).observe(viewLifecycleOwner){
                if (it != null){
                    val lapAdapter = LaporanAdapter(it.data.sinder,navController,requireActivity())
                    lapAdapter.notifyDataSetChanged()
                    val lm = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
                    rvLaporanList.apply {
                        adapter = lapAdapter
                        layoutManager = lm
                    }
                } else {
                    HelperView.showToast("Silahkan Login Kembali", requireContext()).show()
                    HelperView.logout(navController, session)
                }
            }
        }

        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )

//        requestManageAllfileAccess()
    }


    @RequiresApi(Build.VERSION_CODES.R)
    private fun requestManageAllfileAccess() {
        if (Environment.isExternalStorageManager()) {
            var internal = File("/sdcard")
            var internalContents = internal.listFiles()
            Log.d(TAG, "storeExcelInStorage: $internalContents")
        } else {
            val permissionIntent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            startActivity(permissionIntent)
        }
    }

}