package pl.dom133.dzwonek;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.io.File;
import java.util.ArrayList;

public class notification_dzwonek extends AppCompatActivity {

    //Variables private
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private GoogleApiClient mGoogleApiClient;

    //Variables public
    public Times ts = new Times();
    public Settings settings;
    public NotificationTask nt;
    public File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_dzwonek);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Objects
        nt = new NotificationTask(getApplication(), ts);
        settings = new Settings(getApplication());
        ListView vw = (ListView) findViewById(R.id.listView);

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        vw.setAdapter(adapter);
        //Load Settings
        if (settings.getInt("List_size[1]") != 0) {
            int size = settings.getInt("List_size[1]");
            ts.od_time_list_1 = settings.getArray("od", 1);
            ts.do_time_list_1 = settings.getArray("do", 1);
            arrayList.add("Poniedziałek: ");
            for (int i = 0; i <= size - 1; i++) {
                String[] od_times = ts.od_time_list_1.get(i).split(":");
                String[] do_times = ts.do_time_list_1.get(i).split(":");
                if (Integer.valueOf(od_times[0]) <= 9) od_times[0] = "0" + od_times[0];
                if (Integer.valueOf(od_times[1]) <= 9) od_times[1] = "0" + od_times[1];
                if (Integer.valueOf(do_times[0]) <= 9) do_times[0] = "0" + do_times[0];
                if (Integer.valueOf(do_times[1]) <= 9) do_times[1] = "0" + do_times[1];
                arrayList.add(" " + (i + 1) + ". " + od_times[0] + ":" + od_times[1] + "-->" + do_times[0] + ":" + do_times[1]);
            }
            arrayList.add("");
            adapter.notifyDataSetChanged();
        }

        if (settings.getInt("List_size[2]") != 0) {
            int size = settings.getInt("List_size[2]");
            ts.od_time_list_2 = settings.getArray("od", 2);
            ts.do_time_list_2 = settings.getArray("do", 2);
            arrayList.add("Wtorek: ");
            for (int i = 0; i <= size - 1; i++) {
                String[] od_times = ts.od_time_list_2.get(i).split(":");
                String[] do_times = ts.do_time_list_2.get(i).split(":");
                if (Integer.valueOf(od_times[0]) <= 9) od_times[0] = "0" + od_times[0];
                if (Integer.valueOf(od_times[1]) <= 9) od_times[1] = "0" + od_times[1];
                if (Integer.valueOf(do_times[0]) <= 9) do_times[0] = "0" + do_times[0];
                if (Integer.valueOf(do_times[1]) <= 9) do_times[1] = "0" + do_times[1];
                arrayList.add(" " + (i + 1) + ". " + od_times[0] + ":" + od_times[1] + "-->" + do_times[0] + ":" + do_times[1]);
            }
            arrayList.add("");
            adapter.notifyDataSetChanged();
        }

        if (settings.getInt("List_size[3]") != 0) {
            int size = settings.getInt("List_size[3]");
            ts.od_time_list_3 = settings.getArray("od", 3);
            ts.do_time_list_3 = settings.getArray("do", 3);
            arrayList.add("Środa: ");
            for (int i = 0; i <= size - 1; i++) {
                String[] od_times = ts.od_time_list_3.get(i).split(":");
                String[] do_times = ts.do_time_list_3.get(i).split(":");
                if (Integer.valueOf(od_times[0]) <= 9) od_times[0] = "0" + od_times[0];
                if (Integer.valueOf(od_times[1]) <= 9) od_times[1] = "0" + od_times[1];
                if (Integer.valueOf(do_times[0]) <= 9) do_times[0] = "0" + do_times[0];
                if (Integer.valueOf(do_times[1]) <= 9) do_times[1] = "0" + do_times[1];
                arrayList.add(" " + (i + 1) + ". " + od_times[0] + ":" + od_times[1] + "-->" + do_times[0] + ":" + do_times[1]);
            }
            arrayList.add("");
            adapter.notifyDataSetChanged();
        }

        if (settings.getInt("List_size[4]") != 0) {
            int size = settings.getInt("List_size[4]");
            ts.od_time_list_4 = settings.getArray("od", 4);
            ts.do_time_list_4 = settings.getArray("do", 4);
            arrayList.add("Czwartek: ");
            for (int i = 0; i <= size - 1; i++) {
                String[] od_times = ts.od_time_list_4.get(i).split(":");
                String[] do_times = ts.do_time_list_4.get(i).split(":");
                if (Integer.valueOf(od_times[0]) <= 9) od_times[0] = "0" + od_times[0];
                if (Integer.valueOf(od_times[1]) <= 9) od_times[1] = "0" + od_times[1];
                if (Integer.valueOf(do_times[0]) <= 9) do_times[0] = "0" + do_times[0];
                if (Integer.valueOf(do_times[1]) <= 9) do_times[1] = "0" + do_times[1];
                arrayList.add(" " + (i + 1) + ". " + od_times[0] + ":" + od_times[1] + "-->" + do_times[0] + ":" + do_times[1]);
            }
            arrayList.add("");
            adapter.notifyDataSetChanged();
        }

        if (settings.getInt("List_size[5]") != 0) {
            int size = settings.getInt("List_size[5]");
            ts.od_time_list_5 = settings.getArray("od", 5);
            ts.do_time_list_5 = settings.getArray("do", 5);
            arrayList.add("Piątek: ");
            for (int i = 0; i <= size - 1; i++) {
                String[] od_times = ts.od_time_list_5.get(i).split(":");
                String[] do_times = ts.do_time_list_5.get(i).split(":");
                if (Integer.valueOf(od_times[0]) <= 9) od_times[0] = "0" + od_times[0];
                if (Integer.valueOf(od_times[1]) <= 9) od_times[1] = "0" + od_times[1];
                if (Integer.valueOf(do_times[0]) <= 9) do_times[0] = "0" + do_times[0];
                if (Integer.valueOf(do_times[1]) <= 9) do_times[1] = "0" + do_times[1];
                arrayList.add(" " + (i + 1) + ". " + od_times[0] + ":" + od_times[1] + "-->" + do_times[0] + ":" + do_times[1]);
            }
            arrayList.add("");
            adapter.notifyDataSetChanged();
        }
        if (settings.checkExist("Wait")) nt.wait = settings.getInt("Wait");
        if (settings.checkExist("Zmienna")) nt.zmienna = settings.getString("Zmienna");
        Log.i("MAIN", "Load Settings");
        //Notification BackgroundTask
        nt.execute();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle connectionHint) {
                        Log.i("WEAR", "onConnected: " + connectionHint);
                    }

                    @Override
                    public void onConnectionSuspended(int cause) {
                        Log.i("WEAR", "onConnectionSuspended: " + cause);
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.i("WEAR", "onConnectionFailed: " + result);
                    }
                })
                .addApiIfAvailable(Wearable.API)
                .build();

        LayoutInflater factory = LayoutInflater.from(this);
        final View addDialogView = factory.inflate(
                R.layout.add_times_dialog, null);
        final AlertDialog addDialog = new AlertDialog.Builder(this).create();
        addDialog.setView(addDialogView);

        addDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                //Spiners Dialog
                Spinner days_spinner = (Spinner) addDialogView.findViewById(R.id.day_txt);
                ArrayAdapter<CharSequence> adapter_days_spinner = ArrayAdapter.createFromResource(getApplication(),
                        R.array.days_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                days_spinner.setAdapter(adapter_days_spinner);
                Spinner lesson_spinner = (Spinner) addDialogView.findViewById(R.id.lesson_txt);
                ArrayAdapter<CharSequence> adapter_lesson_spinner = ArrayAdapter.createFromResource(getApplication(),
                        R.array.lesson_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lesson_spinner.setAdapter(adapter_lesson_spinner);
            }
        });

        addDialogView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner day = (Spinner) addDialog.findViewById(R.id.day_txt);
                Spinner lesson = (Spinner) addDialog.findViewById(R.id.lesson_txt);
                int lesson_int = Integer.valueOf(lesson.getSelectedItem().toString());
                Log.i("DIALOG", "Day: " + day.getSelectedItem().toString());
                Log.i("DIALOG", "Lesson: " + lesson_int);
                switch (day.getSelectedItem().toString()) {
                    case "Poniedziałek": {
                        Log.i("DIALOG", "Poniedzialek: 1");
                        if (ts.od_time_list_1.size() == 0) {
                            Log.i("DIALOG", "Poniedzialek: 2");
                            ts.od_time_list_1 = ts.getLesson(lesson_int, "od");
                            ts.do_time_list_1 = ts.getLesson(lesson_int, "do");
                            arrayList.add("Poniedziałek: ");
                            for (int i = 1; i <= lesson_int; i++) {
                                Log.i("DIALOG", "Poniedzialek: 3:" + i);
                                String[] od_times = ts.od_time_list_1.get(i - 1).split(":");
                                String[] do_times = ts.do_time_list_1.get(i - 1).split(":");
                                if (Integer.valueOf(od_times[0]) <= 9)
                                    od_times[0] = "0" + od_times[0];
                                if (Integer.valueOf(od_times[1]) <= 9)
                                    od_times[1] = "0" + od_times[1];
                                if (Integer.valueOf(do_times[0]) <= 9)
                                    do_times[0] = "0" + do_times[0];
                                if (Integer.valueOf(do_times[1]) <= 9)
                                    do_times[1] = "0" + do_times[1];
                                arrayList.add(" " + i + ". " + od_times[0] + ":" + od_times[1] + "-->" + do_times[0] + ":" + do_times[1]);
                            }
                            settings.clearArray("od", 1);
                            settings.clearArray("do", 1);
                            settings.saveArray("od", ts.od_time_list_1, 1);
                            settings.saveArray("do", ts.do_time_list_1, 1);
                            arrayList.add("");
                            adapter.notifyDataSetChanged();
                            break;
                        } else {
                            Log.i("DIALOG", "Poniedzialek: 4");
                            Toast.makeText(getApplication(), "W Poniedziałek są już zapisane lekcje", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    case "Wtorek": {
                        Log.i("DIALOG", "Wtorek: 1");
                        if (ts.od_time_list_1.size() != 0) {
                            Log.i("DIALOG", "Wtorek: 2");
                            if (ts.od_time_list_2.size() == 0) {
                                Log.i("DIALOG", "Wtorek: 3");
                                ts.od_time_list_2 = ts.getLesson(lesson_int, "od");
                                ts.do_time_list_2 = ts.getLesson(lesson_int, "do");
                                arrayList.add("Wtorek: ");
                                for (int i = 1; i <= lesson_int; i++) {
                                    Log.i("DIALOG", "Wtorek: 4:" + i);
                                    String[] od_times = ts.od_time_list_2.get(i - 1).split(":");
                                    String[] do_times = ts.do_time_list_2.get(i - 1).split(":");
                                    if (Integer.valueOf(od_times[0]) <= 9)
                                        od_times[0] = "0" + od_times[0];
                                    if (Integer.valueOf(od_times[1]) <= 9)
                                        od_times[1] = "0" + od_times[1];
                                    if (Integer.valueOf(do_times[0]) <= 9)
                                        do_times[0] = "0" + do_times[0];
                                    if (Integer.valueOf(do_times[1]) <= 9)
                                        do_times[1] = "0" + do_times[1];
                                    arrayList.add(" " + i + ". " + od_times[0] + ":" + od_times[1] + "-->" + do_times[0] + ":" + do_times[1]);
                                }
                                settings.clearArray("od", 2);
                                settings.clearArray("do", 2);
                                settings.saveArray("od", ts.od_time_list_2, 2);
                                settings.saveArray("do", ts.do_time_list_2, 2);
                                arrayList.add("");
                                adapter.notifyDataSetChanged();
                                break;
                            } else {
                                Log.i("DIALOG", "Wtorek: 5");
                                Toast.makeText(getApplication(), "W Wtorek są już zapisane lekcje", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        } else {
                            Log.i("DIALOG", "Wtorek: 6");
                            Toast.makeText(getApplication(), "Najpierw musisz dodać Poniedziałek", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    case "Środa": {
                        Log.i("DIALOG", "Sroda: 1");
                        if (ts.od_time_list_2.size() != 0) {
                            Log.i("DIALOG", "Sroda: 2");
                            if (ts.od_time_list_3.size() == 0) {
                                Log.i("DIALOG", "Sroda: 3");
                                ts.od_time_list_3 = ts.getLesson(lesson_int, "od");
                                ts.do_time_list_3 = ts.getLesson(lesson_int, "do");
                                arrayList.add("Środa: ");
                                for (int i = 1; i <= lesson_int; i++) {
                                    Log.i("DIALOG", "Sroda: 4:" + i);
                                    String[] od_times = ts.od_time_list_3.get(i - 1).split(":");
                                    String[] do_times = ts.do_time_list_3.get(i - 1).split(":");
                                    if (Integer.valueOf(od_times[0]) <= 9)
                                        od_times[0] = "0" + od_times[0];
                                    if (Integer.valueOf(od_times[1]) <= 9)
                                        od_times[1] = "0" + od_times[1];
                                    if (Integer.valueOf(do_times[0]) <= 9)
                                        do_times[0] = "0" + do_times[0];
                                    if (Integer.valueOf(do_times[1]) <= 9)
                                        do_times[1] = "0" + do_times[1];
                                    arrayList.add(" " + i + ". " + od_times[0] + ":" + od_times[1] + "-->" + do_times[0] + ":" + do_times[1]);
                                }
                                settings.clearArray("od", 3);
                                settings.clearArray("do", 3);
                                settings.saveArray("od", ts.od_time_list_3, 3);
                                settings.saveArray("do", ts.do_time_list_3, 3);
                                arrayList.add("");
                                adapter.notifyDataSetChanged();
                                break;
                            } else {
                                Log.i("DIALOG", "Sroda: 5");
                                Toast.makeText(getApplication(), "W Środę są już zapisane lekcje", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        } else {
                            Log.i("DIALOG", "Sroda: 6");
                            Toast.makeText(getApplication(), "Najpierw musisz dodać Wtorek", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    case "Czwartek": {
                        Log.i("DIALOG", "Czwartek: 1");
                        if (ts.od_time_list_3.size() != 0) {
                            Log.i("DIALOG", "Czwartek: 2");
                            if (ts.od_time_list_4.size() == 0) {
                                Log.i("DIALOG", "Czwartek: 3");
                                ts.od_time_list_4 = ts.getLesson(lesson_int, "od");
                                ts.do_time_list_4 = ts.getLesson(lesson_int, "do");
                                arrayList.add("Czwartek: ");
                                for (int i = 1; i <= lesson_int; i++) {
                                    Log.i("DIALOG", "Czwartek: 4:" + i);
                                    String[] od_times = ts.od_time_list_4.get(i - 1).split(":");
                                    String[] do_times = ts.do_time_list_4.get(i - 1).split(":");
                                    if (Integer.valueOf(od_times[0]) <= 9)
                                        od_times[0] = "0" + od_times[0];
                                    if (Integer.valueOf(od_times[1]) <= 9)
                                        od_times[1] = "0" + od_times[1];
                                    if (Integer.valueOf(do_times[0]) <= 9)
                                        do_times[0] = "0" + do_times[0];
                                    if (Integer.valueOf(do_times[1]) <= 9)
                                        do_times[1] = "0" + do_times[1];
                                    arrayList.add(" " + i + ". " + od_times[0] + ":" + od_times[1] + "-->" + do_times[0] + ":" + do_times[1]);
                                }
                                settings.clearArray("od", 4);
                                settings.clearArray("do", 4);
                                settings.saveArray("od", ts.od_time_list_4, 4);
                                settings.saveArray("do", ts.do_time_list_4, 4);
                                arrayList.add("");
                                adapter.notifyDataSetChanged();
                                break;
                            } else {
                                Log.i("DIALOG", "Czwartek: 5");
                                Toast.makeText(getApplication(), "W Czwartek są już zapisane lekcje", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        } else {
                            Log.i("DIALOG", "Czwartek: 6");
                            Toast.makeText(getApplication(), "Najpierw musisz dodać Środę", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    case "Piątek": {
                        Log.i("DIALOG", "Piatek: 1");
                        if (ts.od_time_list_4.size() != 0) {
                            Log.i("DIALOG", "Piatek: 2");
                            if (ts.od_time_list_5.size() == 0) {
                                Log.i("DIALOG", "Piatek: 3");
                                ts.od_time_list_5 = ts.getLesson(lesson_int, "od");
                                ts.do_time_list_5 = ts.getLesson(lesson_int, "do");
                                arrayList.add("Piątek: ");
                                for (int i = 1; i <= lesson_int; i++) {
                                    Log.i("DIALOG", "Piatek: 4:" + i);
                                    String[] od_times = ts.od_time_list_5.get(i - 1).split(":");
                                    String[] do_times = ts.do_time_list_5.get(i - 1).split(":");
                                    if (Integer.valueOf(od_times[0]) <= 9)
                                        od_times[0] = "0" + od_times[0];
                                    if (Integer.valueOf(od_times[1]) <= 9)
                                        od_times[1] = "0" + od_times[1];
                                    if (Integer.valueOf(do_times[0]) <= 9)
                                        do_times[0] = "0" + do_times[0];
                                    if (Integer.valueOf(do_times[1]) <= 9)
                                        do_times[1] = "0" + do_times[1];
                                    arrayList.add(" " + i + ". " + od_times[0] + ":" + od_times[1] + "-->" + do_times[0] + ":" + do_times[1]);
                                }
                                settings.clearArray("od", 5);
                                settings.clearArray("do", 5);
                                settings.saveArray("od", ts.od_time_list_5, 5);
                                settings.saveArray("do", ts.do_time_list_5, 5);
                                arrayList.add("");
                                adapter.notifyDataSetChanged();
                                break;
                            } else {
                                Log.i("DIALOG", "Piatek: 5");
                                Toast.makeText(getApplication(), "W Piątek są już zapisane lekcje", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        } else {
                            Log.i("DIALOG", "Piatek: 6");
                            Toast.makeText(getApplication(), "Najpierw musisz dodać Czwartek", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
                settings.saveString("Wait", String.valueOf(nt.wait));
                settings.saveString("Zmienna", nt.zmienna);
                addDialog.cancel();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addDialog.show();
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notification_dzwonek, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_clear_all) {
            nt.isCancle = true;
            arrayList.clear();
            adapter.notifyDataSetChanged();
            ts.od_time_list_1.clear();ts.do_time_list_1.clear();ts.od_time_list_2.clear();ts.do_time_list_2.clear();ts.od_time_list_3.clear();ts.do_time_list_3.clear();ts.od_time_list_4.clear();ts.do_time_list_4.clear();ts.od_time_list_5.clear();ts.do_time_list_5.clear();
            settings.clearArray("od", 1);settings.clearArray("do", 1);settings.clearArray("od", 2);settings.clearArray("do", 2);settings.clearArray("od", 3);settings.clearArray("do", 3);settings.clearArray("od", 4);settings.clearArray("do", 4);settings.clearArray("od", 5);settings.clearArray("do", 5);
            new Notifications(getApplication()).cancleNotification();
            Toast.makeText(getApplication(), "Poprawnie usunięto wszystko", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }
}