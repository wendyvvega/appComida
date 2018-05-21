package com.example.supdude.appcomidita;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class metodos {

    public LatLng getLocationFromAddress(Context context, String inputtedAddress) {
        LatLng latLng =null;
        List<Address> addressList = null;

        if (inputtedAddress != null || inputtedAddress.length() > 0) {
            Geocoder geocoder = new Geocoder(context);
            try {
                addressList = geocoder.getFromLocationName(inputtedAddress, 1);

            } catch (IOException e) {

                e.printStackTrace();
            }

            android.location.Address address = addressList.get(0);
            String locality = address.getLocality();
            Log.v("Tag", locality);
            double latitude = address.getLatitude();
            double longitude = address.getLongitude();
            latLng=new LatLng(latitude, longitude);

        } return latLng;
    }
    


}
