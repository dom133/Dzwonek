package pl.dom133.dzwonek;

import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import java.util.Objects;

public class Notification_Service extends Service {

    private Application app;

    public Notification_Service() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplication();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(Objects.equals(intent.getAction(), "ACTION_STOP")) {
            stopSelf();
            return START_NOT_STICKY;
        } else {
            return START_STICKY;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class NotificationTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
