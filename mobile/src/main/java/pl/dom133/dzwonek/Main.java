package pl.dom133.dzwonek;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.multidex.MultiDex;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;

public class Main extends AppCompatActivity{

    private Resources res;
    private ArrayAdapter<String> adapter_list = null;
    private ArrayList<String> arrayList = new ArrayList<>();
    private Time time;
    private SharedPreferences sPref;
    private GoogleApi googleApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Components
        res = getResources();
        ListView list = (ListView) findViewById(R.id.list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_button);
        sPref = getSharedPreferences("Dzwonek", Context.MODE_PRIVATE);
        time = new Time(sPref);

        adapter_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(adapter_list);

        //Dialog
        LayoutInflater factory = LayoutInflater.from(this);
        final View addDialogView = factory.inflate(R.layout.add_dialog, null);
        final AlertDialog addDialog = new AlertDialog.Builder(this).create();
        addDialog.setView(addDialogView);
        addDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        final Spinner lesson_spinner = (Spinner) addDialogView.findViewById(R.id.lesson_spinner);
        final Spinner type_spinner = (Spinner) addDialogView.findViewById(R.id.type_spinner);

        addDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                //Dialog Spinner
                Spinner day_spinner = (Spinner) addDialogView.findViewById(R.id.days_spinner);
                ArrayAdapter<CharSequence> day_adapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.days_array, android.R.layout.simple_spinner_item);
                ArrayAdapter<CharSequence> lesson_adapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.lessons_array_0, android.R.layout.simple_spinner_item);
                ArrayAdapter<CharSequence> type_adapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.type_array, android.R.layout.simple_spinner_item);
                //Layout
                LinearLayout type_layout = (LinearLayout) addDialogView.findViewById(R.id.type_layout);

                if(arrayList.size()>1) {type_layout.setVisibility(View.GONE);}
                else {type_layout.setVisibility(View.VISIBLE);}

                day_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lesson_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                type_spinner.setAdapter(type_adapter);
                day_spinner.setAdapter(day_adapter);
                lesson_spinner.setAdapter(lesson_adapter);
                type_spinner.setSelection(sPref.getInt("les_type", 0));

                if(sPref.getInt("les_type", 0)==0) {
                    lesson_adapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.lessons_array_0, android.R.layout.simple_spinner_item);
                    lesson_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    lesson_spinner.setAdapter(lesson_adapter); lesson_adapter.notifyDataSetChanged();
                } else {
                    lesson_adapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.lessons_array_1, android.R.layout.simple_spinner_item);
                    lesson_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    lesson_spinner.setAdapter(lesson_adapter); lesson_adapter.notifyDataSetChanged();
                }
            }
        });

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sPref.edit().putInt("les_type", i).commit();
                if(i==0) {
                    ArrayAdapter<CharSequence> lesson_adapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.lessons_array_0, android.R.layout.simple_spinner_item);
                    lesson_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    lesson_spinner.setAdapter(lesson_adapter); lesson_adapter.notifyDataSetChanged();
                } else {
                    ArrayAdapter<CharSequence> lesson_adapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.lessons_array_1, android.R.layout.simple_spinner_item);
                    lesson_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    lesson_spinner.setAdapter(lesson_adapter); lesson_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addDialogView.findViewById(R.id.dialog_add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner day_spinner = (Spinner) addDialogView.findViewById(R.id.days_spinner);
                Spinner lesson_spinner = (Spinner) addDialogView.findViewById(R.id.lesson_spinner);
                String days_array[] = res.getStringArray(R.array.days_array);
                Log.i("INFO", "DAY: "+day_spinner.getSelectedItem().toString()+" LESSON: "+lesson_spinner.getSelectedItem().toString());
                long day = day_spinner.getSelectedItemId()+1;
                if(day_spinner.getSelectedItemId()==0) {
                    if(!sPref.contains("day_"+day)) {
                        arrayList.add(day_spinner.getSelectedItem().toString()+": ");
                        arrayList.addAll(time.getLesson(Integer.parseInt(lesson_spinner.getSelectedItem().toString()), sPref.getInt("les_type", 0)));
                        adapter_list.notifyDataSetChanged();
                        sPref.edit().putInt("day_"+day, Integer.parseInt(lesson_spinner.getSelectedItem().toString())).commit();
                        Toast.makeText(getApplication(), "Poprawnie dodano lekcje", Toast.LENGTH_SHORT).show();
                    } else {Toast.makeText(getApplication(), "W "+day_spinner.getSelectedItem().toString().toLowerCase()+" są już dodane lekcje", Toast.LENGTH_SHORT).show();}
                } else {
                    if(sPref.contains("day_"+(day-1))) {
                        if(!sPref.contains("day_"+day)) {
                            arrayList.add(day_spinner.getSelectedItem().toString()+": ");
                            arrayList.addAll(time.getLesson(Integer.parseInt(lesson_spinner.getSelectedItem().toString()), sPref.getInt("les_type", 0)));
                            adapter_list.notifyDataSetChanged();
                            sPref.edit().putInt("day_"+day, Integer.parseInt(lesson_spinner.getSelectedItem().toString())).commit();
                            Toast.makeText(getApplication(), "Poprawnie dodano lekcje", Toast.LENGTH_SHORT).show();
                        } else {Toast.makeText(getApplication(), "W "+day_spinner.getSelectedItem().toString().toLowerCase()+" są już dodane lekcje", Toast.LENGTH_SHORT).show();}
                    } else {Toast.makeText(getApplication(), "Najpierw musisz dodać lekcje w "+days_array[(int)(day_spinner.getSelectedItemId()-1)].toLowerCase(), Toast.LENGTH_SHORT).show();}
                }
                restartService();
                googleApi.sendData("arrayList", arrayList);
                addDialog.cancel();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog.show();
            }
        });

        //Load List
        for(int i=1; i<=5; i++) {LoadList(i);}

        startService(new Intent(this, Notification_Service.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApi = new GoogleApi(getApplication());
        googleApi.sendData("arrayList", arrayList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_settings) {
            startActivity(new Intent(this, Settings.class));
        } else if(id==R.id.action_clear) {
            for(int i=1; i<=5; i++) {sPref.edit().remove("day_"+i).commit();}
            arrayList.clear();
            adapter_list.notifyDataSetChanged();
            googleApi.sendData("arrayList", arrayList);
            restartService();
            Toast.makeText(getApplication(), "Poprawnie usunięto wszytskie dni", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    /*@Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(Main.this);
    }*/

    public void LoadList(int day) {
        if(sPref.contains("day_"+day)) {
            if(day==1) {arrayList.add("Poniedziałek: ");}
            else if(day==2) {arrayList.add("Wtorek: ");}
            else if(day==3) {arrayList.add("Środa: ");}
            else if(day==4) {arrayList.add("Czwartek: ");}
            else if(day==5) {arrayList.add("Piątek: ");}
            arrayList.addAll(time.getLesson(sPref.getInt("day_" + day, 1), sPref.getInt("les_type", 0)));
            adapter_list.notifyDataSetChanged();
        }
    }

    public void restartService() {
        Intent intent = new Intent(getApplication(), Notification_Service.class);
        intent.setAction("ACTION_STOP");
        startService(intent);
        startService(new Intent(getApplication(), Notification_Service.class));
    }

}