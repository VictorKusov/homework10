package ru.list.victor_90.study.maps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MyLocationListener implements LocationListener {

    private static final String LOG = "[MY LOG]";
    private LocationManager manager;
    private Location location;
    private List<String> providers;

    public MyLocationListener(Context context) {
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        providers = manager.getProviders(true);
        for(String provide : providers){
            manager.requestLocationUpdates(provide,5000,0,this);
        }
    }

    public Location getLocation() {
        return location;
    }

    public void disableLocationProvider() {
        manager.removeUpdates(this);
    }


    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        Log.d(LOG, "onLocationChanged");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(LOG, "onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(LOG, "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(LOG, "onProviderDisabled");
        providers = manager.getProviders(true);
    }
}
