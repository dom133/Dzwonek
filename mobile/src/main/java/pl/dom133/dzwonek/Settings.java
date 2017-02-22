package pl.dom133.dzwonek;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Settings extends AppCompatActivity {

    private SharedPreferences sPref;
    private List<String> t_list = new ArrayList<>();
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){actionBar.setDisplayHomeAsUpEnabled(true); actionBar.setTitle("Ustawienia");}

        sPref = getSharedPreferences("Dzwonek", Context.MODE_PRIVATE);

        //EditText
        final EditText h_text = (EditText) findViewById(R.id.h_text);
        final EditText m_text = (EditText) findViewById(R.id.m_text);
        final EditText s_text = (EditText) findViewById(R.id.s_text);

        //Spinners
        Spinner time_spinner = (Spinner) findViewById(R.id.time_spinner);
        ArrayAdapter<CharSequence> time_adapter = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_spinner_item);
        time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(time_adapter);

        //Load Settings
        time_spinner.setSelection(sPref.getInt("ts_pos", 0));
        if(sPref.contains("time")) {
            Log.i("INFO", "Load Settings");
            List<String> list = Arrays.asList(gson.fromJson(sPref.getString("time", null), String[].class));
            h_text.setText(list.get(0)); m_text.setText(list.get(1)); s_text.setText(list.get(2));
            t_list.clear();t_list.addAll(list);
        } else {t_list.add("");t_list.add("");t_list.add("");}

        time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("INFO", "Time spinner selected: "+i);

                sPref.edit().putInt("ts_pos", i).commit();

                switch (i) {
                    case 0: {
                        sPref.edit().putInt("wait", 1000).commit();
                        break;
                    }

                    case 1: {
                        sPref.edit().putInt("wait", (1000*60)).commit();
                        break;
                    }

                    case 2: {
                        sPref.edit().putInt("wait", (1000*60)*5).commit();
                        break;
                    }

                    case 3: {
                        sPref.edit().putInt("wait", (1000*60)*10).commit();
                        break;
                    }

                    case 4: {
                        sPref.edit().putInt("wait", (1000*60)*15).commit();
                        break;
                    }

                    case 5: {
                        sPref.edit().putInt("wait", (1000*60)*30).commit();
                        break;
                    }

                    case 6: {
                        sPref.edit().putInt("wait", (1000*60)*60).commit();
                        break;
                    }

                    case 7: {
                        sPref.edit().putInt("wait", (1000*60)*120).commit();
                        break;
                    }

                    case 8: {
                        sPref.edit().putInt("wait", (1000*60)*240).commit();
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Save editext
        h_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {checkText(h_text, 23, 0);}
                catch(Exception e) {Log.e("ERROR", e.getMessage()); t_list.add(0, "0");}
            }
        });

        m_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {checkText(m_text, 59, 1);}
                catch(Exception e) {Log.e("ERROR", e.getMessage()); t_list.add(1, "0");}
            }
        });

        s_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {checkText(s_text, 59, 2);}
                catch(Exception e) {Log.e("ERROR", e.getMessage()); t_list.add(2, "0");}
            }
        });

    }

    public void checkText(EditText text, int maxValue, int index) {
        StringBuilder stext = new StringBuilder(text.getText().toString());
        if (Integer.valueOf(stext.toString()) > maxValue) {
            text.setError("Ta wartość nie może być większa niż "+maxValue); t_list.add(index, "0");
        } else {
            t_list.add(index, text.getText().toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("INFO", "Save Settings"); sPref.edit().putString("time", gson.toJson(t_list)).commit(); t_list.clear();
        Intent intent = new Intent(getApplication(), Notification_Service.class);
        intent.setAction("ACTION_STOP");
        startService(intent);
        startService(new Intent(getApplication(), Notification_Service.class));
        startActivity(new Intent(this, Main.class));
        this.finish();
        return true;
    }
}