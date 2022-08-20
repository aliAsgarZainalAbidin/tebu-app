package id.deval.tebu.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.db.Session
import id.deval.tebu.db.request.SinderRequest
import id.deval.tebu.retrofit.ApiFactory
import id.deval.tebu.retrofit.ApiInterface
import id.deval.tebu.viewmodels.SinderViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeriodicWorker(
    val context: Context,
    val workerParams: WorkerParameters,
) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        Log.d("PeriodicWorker", "doWork: PERIODIC POST DATA")
        val simpleFormatter = SimpleDateFormat("HH:mm:ss")
        val now = Calendar.getInstance().time
        val nowString = simpleFormatter.format(now)
        Log.d(TAG, "doWork: $nowString")
        val token = inputData.getString("session")

        val sinder = SinderRequest(
                "Ali POST PERIODIC",
                nowString.toString(),
                "password",
                "081524752447",
                "alamat",
                "wilayah",
                lokasi = "lokasi"
            )

        val apiInterface = ApiFactory.create()
        apiInterface.addSinder(sinder, token.toString())

        return Result.success()
    }
}