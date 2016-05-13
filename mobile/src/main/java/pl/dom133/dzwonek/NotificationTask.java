package pl.dom133.dzwonek;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.logging.Logger;

/**
 * Created by dom133 on 24.03.2016.
 */
public class NotificationTask extends AsyncTask<Void, Void, Void> {

    Application app;
    Times ts;
    int i=0;

    public NotificationTask(Application app, Times ts)
    {
        this.app = app;
        this.ts = ts;
    }

    protected Void doInBackground(Void... test) {
        Notifications nf = new Notifications(app);
        try {
            while (true) {
                if (ts.od_time_list.isEmpty() == false) {
                    while(true) {
                        int size = ts.od_time_list.size();
                        if(i>(size-1)) {i=0; break;}

                        String[] od_times_last = new String[0];
                        String[] do_times_last = new String[0];
                        if(i!=0) {od_times_last = ts.od_time_list.get(i-1).split(":"); do_times_last = ts.do_time_list.get(i-1).split(":"); }

                        String[] od_times = ts.od_time_list.get(i).split(":");
                        String[] do_times = ts.do_time_list.get(i).split(":");

                        Log.i("INFO", "1:"+i+" : "+ts.getTime().get(0)+":"+ts.getTime().get(1)+" "+od_times[0]+":"+od_times[1]+" "+do_times[0]+":"+do_times[1]);
                        if(ts.getTime().get(0)>=Integer.valueOf(od_times[0]) && ts.getTime().get(1)>=Integer.valueOf(od_times[1])) {
                            while (true) {
                                Log.i("INFO", "2 : " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                int hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                int minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1);
                                int sec;
                                minuts = minuts + (hour * 60);
                                sec = (minuts * 60) - ts.getTime().get(2);
                                if (minuts == 0) {
                                    i = i + 1;
                                    nf.cancleNotification();
                                    break;
                                }

                                if (sec == 1)
                                    nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostała " + sec + " sekunda");
                                else if (sec >= 2 && sec < 5)
                                    nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostały " + sec + " sekundy");
                                else if (sec >= 5 && sec <= 60)
                                    nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostało " + sec + " sekund");
                                else if (minuts == 1)
                                    nf.sendNotification("Czas: " + minuts + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostała " + minuts + " minuta");
                                else if (minuts >= 2 && minuts < 5)
                                    nf.sendNotification("Czas: " + minuts + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostały " + minuts + " minuty");
                                else if (minuts >= 5)
                                    nf.sendNotification("Czas: " + minuts + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostało " + minuts + " minut");
                                Thread.sleep(1000);
                            }
                        } else if(ts.getTime().get(0)<=Integer.valueOf(od_times[0]) && ts.getTime().get(1)<Integer.valueOf(od_times[1])) {
                            while (true) {
                                Log.i("INFO", "3 : " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                int hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                int minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1);
                                int sec;
                                minuts = minuts + (hour * 60);
                                sec = (minuts * 60) - ts.getTime().get(2);
                                if (minuts == 0) {
                                    nf.cancleNotification();
                                    break;
                                }

                                if (sec == 1)
                                    nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostała " + sec + " sekunda");
                                else if (sec >= 2 && sec < 5)
                                    nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostały " + sec + " sekundy");
                                else if (sec >= 5 && sec <= 60)
                                    nf.sendNotification("Czas: " + sec + " sec", "Do dzwonka pozostało " + sec + " sekund");
                                else if (minuts == 1)
                                    nf.sendNotification("Czas: " + minuts + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostała " + minuts + " minuta");
                                else if (minuts >= 2 && minuts < 5)
                                    nf.sendNotification("Czas: " + minuts + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostały " + minuts + " minuty");
                                else if (minuts >= 5)
                                    nf.sendNotification("Czas: " + minuts + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostało " + minuts + " minut");
                                Thread.sleep(1000);
                            }
                        }
                        Thread.sleep(1000);
                    }
                    Thread.sleep(1000);
                }
                Thread.sleep(1000);
            }
        } catch(InterruptedException ex){
            return null;
        }
    }
}
