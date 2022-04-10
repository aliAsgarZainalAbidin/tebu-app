package id.deval.tebu.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import id.deval.tebu.R
import id.deval.tebu.db.Session

object HelperView {

    fun logout(navController: NavController, session: Session){
        when(session.role){
            "admin" -> navController.navigate(R.id.action_baseFragment_to_loginFragment)
            "sinder" -> navController.navigate(R.id.action_taksasiFragment_to_loginFragment)
        }
        session.logout()
    }

    fun getSecNavController(fragment : Fragment):NavController{
        return fragment.childFragmentManager.findFragmentById(R.id.fcv_base_host)?.findNavController() ?: fragment.findNavController()
    }

    fun getMainNavController(activity: Activity):NavController{
        return activity.findNavController(R.id.fcv_main_host)
    }

    fun showToast(message:String, context: Context):Toast{
        return Toast.makeText(context,message, Toast.LENGTH_SHORT)
    }
}