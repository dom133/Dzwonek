package pl.dom133.dzwonek;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.logging.Logger;

public class NotificationTask extends AsyncTask<Void, Void, Void> {

    //Private
    private Application app;
    private Times ts;
    private int i=0;
    //Public
    public boolean isCancle = false;
    public int wait = 1000;
    public int zmienna = 0;


    public NotificationTask(Application app, Times ts)
    {
        this.app = app;
        this.ts = ts;
    }

    @Override

    protected Void doInBackground(Void... test) {
            try {
                while (true) {
                    if (ts.od_time_list.isEmpty() == false) {
                        while (true) {
                            int size = ts.od_time_list.size();
                            if (i > (size - 1)) {
                                i = 0;
                                break;
                            }

                            String[] od_times = ts.od_time_list.get(i).split(":");
                            String[] do_times = ts.do_time_list.get(i).split(":");
                            Notifications nf = new Notifications(app);

                            Log.i("NOTIFICATION_TASK", "1. " + i + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1) + " " + od_times[0] + ":" + od_times[1] + " " + do_times[0] + ":" + do_times[1]);
                            if ((ts.getTime().get(0) >= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1])) || (ts.getTime().get(0) <= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]))) {
                                while (true) {
                                    if(isCancle==true) {isCancle = false; break;}
                                    Log.i("NOTIFICATION_TASK", "2. " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                    int hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                    int minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1);
                                    minuts = minuts + (hour * 60) - zmienna;
                                    int sec = (minuts * 60) - ts.getTime().get(2);
                                    if (minuts <= 0) {
                                        i = i + 1;
                                        nf.cancleNotification();
                                        break;
                                    }

                                    if (sec == 1)
                                        nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostała " + sec + " sekunda", minuts);
                                    else if (sec >= 2 && sec < 5)
                                        nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostały " + sec + " sekundy", minuts);
                                    else if (sec >= 5 && sec <= 59)
                                        nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostało " + sec + " sekund", minuts);
                                    else if (minuts == 1)
                                        nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostała " + minuts + " minuta", minuts);
                                    else if (minuts >= 2 && minuts < 5)
                                        nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostały " + minuts + " minuty", minuts);
                                    else if (minuts >= 5)
                                        nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostało " + minuts + " minut", minuts);
                                    Thread.sleep(wait);
                                }
                            } else if (ts.getTime().get(0) <= Integer.valueOf(od_times[0]) && ts.getTime().get(1) < Integer.valueOf(od_times[1])) {
                                while (true) {
                                    if(isCancle==true) {isCancle = false; break;}
                                    int hour = Integer.valueOf(od_times[0]) - ts.getTime().get(0);
                                    int minuts = Integer.valueOf(od_times[1]) - ts.getTime().get(1);
                                    minuts = minuts + (hour * 60) - zmienna;
                                    int sec = (minuts * 60) - ts.getTime().get(2);
                                    Log.i("NOTIFICATION_TASK", "3. " + minuts + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                    if (minuts <= 0) {
                                        nf.cancleNotification();
                                        break;
                                    }

                                    if (sec == 1)
                                        nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostała " + sec + " sekunda", minuts);
                                    else if (sec >= 2 && sec < 5)
                                        nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostały " + sec + " sekundy", minuts);
                                    else if (sec >= 5 && sec <= 59)
                                        nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostało " + sec + " sekund", minuts);
                                    else if (minuts == 1)
                                        nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostała " + minuts + " minuta", minuts);
                                    else if (minuts >= 2 && minuts < 5)
                                        nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostały " + minuts + " minuty", minuts);
                                    else if (minuts >= 5)
                                        nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostało " + minuts + " minut", minuts);
                                    Thread.sleep(wait);
                                }
                            }
                            Thread.sleep(wait);
                        }
                        Thread.sleep(wait);
                    }
                    Thread.sleep(wait);
                }
            } catch (InterruptedException ex) {
                return null;
            }
    }
}
