package com.nguyen.codelabandroidlifecycles

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

object BoundLocationManager {
    fun bindLocationListenerIn(lifecycleOwner: LifecycleOwner, listener: LocationListener, context: Context) {
        BoundLocationListener(lifecycleOwner, listener, context)
    }

    internal class BoundLocationListener(
        lifecycleOwner: LifecycleOwner,
        private val listener: LocationListener, private val context: Context
    ) : LifecycleObserver {
        private var locationManager: LocationManager? = null

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        //TODO: Call this on resume
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun addLocationListener() {
            // Note: Use the Fused Location Provider from Google Play Services instead.
            // https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderApi
            /*locationManager?.let { manager ->
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, listener)
                Log.d("BoundLocationMgr", "Listener added")

                // Force an update with the last location, if available.
                val lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                lastLocation?.let { location ->
                    listener.onLocationChanged(location)
                }
            }*/
        }

        //TODO: Call this on pause
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun removeLocationListener() {
            if (locationManager == null) {
                return
            }
            locationManager!!.removeUpdates(listener)
            locationManager = null
            Log.d("BoundLocationMgr", "Listener removed")
        }
    }
}