package pl.dom133.dzwonek;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;

public class GoogleApi implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    private Application app;
    private String name;
    private ArrayList<String> arrayList;

    public GoogleApi(Application app) {
        this.app = app;
        googleApiClient = new GoogleApiClient.Builder(app)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApiIfAvailable(Wearable.API)
                .build();
    }

    public void sendData(String name, ArrayList<String> arrayList) {
        this.name = name;
        this.arrayList = arrayList;
        googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("INFO", "Connection succes "+bundle);
        Log.i("INFO", "Sending data " + name);

        String[] array = new String[arrayList.size()];
        array = arrayList.toArray(array);

        PutDataMapRequest dataMap = PutDataMapRequest.create("/dzwonek/" + name);
        dataMap.getDataMap().putStringArray(name, array);
        PutDataRequest request = dataMap.asPutDataRequest();
        PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi
                .putDataItem(googleApiClient, request);
        name = null;
        arrayList = new ArrayList<>();
        googleApiClient.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("INFO", "Connection Suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("INFO", "Connection Failed");
    }
}
