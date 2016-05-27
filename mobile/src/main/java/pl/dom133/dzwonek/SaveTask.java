package pl.dom133.dzwonek;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.Task;

/**
 * Created by dom133 on 26.05.2016.
 */
public class SaveTask extends AsyncTask<Void, Void, Void> {

    Settings settings;
    NotificationTask nt;
    Times ts;
    Application app;

    public SaveTask(Settings settings, NotificationTask nt, Times ts, Application app) {
        this.settings = settings;
        this.nt = nt;
        this.ts = ts;
        this.app = app;
    }

    @Override
    protected Void doInBackground(Void... test) {
        while(true) {
            try {
                nt.isCancle = true;
                if (ts.od_time_list_1.size() != 0) {
                    settings.clearArray("od", 1);
                    settings.clearArray("do", 1);
                    settings.saveArray("od", ts.od_time_list_1, 1);
                    settings.saveArray("do", ts.do_time_list_1, 1);
                    Log.i("MAIN", "Save  settings 1");
                } else {
                    settings.clearArray("od", 1);
                    settings.clearArray("do", 1);
                    Log.i("MAIN", "Clear  settings 1");
                }
                if (ts.od_time_list_2.size() != 0) {
                    settings.clearArray("od", 2);
                    settings.clearArray("do", 2);
                    settings.saveArray("od", ts.od_time_list_2, 2);
                    settings.saveArray("do", ts.do_time_list_2, 2);
                    Log.i("MAIN", "Save  settings 2");
                } else {
                    settings.clearArray("od", 2);
                    settings.clearArray("do", 2);
                    Log.i("MAIN", "Clear  settings 2");
                }
                if (ts.od_time_list_3.size() != 0) {
                    settings.clearArray("od", 3);
                    settings.clearArray("do", 3);
                    settings.saveArray("od", ts.od_time_list_3, 3);
                    settings.saveArray("do", ts.do_time_list_3, 3);
                    Log.i("MAIN", "Save  settings 3");
                } else {
                    settings.clearArray("od", 3);
                    settings.clearArray("do", 3);
                    Log.i("MAIN", "Clear  settings 3");
                }
                if (ts.od_time_list_4.size() != 0) {
                    settings.clearArray("od", 4);
                    settings.clearArray("do", 4);
                    settings.saveArray("od", ts.od_time_list_4, 4);
                    settings.saveArray("do", ts.do_time_list_4, 4);
                    Log.i("MAIN", "Save  settings 4");
                } else {
                    settings.clearArray("od", 4);
                    settings.clearArray("do", 4);
                    Log.i("MAIN", "Clear  settings 4");
                }
                if (ts.od_time_list_5.size() != 0) {
                    settings.clearArray("od", 5);
                    settings.clearArray("do", 5);
                    settings.saveArray("od", ts.od_time_list_5, 5);
                    settings.saveArray("do", ts.do_time_list_5, 5);
                    Log.i("MAIN", "Save settings 5");
                } else {
                    settings.clearArray("od", 5);
                    settings.clearArray("do", 5);
                    Log.i("MAIN", "Clear  settings 5");
                }
                new Notifications(app).cancleNotification();
                settings.saveString("Wait", String.valueOf(nt.wait));
                settings.saveString("Zmienna", nt.zmienna);
                Log.i("MAIN", "Save all settings");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
