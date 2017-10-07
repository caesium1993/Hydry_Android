package com.example.hydry;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;


/**
 * Created by caesium on 3/10/2017.
 */

public class LocationDetermination {
    private static LocationManager locationManager;
    //public static String cityName;
    private static Geocoder geocoder;
    private Activity activity;

    public LocationDetermination(Activity activity) {
        this.activity = activity;
    }

    /*
     * check GPS setting
     */
    public void checkGPSSetting() {
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(activity, "Locating your city...", Toast.LENGTH_LONG).show();
            getLocation();
        } else {
            Toast.makeText(activity, "Please turn on GPS", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
            activity.startActivityForResult(intent, 0);
        }

    }

    /*
     * to obtain the located city
     */
    public String getLocation() {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.
                LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // fine accuracy for GPS
        criteria.setAltitudeRequired(false); // no need to read altitude data
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // low power requirement

        String provider = locationManager.getBestProvider(criteria, true);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.
                ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        geocoder = new Geocoder(activity);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size()>0){
            String message = "Welcome to "+addresses.get(0).getCountryName();
            Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
        }
        return addresses.get(0).getCountryName();

    }
}
