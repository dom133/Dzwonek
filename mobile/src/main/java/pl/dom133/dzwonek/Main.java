package pl.dom133.dzwonek;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Main extends AppCompatActivity {


    private ArrayAdapter<String> adapter_list = null;
    private ArrayList<String> arrayList = new ArrayList<>();
    private Time time = new Time();
    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Components
        ListView list = (ListView) findViewById(R.id.list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_button);
        sPref = getSharedPreferences("Dzwonek", Context.MODE_PRIVATE);

        adapter_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(adapter_list);

        //Dialog
        LayoutInflater factory = LayoutInflater.from(this);
        final View addDialogView = factory.inflate(R.layout.add_dialog, null);
        final AlertDialog addDialog = new AlertDialog.Builder(this).create();
        addDialog.setView(addDialogView);


        addDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                //Dialog Spinner
                Spinner day_spinner = (Spinner) addDialogView.findViewById(R.id.days_spinner);
                Spinner lesson_spinner = (Spinner) addDialogView.findViewById(R.id.lesson_spinner);
                ArrayAdapter<CharSequence> day_adapter = ArrayAdapter.createFromResource(getApplication(), R.array.days_array, android.R.layout.simple_spinner_item);
                ArrayAdapter<CharSequence> lesson_adapter = ArrayAdapter.createFromResource(getApplication(), R.array.lessons_array, android.R.layout.simple_spinner_item);
                day_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lesson_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                day_spinner.setAdapter(day_adapter);
                lesson_spinner.setAdapter(lesson_adapter);
            }
        });

        addDialogView.findViewById(R.id.dialog_add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner day_spinner = (Spinner) addDialogView.findViewById(R.id.days_spinner);
                Spinner lesson_spinner = (Spinner) addDialogView.findViewById(R.id.lesson_spinner);
                Log.i("INFO", "DAY: "+day_spinner.getSelectedItem().toString()+" LESSON: "+lesson_spinner.getSelectedItem().toString());
                switch(day_spinner.getSelectedItem().toString()) {
                    case "Poniedziałek":{
                        Log.i("INFO", "PON 1");
                        if(!sPref.contains("day_1")) {
                            Log.i("INFO", "PON 2");
                            arrayList.add("Poniedziałek: ");
                            arrayList.addAll(time.getLesson(Integer.parseInt(lesson_spinner.getSelectedItem().toString())));
                            adapter_list.notifyDataSetChanged();
                            sPref.edit().putInt("day_1", Integer.parseInt(lesson_spinner.getSelectedItem().toString())).commit();
                            Toast.makeText(getApplication(), "Poprawnie dodano lekcje", Toast.LENGTH_SHORT).show();
                        } else {Log.i("INFO", "PON 3");Toast.makeText(getApplication(), "W poniedziałek są już dodane lekcje", Toast.LENGTH_SHORT).show();}
                        break;
                    }

                    case "Wtorek":{
                        Log.i("INFO", "WTO 1");
                        if(sPref.contains("day_1")) {
                            Log.i("INFO", "WTO 2");
                            if(!sPref.contains("day_2")) {
                                Log.i("INFO", "WTO 3");
                                arrayList.add("Wtorek: ");
                                arrayList.addAll(time.getLesson(Integer.parseInt(lesson_spinner.getSelectedItem().toString())));
                                adapter_list.notifyDataSetChanged();
                                sPref.edit().putInt("day_2", Integer.parseInt(lesson_spinner.getSelectedItem().toString())).commit();
                                Toast.makeText(getApplication(), "Poprawnie dodano lekcje", Toast.LENGTH_SHORT).show();
                            } else {Log.i("INFO", "WTO 4");Toast.makeText(getApplication(), "W wtorek są już dodane lekcje", Toast.LENGTH_SHORT).show();}
                        } else {Log.i("INFO", "WTO 5");Toast.makeText(getApplication(), "Najpierw musisz dodać lekcje w poniedziałek", Toast.LENGTH_SHORT).show();}
                        break;
                    }

                    case "Środa":{
                        Log.i("INFO", "SRO 1");
                        if(sPref.contains("day_2")) {
                            Log.i("INFO", "SRO 2");
                            if(!sPref.contains("day_3")) {
                                Log.i("INFO", "SRO 3");
                                arrayList.add("Środa: ");
                                arrayList.addAll(time.getLesson(Integer.parseInt(lesson_spinner.getSelectedItem().toString())));
                                adapter_list.notifyDataSetChanged();
                                sPref.edit().putInt("day_3", Integer.parseInt(lesson_spinner.getSelectedItem().toString())).commit();
                                Toast.makeText(getApplication(), "Poprawnie dodano lekcje", Toast.LENGTH_SHORT).show();
                            } else {Log.i("INFO", "SRO 4");Toast.makeText(getApplication(), "W środę są już dodane lekcje", Toast.LENGTH_SHORT).show();}
                        } else {Log.i("INFO", "SRO 5");Toast.makeText(getApplication(), "Najpierw musisz dodać lekcje w wtorek", Toast.LENGTH_LONG).show();}
                        break;
                    }

                    case "Czwartek":{
                        Log.i("INFO", "CZW 1");
                        if(sPref.contains("day_3")) {
                            Log.i("INFO", "CZW 2");
                            if(!sPref.contains("day_4")) {
                                Log.i("INFO", "CZW 3");
                                arrayList.add("Czwartek: ");
                                arrayList.addAll(time.getLesson(Integer.parseInt(lesson_spinner.getSelectedItem().toString())));
                                adapter_list.notifyDataSetChanged();
                                sPref.edit().putInt("day_4", Integer.parseInt(lesson_spinner.getSelectedItem().toString())).commit();
                                Toast.makeText(getApplication(), "Poprawnie dodano lekcje", Toast.LENGTH_SHORT).show();
                            } else {Log.i("INFO", "CZW 4");Toast.makeText(getApplication(), "W czwartek są już dodane lekcje", Toast.LENGTH_SHORT).show();}
                        } else {Log.i("INFO", "CZW 5");Toast.makeText(getApplication(), "Najpierw musisz dodać lekcje w środę", Toast.LENGTH_LONG).show();}
                        break;
                    }

                    case "Piątek":{
                        Log.i("INFO", "PIA 1");
                        if(sPref.contains("day_4")) {
                            Log.i("INFO", "PIA 2");
                            if(!sPref.contains("day_5")) {
                                Log.i("INFO", "PIA 3");
                                arrayList.add("Piątek: ");
                                arrayList.addAll(time.getLesson(Integer.parseInt(lesson_spinner.getSelectedItem().toString())));
                                adapter_list.notifyDataSetChanged();
                                sPref.edit().putInt("day_5", Integer.parseInt(lesson_spinner.getSelectedItem().toString())).commit();
                                Toast.makeText(getApplication(), "Poprawnie dodano lekcje", Toast.LENGTH_SHORT).show();
                            } else {Log.i("INFO", "PIA 4");Toast.makeText(getApplication(), "W piątek są już dodane lekcje", Toast.LENGTH_SHORT).show();}
                        } else {Log.i("INFO", "PIA 5");Toast.makeText(getApplication(), "Najpierw musisz dodać lekcje w piątek", Toast.LENGTH_LONG).show();}
                        break;
                    }

                }
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
            Toast.makeText(getApplication(), "Poprawnie usunięto wszytskie dni", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void LoadList(int day) {
        if(sPref.contains("day_"+day)) {
            if(day==1) {arrayList.add("Poniedziałek: ");}
            else if(day==2) {arrayList.add("Wtorek: ");}
            else if(day==3) {arrayList.add("Środa: ");}
            else if(day==4) {arrayList.add("Czwartek: ");}
            else if(day==5) {arrayList.add("Piątek: ");}
            arrayList.addAll(time.getLesson(sPref.getInt("day_" + day, 1)));
            adapter_list.notifyDataSetChanged();
        }
    }
}
