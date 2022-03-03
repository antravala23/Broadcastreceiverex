package com.example.broadcastreceiverex

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.broadcastreceiverex.databinding.ActivityBroadcastBinding
import java.lang.Exception


class BroadcastActivity : AppCompatActivity() {

    private var receiverclass: Receiverclass = Receiverclass()
    private lateinit var binding: ActivityBroadcastBinding

    override fun onResume() {
        super.onResume()

        val filterRefreshUpdate = IntentFilter()
        filterRefreshUpdate.addAction("com.example.broadcastreceiverex.CONNECTEDINT")
        filterRefreshUpdate.addAction("com.example.broadcastreceiverex.DISCONNECTEDINT")

        LocalBroadcastManager.getInstance(this@BroadcastActivity).registerReceiver(receiverclass, filterRefreshUpdate)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBroadcastBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.sendbtn.setOnClickListener {

            var intent: Intent? = null

            val activeNetwork =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val isConnected = activeNetwork != null &&
                    activeNetwork.allNetworkInfo[1].isConnected
            if (isConnected) {
                try {

                    intent = Intent("com.example.broadcastreceiverex.CONNECTEDINT")

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                intent = Intent("com.example.broadcastreceiverex.DISCONNECTEDINT")

            }

            LocalBroadcastManager.getInstance(this@BroadcastActivity).sendBroadcast(intent!!)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        LocalBroadcastManager.getInstance(this@BroadcastActivity).unregisterReceiver(receiverclass)
    }

}