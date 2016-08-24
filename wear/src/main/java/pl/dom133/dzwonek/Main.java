package pl.dom133.dzwonek;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main extends Activity implements GoogleApiClient.ConnectionCallbacks, DataApi.DataListener {

    private TextView mTextView;
    private GoogleApiClient googleApiClient;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter = null;
    private SharedPreferences sPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        //arrayAdapter = ArrayAdapter.createFromResource(this, R.array.test_array, android.R.layout.simple_list_item_1);

        sPref = getSharedPreferences("Dzwonek", Context.MODE_PRIVATE);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                //ListView
                ListView list = (ListView) stub.findViewById(R.id.list_spinner);
                list.setAdapter(arrayAdapter);
            }
        });

        //Load ArrayList
        if(sPref.contains("arrayList")) {
            Set<String> set = sPref.getStringSet("arrayList", null);
            arrayList = new ArrayList<>(set);
            arrayAdapter.notifyDataSetChanged();
        }

        //GoogleApiClient
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(Wearable.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {

        for (DataEvent event: dataEvents) {

            Log.i("INFO", "Event received: " + event.getDataItem().getUri());

            String eventUri = event.getDataItem().getUri().toString();

            if (eventUri.contains ("/dzwonek/arrayList")) {

                DataMapItem dataItem = DataMapItem.fromDataItem (event.getDataItem());
                String[] data = dataItem.getDataMap().getStringArray("arrayList");
                List<String> data_list = Arrays.asList(data);
                arrayList = new ArrayList<>(data_list);
                Set<String> set = new HashSet<String>();
                set.addAll(arrayList);
                sPref.edit().putStringSet("arrayList", set).commit();
                arrayAdapter.notifyDataSetChanged();
                Log.i("INFO","Data colected ");
            } else if(eventUri.contains ("/dzwonek/notification")) {
                ;
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("INFO", "Connection succes "+bundle);
        Wearable.DataApi.addListener(googleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
