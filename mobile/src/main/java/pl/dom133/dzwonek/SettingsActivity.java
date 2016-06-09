package pl.dom133.dzwonek;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class SettingsActivity extends Activity {


    Settings settings = new Settings(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView zmienna = (TextView)findViewById(R.id.zmienna_txt);
                settings.saveString("Zmienna", zmienna.getText().toString());
                Log.i("SETTINGS", "Zmienna: "+settings.getString("Zmienna"));
                Toast.makeText(getApplication(), "Ustawienia zapisane", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
