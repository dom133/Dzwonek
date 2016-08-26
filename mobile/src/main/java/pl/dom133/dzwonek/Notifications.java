package pl.dom133.dzwonek;


import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class Notifications {

    Application app;

    public Notifications(Application app) {
        this.app = app;
    }

    public void cancleNotification(int id) {
        NotificationManagerCompat nM = NotificationManagerCompat.from(app);
        nM.cancel(id);
    }

    public void sendNotification(String title, String contents, int minuts, int sec) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(app)
                .setSmallIcon(R.drawable.ic_dzwonek)
                .setContentTitle(title)
                .setContentText(contents)
                .setOngoing(true)
                .setPriority(2);

        switch(minuts) {
            case 10:{
                if(sec!=60){break;}
                else {notification.setVibrate(new long[]{500, 3000}); break;}
            }

            case 5: {
                if(sec!=60){break;}
                else {notification.setVibrate(new long[]{500, 2000}); break;}
            }

            case 1: {
                if(sec!=60){break;}
                else {notification.setVibrate(new long[]{500, 1000}); break;}
            }
        }

        NotificationManager notificationManager = (NotificationManager) app.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification.build());

    }
}
