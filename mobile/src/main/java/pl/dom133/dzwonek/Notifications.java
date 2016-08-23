package pl.dom133.dzwonek;


import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class Notifications {

    Application app;
    int i=0;

    public Notifications(Application app) {
        this.app = app;
    }

    public void cancleNotification(int id) {
        NotificationManagerCompat nM = NotificationManagerCompat.from(app);
        nM.cancel(id);
    }

    public void sendNotification(String title, String contents, int minuts) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(app)
                .setSmallIcon(R.drawable.ic_dzwonek)
                .setContentTitle(title)
                .setContentText(contents)
                .setOngoing(true)
                .setPriority(2);

        switch(minuts) {
            case 10:{
                if(i!=0){break;}
                else {notification.setVibrate(new long[]{500, 3000}); i++; break;}
            }

            case 5: {
                if(i!=1){break;}
                else {notification.setVibrate(new long[]{500, 2000}); i++; break;}
            }

            case 1: {
                if(i!=2){break;}
                else {notification.setVibrate(new long[]{500, 1000}); i++; break;}
            }
        }

        NotificationManager notificationManager = (NotificationManager) app.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification.build());

    }
}
