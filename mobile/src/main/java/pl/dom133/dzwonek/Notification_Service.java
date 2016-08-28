package pl.dom133.dzwonek;

import android.app.Application;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

public class Notification_Service extends Service {

    private Application app;
    private Notifications notification;
    private NotificationTask nt;
    private Time time = new Time();
    private SharedPreferences sPref;
    private ArrayList<String> arrayList = new ArrayList<>();
    private GoogleApi googleApi;


    public Notification_Service() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("INFO", "Notification onCreate");
        app = getApplication();
        nt = new NotificationTask();
        notification = new Notifications(getApplication());
        sPref = getSharedPreferences("Dzwonek", Context.MODE_PRIVATE);
        googleApi = new GoogleApi(getApplication());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("INFO", "Notification onDestroy");
        startService(new Intent(this, Notification_Service.class));
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("INFO", "Notification onStartCommand");
        if(intent!=null && intent.getAction() !=null && Objects.equals(intent.getAction(), "ACTION_STOP")) {
            Log.i("INFO", "Stop Service");
            nt.cancel(true);
            stopSelf();
            return START_NOT_STICKY;
        } else {
            //nt.cancel(true);
            try {
                Log.i("INFO", "Start notification task");
                nt.execute();
                return START_STICKY;
            } catch(Exception e) {
                Log.e("ERROR", e.getMessage());
                return START_STICKY;
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class NotificationTask extends AsyncTask<String, String, String> {
        private boolean running = false;


        @Override
        protected String doInBackground(String... strings) {
            running = true;
            while(!isCancelled()) {
                try {
                    int lesson = time.getLessonByTime(time.getTime().get(0), time.getTime().get(1)).get(0);
                    int last_lesson = time.getLessonByTime(time.getTime().get(0), time.getTime().get(1)).get(1);
                    int day = time.getDay();
                    Log.i("INFO", "Day: " + day + " Lesson: " + lesson +" Last: "+last_lesson+ " Time: " + time.getTime().get(0) + ":" + time.getTime().get(1) + ":" + time.getTime().get(2));
                    if (day >= 1 || day <= 5 && sPref.contains("day_" + day)) {
                        Log.i("INFO", "Day: " + day);
                        if (lesson <= sPref.getInt("day_" + day, 1) && lesson != 10 && lesson != 0 && time.getTime().get(0) >= time.getLessonArray(lesson, "od").get(0) && (time.getTime().get(1) < time.getLessonArray(lesson, "do").get(1) || time.getTime().get(1) > time.getLessonArray(lesson, "do").get(1))) {
                            int hour = (time.getLessonArray(lesson, "do").get(0) - time.getTime().get(0));
                            int minuts = (time.getLessonArray(lesson, "do").get(1) - time.getTime().get(1)) + (hour * 60);
                            int sec = 60-time.getTime().get(2);

                            arrayList.add(String.valueOf(minuts)); arrayList.add(String.valueOf(sec));

                            Log.i("INFO", "Time: " + hour + ":" + minuts + ":" + sec);
                            if (minuts > 1) {
                                if(sec!=60 ){notification.sendNotification("Pozostało: " + minuts + "min", "Do dzwonka pozostało: " + (minuts-1) + ":" + time.getString(sec) + "min", minuts, sec); arrayList.add("Pozostało: " + minuts + "min"); arrayList.add("Do dzwonka pozostało: " + (minuts-1) + ":" + time.getString(sec) + "min");}
                                else {notification.sendNotification("Pozostało: " + minuts + "min", "Do dzwonka pozostało: " + minuts+"min", minuts, sec); arrayList.add("Pozostało: " + minuts + "min"); arrayList.add("Do dzwonka pozostało: " + minuts+"min");}
                            } else if (minuts == 1 && sec < 60) {notification.sendNotification("Pozostało: " + time.getString(sec) + "sec", "Do dzwonka pozostało: " + time.getString(sec) + "sec", minuts, sec); arrayList.add("Pozostało: " + time.getString(sec) + "sec"); arrayList.add("Do dzwonka pozostało: " + time.getString(sec) + "sec");
                            } else {notification.cancleNotification(1);arrayList.add("");}
                        } else if(last_lesson != 0 && lesson==10 && last_lesson < sPref.getInt("day_" + day, 1) && time.getTime().get(1) >= time.getLessonArray(last_lesson, "do").get(1)) {
                            int minuts = (time.getLessonArray(last_lesson + 1, "od").get(1) - time.getTime().get(1));
                            int sec = 60 - time.getTime().get(2);
                            Log.i("INFO", "Time: " + minuts + ":" + sec);
                            if (minuts > 1) {
                                if (sec != 60) {notification.sendNotification("Pozostało: " + minuts + "min", "Do dzwonka pozostało: " + (minuts - 1) + ":" + time.getString(sec) + "min", minuts, sec); arrayList.add("Pozostało: " + minuts + "min"); arrayList.add("Do dzwonka pozostało: " + (minuts - 1) + ":" + time.getString(sec) + "min");}
                                else {notification.sendNotification("Pozostało: " + minuts + "min", "Do dzwonka pozostało: " + minuts + "min", minuts, sec); arrayList.add("Pozostało: " + minuts + "min"); arrayList.add("Do dzwonka pozostało: " + minuts + "min");}
                            } else if (minuts == 1 && sec < 60) {notification.sendNotification("Pozostało: " + time.getString(sec) + "sec", "Do dzwonka pozostało: " + time.getString(sec) + "sec", minuts, sec); arrayList.add("Pozostało: " + time.getString(sec) + "sec"); arrayList.add("Do dzwonka pozostało: " + time.getString(sec) + "sec");}
                            else {notification.cancleNotification(1); arrayList.add("");}
                        } else {notification.cancleNotification(1); arrayList.add("");}
                    } else {notification.cancleNotification(1); arrayList.add("");}

                    googleApi.sendData("notification", arrayList);
                    Thread.sleep(sPref.getInt("wait", 1000));
                    arrayList.clear();
                } catch(Exception e) {
                    Log.e("ERROR", e.getMessage());
                }
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            running = false;
        }
    }
}
