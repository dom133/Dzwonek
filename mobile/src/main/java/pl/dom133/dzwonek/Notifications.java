package pl.dom133.dzwonek;

import android.app.Application;
import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dom133 on 24.03.2016.
 */
public class Notifications {

    Application app;

    public Notifications(Application app)
    {
        this.app = app;
    }

    public void sendNotification(String title, String txt)
    {
        Notification notification = new NotificationCompat.Builder(app)
                .setSmallIcon(R.drawable.ic_setting_light)
                .setPriority(2)
                .setContentTitle(title)
                .setContentText(txt)
                .extend(
                        new NotificationCompat.WearableExtender()
                                .setHintShowBackgroundOnly(true))
                                .setPriority(2)
                                .setSmallIcon(R.drawable.mr_ic_play_light)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(app);

        int notificationId = 1;
        notificationManager.notify(notificationId, notification);
    }
}
