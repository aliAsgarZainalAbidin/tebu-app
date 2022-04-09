package id.deval.tebu.utils

import androidx.navigation.NavController
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
}