package id.deval.tebu.utils

import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus

open class BasedFragment : Fragment() {
    val bus : EventBus = EventBus.getDefault()

    override fun onResume() {
        super.onResume()
        bus.register(this)
    }

    override fun onPause() {
        super.onPause()
        bus.unregister(this)
    }
}