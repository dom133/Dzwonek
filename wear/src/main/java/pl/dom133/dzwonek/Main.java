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
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Main extends Activity implements GoogleApiClient.ConnectionCallbacks, DataApi.DataListener {

    private GoogleApiClient googleApiClient;
    private TextView mTextView;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter = null;
    private SharedPreferences sPref;
    private Notifications notifications;
    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        //arrayAdapter = ArrayAdapter.createFromResource(this, R.array.test_array, android.R.layout.simple_list_item_1);

        notifications = new Notifications(getApplication());
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
            Log.i("INFO", "Load ArrayList");
            String[] text = gson.fromJson(sPref.getString("arrayList", null), String[].class);
            List<String> list = Arrays.asList(text);
            arrayList = new ArrayList<>(list);
            arrayAdapter.clear();
            arrayAdapter.addAll(arrayList);
            arrayAdapter.notifyDataSetChanged();
        }

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(Wearable.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {

        try {
            for (DataEvent event : dataEvents) {

                Log.i("INFO", "Event received: " + event.getDataItem().getUri());

                String eventUri = event.getDataItem().getUri().toString();

                if (eventUri.contains("/dzwonek/arrayList")) {
                    DataMapItem dataItem = DataMapItem.fromDataItem(event.getDataItem());
                    String[] data = dataItem.getDataMap().getStringArray("arrayList");
                    List<String> data_list = Arrays.asList(data);
                    arrayList = new ArrayList<>(data_list);
                    sPref.edit().remove("arrayList").commit();
                    sPref.edit().putString("arrayList", gson.toJson(data_list)).commit();
                    arrayAdapter.clear();
                    arrayAdapter.addAll(arrayList);
                    arrayAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplication(), "Dane synchronizowane", Toast.LENGTH_LONG).show();
                    Log.i("INFO", "Data colected ");
                } else if (eventUri.contains("/dzwonek/notification")) {
                    DataMapItem dataItem = DataMapItem.fromDataItem(event.getDataItem());
                    String[] data = dataItem.getDataMap().getStringArray("notification");
                    List<String> data_list = Arrays.asList(data);
                    ArrayList<String> arrayList = new ArrayList<>(data_list);
                    Log.i("INFO", "0: " + arrayList.get(0) + " 1:" + arrayList.get(1));
                    if (arrayList.size() <= 2) {
                        notifications.cancleNotification(1);
                    } else if (Objects.equals(arrayList.get(1), "")) {
                        notifications.cancleNotification(1);
                    } else {
                        notifications.sendNotification(arrayList.get(2), arrayList.get(3), Integer.valueOf(arrayList.get(0)), Integer.valueOf(arrayList.get(1)));
                    }
                }
            }
        } catch(Exception e) {Log.e("ERROR", e.getMessage()); notifications.cancleNotification(1);}
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