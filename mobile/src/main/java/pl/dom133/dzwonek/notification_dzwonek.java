package pl.dom133.dzwonek;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.File;
import java.util.ArrayList;

public class notification_dzwonek extends AppCompatActivity {

    //Variables private
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    //Variables public
    public int count = 1;
    Times ts = new Times();
    File file;
    FileXML fileXML;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_dzwonek);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView vw = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        vw.setAdapter(adapter);

        //Notification BackgroundTask
        NotificationTask nt = new NotificationTask(getApplication(), ts);
        nt.execute();

        file = new File(this.getFilesDir(), "list.xml");
        fileXML = new FileXML(this.getApplication());

        if(file.exists())
        {

        } else { fileXML.createXML(); }

        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(
                R.layout.add_times_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(deleteDialogView);

        deleteDialogView.findViewById(R.id.od_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment((EditText) deleteDialog.findViewById(R.id.od_time), ts);
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        deleteDialogView.findViewById(R.id.do_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment((EditText)deleteDialog.findViewById(R.id.do_time), ts);
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        deleteDialogView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText od_time = (EditText)deleteDialogView.findViewById(R.id.od_time);
                EditText do_time = (EditText)deleteDialogView.findViewById(R.id.do_time);
                if(!od_time.getText().toString().isEmpty() && !do_time.getText().toString().isEmpty()) {
                    String[] od_time_split = od_time.getText().toString().split(":");
                    String[] do_time_split = do_time.getText().toString().split(":");
                    String od_time_string = null;
                    String do_time_string = null;
                    if (od_time_split[0].charAt(0) == '0') {
                        if (od_time_split[1].charAt(0) == '0') {
                            od_time_string = od_time_split[0].charAt(1) + ":" + od_time_split[1].charAt(1);
                        } else {
                            od_time_string = od_time_split[0].charAt(1) + ":" + od_time_split[1];
                        }
                    } else {
                        if (od_time_split[1].charAt(0) == '0') {
                            od_time_string = od_time_split[0] + ":" + od_time_split[1].charAt(1);
                        } else {
                            od_time_string = od_time_split[0] + ":" + od_time_split[1];
                        }
                    }

                    if (do_time_split[0].charAt(0) == '0') {
                        if (do_time_split[1].charAt(0) == '0') {
                            do_time_string = do_time_split[0].charAt(1) + ":" + do_time_split[1].charAt(1);
                        } else {
                            do_time_string = do_time_split[0].charAt(1) + ":" + do_time_split[1];
                        }
                    } else {
                        if (do_time_split[1].charAt(0) == '0') {
                            do_time_string = do_time_split[0] + ":" + do_time_split[1].charAt(1);
                        } else {
                            do_time_string = do_time_split[0] + ":" + do_time_split[1];
                        }
                    }

                    ts.od_time_list.add(od_time_string);
                    ts.do_time_list.add(do_time_string);
                    arrayList.add("Od: " + od_time.getText().toString() + " Do: " + do_time.getText().toString());
                    adapter.notifyDataSetChanged();
                    deleteDialog.cancel();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog.show();
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification_dzwonek, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "notification_dzwonek Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://pl.dom133.dzwonek/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "notification_dzwonek Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://pl.dom133.dzwonek/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
