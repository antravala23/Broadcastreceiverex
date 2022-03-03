package com.example.broadcastreceiverex

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.net.NetworkInfo

import android.net.ConnectivityManager
import java.lang.Exception


class Receiverclass: BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {

        if(p1?.action.equals("com.example.broadcastreceiverex.CONNECTEDINT")) {
            Toast.makeText(p0, "receiver invoked", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(p0, "receiver not invoked", Toast.LENGTH_SHORT).show()
        }

    }

}