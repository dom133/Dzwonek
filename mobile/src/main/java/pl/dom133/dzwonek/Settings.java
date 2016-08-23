package pl.dom133.dzwonek;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Settings extends AppCompatActivity {

    private SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){actionBar.setDisplayHomeAsUpEnabled(true); actionBar.setTitle("Ustawienia");}

        sPref = getSharedPreferences("Dzwonek", Context.MODE_PRIVATE);

        //Spinners
        Spinner time_spinner = (Spinner) findViewById(R.id.time_spinner);
        ArrayAdapter<CharSequence> time_adapter = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_spinner_item);
        time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(time_adapter);

        //Load position spinner
        time_spinner.setSelection(sPref.getInt("ts_pos", 0));

        time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("INFO", "Time spinner selected: "+i);
                sPref.edit().putInt("ts_pos", i).commit();

                switch (i) {
                    case 0: {
                        sPref.edit().putInt("wait", (1000*60)).commit();
                        break;
                    }

                    case 1: {
                        sPref.edit().putInt("wait", (1000*60)*5).commit();
                        break;
                    }

                    case 2: {
                        sPref.edit().putInt("wait", (1000*60)*10).commit();
                        break;
                    }

                    case 3: {
                        sPref.edit().putInt("wait", (1000*60)*15).commit();
                        break;
                    }

                    case 4: {
                        sPref.edit().putInt("wait", (1000*60)*30).commit();
                        break;
                    }

                    case 5: {
                        sPref.edit().putInt("wait", (1000*60)*60).commit();
                        break;
                    }

                    case 6: {
                        sPref.edit().putInt("wait", (1000*60)*120).commit();
                        break;
                    }

                    case 7: {
                        sPref.edit().putInt("wait", (1000*60)*240).commit();
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, Main.class));
        return true;
    }
}
