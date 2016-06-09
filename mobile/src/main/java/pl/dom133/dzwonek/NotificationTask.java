package pl.dom133.dzwonek;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Set;
import java.util.logging.Logger;

public class NotificationTask extends AsyncTask<Void, Void, Void> {

    //Private
    private Application app;
    private Times ts;
    private int i=0;
    private Settings settings = new Settings(app);
    //Public
    public boolean isCancle = false;
    public int wait = 1000;
    public String zmienna = "0:0";


    public NotificationTask(Application app, Times ts)
    {
        this.app = app;
        this.ts = ts;
    }

    @Override
    protected Void doInBackground(Void... test) {
            try {
                while (true) {
                    switch (ts.getDay()) {
                        case 2: {
                            if(ts.od_time_list_1.size()==0) {
                                Log.i("NOTIFICATION_TASK", "Size 0");
                                break;
                            } else {
                                int size = ts.od_time_list_1.size();
                                if (i > (size - 1)) {
                                    i = 0;
                                    break;
                                }
                                zmienna = settings.getString("Zmienna");
                                String[] od_times = ts.od_time_list_1.get(i).split(":");
                                String[] do_times = ts.do_time_list_1.get(i).split(":");
                                Notifications nf = new Notifications(app);

                                String [] zmienna_list = zmienna.split(":");
                                int hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                int minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                minuts = minuts + (hour * 60);
                                int sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);

                                if((ts.getTime().get(0) > Integer.valueOf(od_times[0]) && ts.getTime().get(1) > Integer.valueOf(od_times[1])) ||  (ts.getTime().get(0) > Integer.valueOf(od_times[0]) && ts.getTime().get(1) < Integer.valueOf(od_times[1]))) {i++; break;}

                                Log.i("NOTIFICATION_TASK", "1. " + i + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1) + " " + od_times[0] + ":" + od_times[1] + " " + do_times[0] + ":" + do_times[1]);
                                if ((ts.getTime().get(0) >= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]) && sec>0) || (ts.getTime().get(0) <= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]) && sec>0)) {
                                    while (true) {
                                        if(isCancle==true) {isCancle = false; break;}
                                        zmienna_list = zmienna.split(":");
                                        Log.i("NOTIFICATION_TASK", "2. " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                        hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                        minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                        minuts = minuts + (hour * 60);
                                        sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);
                                        String sec_str = String.valueOf(sec);
                                        if(sec<=9)sec_str = "0"+sec_str;
                                        if (sec <= 0) {
                                            i = i + 1;
                                            nf.cancleNotification();
                                            break;
                                        }

                                        if (sec == 1)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostała " + sec_str + " sekunda", minuts);
                                        else if (sec >= 2 && sec < 5)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostały " + sec_str + " sekundy", minuts);
                                        else if (sec >= 5 && sec <= 59)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostało " + sec_str + " sekund", minuts);
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
                                        zmienna_list = zmienna.split(":");
                                        hour = Integer.valueOf(od_times[0]) - ts.getTime().get(0);
                                        minuts = Integer.valueOf(od_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                        sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);
                                        String sec_str = String.valueOf(sec);
                                        if(sec<=9)sec_str = "0"+sec_str;
                                        Log.i("NOTIFICATION_TASK", "3. " + minuts + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                        if (sec <= 0) {
                                            nf.cancleNotification();
                                            break;
                                        }

                                        if (sec == 1)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostała " + sec_str + " sekunda", minuts);
                                        else if (sec >= 2 && sec < 5)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostały " + sec_str + " sekundy", minuts);
                                        else if (sec >= 5 && sec <= 59)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostało " + sec_str + " sekund", minuts);
                                        else if (minuts == 1)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostała " + minuts + " minuta", minuts);
                                        else if (minuts >= 2 && minuts < 5)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostały " + minuts + " minuty", minuts);
                                        else if (minuts >= 5)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostało " + minuts + " minut", minuts);
                                        Thread.sleep(wait);
                                    }
                                }
                                break;
                            }
                        }
                        case 3: {
                            if(ts.od_time_list_2.size()==0) {
                                Log.i("NOTIFICATION_TASK", "Size 0");
                                break;
                            } else {
                                int size = ts.od_time_list_2.size();
                                if (i > (size - 1)) {
                                    i = 0;
                                    break;
                                }

                                zmienna = settings.getString("Zmienna");

                                String[] od_times = ts.od_time_list_2.get(i).split(":");
                                String[] do_times = ts.do_time_list_2.get(i).split(":");
                                Notifications nf = new Notifications(app);

                                String [] zmienna_list = zmienna.split(":");
                                int hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                int minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                minuts = minuts + (hour * 60);
                                int sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);

                                Log.i("NOTIFICATION_TASK", "1. " + i + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1) + " " + od_times[0] + ":" + od_times[1] + " " + do_times[0] + ":" + do_times[1]);
                                if ((ts.getTime().get(0) >= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]) && sec>0) || (ts.getTime().get(0) <= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]) && sec>0)) {
                                    while (true) {
                                        if(isCancle==true) {isCancle = false; break;}
                                        zmienna_list = zmienna.split(":");
                                        Log.i("NOTIFICATION_TASK", "2. " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                        hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                        minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                        minuts = minuts + (hour * 60);
                                        sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);
                                        String sec_str = String.valueOf(sec);
                                        if(sec<=9)sec_str = "0"+sec_str;
                                        if (sec <= 0) {
                                            i = i + 1;
                                            nf.cancleNotification();
                                            break;
                                        }

                                        if (sec == 1)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostała " + sec_str + " sekunda", minuts);
                                        else if (sec >= 2 && sec < 5)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostały " + sec_str + " sekundy", minuts);
                                        else if (sec >= 5 && sec <= 59)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostało " + sec_str + " sekund", minuts);
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
                                        zmienna_list = zmienna.split(":");
                                        hour = Integer.valueOf(od_times[0]) - ts.getTime().get(0);
                                        minuts = Integer.valueOf(od_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                        sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);
                                        String sec_str = String.valueOf(sec);
                                        if(sec<=9)sec_str = "0"+sec_str;
                                        Log.i("NOTIFICATION_TASK", "3. " + minuts + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                        if (sec <= 0) {
                                            nf.cancleNotification();
                                            break;
                                        }

                                        if (sec == 1)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostała " + sec_str + " sekunda", minuts);
                                        else if (sec >= 2 && sec < 5)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostały " + sec_str + " sekundy", minuts);
                                        else if (sec >= 5 && sec <= 59)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostało " + sec_str + " sekund", minuts);
                                        else if (minuts == 1)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostała " + minuts + " minuta", minuts);
                                        else if (minuts >= 2 && minuts < 5)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostały " + minuts + " minuty", minuts);
                                        else if (minuts >= 5)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostało " + minuts + " minut", minuts);
                                        Thread.sleep(wait);
                                    }
                                }
                                break;
                            }
                        }
                        case 4: {
                            if(ts.od_time_list_3.size()==0) {
                                Log.i("NOTIFICATION_TASK", "Size 0");
                                break;
                            } else {
                                int size = ts.od_time_list_3.size();
                                if (i > (size - 1)) {
                                    i = 0;
                                    break;
                                }

                                zmienna = settings.getString("Zmienna");

                                String[] od_times = ts.od_time_list_3.get(i).split(":");
                                String[] do_times = ts.do_time_list_3.get(i).split(":");
                                Notifications nf = new Notifications(app);

                                String [] zmienna_list = zmienna.split(":");
                                int hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                int minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                minuts = minuts + (hour * 60);
                                int sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);

                                if((ts.getTime().get(0) > Integer.valueOf(od_times[0]) && ts.getTime().get(1) > Integer.valueOf(od_times[1])) ||  (ts.getTime().get(0) > Integer.valueOf(od_times[0]) && ts.getTime().get(1) < Integer.valueOf(od_times[1]))) {i++; break;}

                                Log.i("NOTIFICATION_TASK", "1. " + i + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1) + " " + od_times[0] + ":" + od_times[1] + " " + do_times[0] + ":" + do_times[1]);
                                if ((ts.getTime().get(0) >= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]) && sec>0) || (ts.getTime().get(0) <= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]) && sec>0)) {
                                    while (true) {
                                        if(isCancle==true) {isCancle = false; break;}
                                        zmienna_list = zmienna.split(":");
                                        Log.i("NOTIFICATION_TASK", "2. " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                        hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                        minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                        minuts = minuts + (hour * 60);
                                        sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);
                                        String sec_str = String.valueOf(sec);
                                        if(sec<=9)sec_str = "0"+sec_str;
                                        if (sec <= 0) {
                                            i = i + 1;
                                            nf.cancleNotification();
                                            break;
                                        }

                                        if (sec == 1)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostała " + sec_str + " sekunda", minuts);
                                        else if (sec >= 2 && sec < 5)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostały " + sec_str + " sekundy", minuts);
                                        else if (sec >= 5 && sec <= 59)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostało " + sec_str + " sekund", minuts);
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
                                        zmienna_list = zmienna.split(":");
                                        hour = Integer.valueOf(od_times[0]) - ts.getTime().get(0);
                                        minuts = Integer.valueOf(od_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                        sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);
                                        String sec_str = String.valueOf(sec);
                                        if(sec<=9)sec_str = "0"+sec_str;
                                        Log.i("NOTIFICATION_TASK", "3. " + minuts + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                        if (sec <= 0) {
                                            nf.cancleNotification();
                                            break;
                                        }

                                        if (sec == 1)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostała " + sec_str + " sekunda", minuts);
                                        else if (sec >= 2 && sec < 5)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostały " + sec_str + " sekundy", minuts);
                                        else if (sec >= 5 && sec <= 59)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostało " + sec_str + " sekund", minuts);
                                        else if (minuts == 1)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostała " + minuts + " minuta", minuts);
                                        else if (minuts >= 2 && minuts < 5)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostały " + minuts + " minuty", minuts);
                                        else if (minuts >= 5)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostało " + minuts + " minut", minuts);
                                        Thread.sleep(wait);
                                    }
                                }
                                break;
                            }
                        }
                        case 5: {
                            if(ts.od_time_list_4.size()==0) {
                                Log.i("NOTIFICATION_TASK", "Size 0");
                                break;
                            } else {
                                int size = ts.od_time_list_4.size();
                                if (i > (size - 1)) {
                                    i = 0;
                                    break;
                                }

                                zmienna = settings.getString("Zmienna");

                                String[] od_times = ts.od_time_list_4.get(i).split(":");
                                String[] do_times = ts.do_time_list_4.get(i).split(":");
                                Notifications nf = new Notifications(app);

                                String [] zmienna_list = zmienna.split(":");
                                int hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                int minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                minuts = minuts + (hour * 60);
                                int sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);

                                if((ts.getTime().get(0) > Integer.valueOf(od_times[0]) && ts.getTime().get(1) > Integer.valueOf(od_times[1])) ||  (ts.getTime().get(0) > Integer.valueOf(od_times[0]) && ts.getTime().get(1) < Integer.valueOf(od_times[1]))) {i++; break;}

                                Log.i("NOTIFICATION_TASK", "1. " + i + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1) + " " + od_times[0] + ":" + od_times[1] + " " + do_times[0] + ":" + do_times[1]);
                                if ((ts.getTime().get(0) >= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]) && sec>0) || (ts.getTime().get(0) <= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]) && sec>0)) {
                                    while (true) {
                                        if(isCancle==true) {isCancle = false; break;}
                                        zmienna_list = zmienna.split(":");
                                        Log.i("NOTIFICATION_TASK", "2. " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                        hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                        minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                        minuts = minuts + (hour * 60);
                                        sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);
                                        String sec_str = String.valueOf(sec);
                                        if(sec<=9)sec_str = "0"+sec_str;
                                        if (sec <= 0) {
                                            i = i + 1;
                                            nf.cancleNotification();
                                            break;
                                        }

                                        if (sec == 1)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostała " + sec_str + " sekunda", minuts);
                                        else if (sec >= 2 && sec < 5)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostały " + sec_str + " sekundy", minuts);
                                        else if (sec >= 5 && sec <= 59)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostało " + sec_str + " sekund", minuts);
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
                                        zmienna_list = zmienna.split(":");
                                        hour = Integer.valueOf(od_times[0]) - ts.getTime().get(0);
                                        minuts = Integer.valueOf(od_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                        sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);
                                        String sec_str = String.valueOf(sec);
                                        if(sec<=9)sec_str = "0"+sec_str;
                                        Log.i("NOTIFICATION_TASK", "3. " + minuts + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                        if (sec <= 0) {
                                            nf.cancleNotification();
                                            break;
                                        }

                                        if (sec == 1)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostała " + sec_str + " sekunda", minuts);
                                        else if (sec >= 2 && sec < 5)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostały " + sec_str + " sekundy", minuts);
                                        else if (sec >= 5 && sec <= 59)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostało " + sec_str + " sekund", minuts);
                                        else if (minuts == 1)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostała " + minuts + " minuta", minuts);
                                        else if (minuts >= 2 && minuts < 5)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostały " + minuts + " minuty", minuts);
                                        else if (minuts >= 5)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostało " + minuts + " minut", minuts);
                                        Thread.sleep(wait);
                                    }
                                }
                                break;
                            }
                        }
                        case 6: {
                            if(ts.od_time_list_5.size()==0) {
                                Log.i("NOTIFICATION_TASK", "Size 0");
                                break;
                            } else {
                                int size = ts.od_time_list_5.size();
                                if (i > (size - 1)) {
                                    i = 0;
                                    break;
                                }

                                zmienna = settings.getString("Zmienna");

                                String[] od_times = ts.od_time_list_5.get(i).split(":");
                                String[] do_times = ts.do_time_list_5.get(i).split(":");
                                Notifications nf = new Notifications(app);

                                String [] zmienna_list = zmienna.split(":");
                                int hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                int minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                minuts = minuts + (hour * 60);
                                int sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);

                                if((ts.getTime().get(0) > Integer.valueOf(od_times[0]) && ts.getTime().get(1) > Integer.valueOf(od_times[1])) ||  (ts.getTime().get(0) > Integer.valueOf(od_times[0]) && ts.getTime().get(1) < Integer.valueOf(od_times[1]))) {i++; break;}

                                Log.i("NOTIFICATION_TASK", "1. " + i + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1) + " " + od_times[0] + ":" + od_times[1] + " " + do_times[0] + ":" + do_times[1]);
                                if ((ts.getTime().get(0) >= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]) && sec>0) || (ts.getTime().get(0) <= Integer.valueOf(od_times[0]) && ts.getTime().get(1) >= Integer.valueOf(od_times[1]) && sec>0)) {
                                    while (true) {
                                        if(isCancle==true) {isCancle = false; break;}
                                        zmienna_list = zmienna.split(":");
                                        Log.i("NOTIFICATION_TASK", "2. " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                        hour = Integer.valueOf(do_times[0]) - ts.getTime().get(0);
                                        minuts = Integer.valueOf(do_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                        minuts = minuts + (hour * 60);
                                        sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);
                                        String sec_str = String.valueOf(sec);
                                        if(sec<=9)sec_str = "0"+sec_str;
                                        if (sec <= 0) {
                                            i = i + 1;
                                            nf.cancleNotification();
                                            break;
                                        }

                                        if (sec == 1)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostała " + sec_str + " sekunda", minuts);
                                        else if (sec >= 2 && sec < 5)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostały " + sec_str + " sekundy", minuts);
                                        else if (sec >= 5 && sec <= 59)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostało " + sec_str + " sekund", minuts);
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
                                        zmienna_list = zmienna.split(":");
                                        hour = Integer.valueOf(od_times[0]) - ts.getTime().get(0);
                                        minuts = Integer.valueOf(od_times[1]) - ts.getTime().get(1) - Integer.valueOf(zmienna_list[0]);
                                        sec = (minuts * 60) - ts.getTime().get(2) - Integer.valueOf(zmienna_list[1]);
                                        String sec_str = String.valueOf(sec);
                                        if(sec<=9)sec_str = "0"+sec_str;
                                        Log.i("NOTIFICATION_TASK", "3. " + minuts + " : " + ts.getTime().get(0) + ":" + ts.getTime().get(1));
                                        if (sec <= 0) {
                                            nf.cancleNotification();
                                            break;
                                        }

                                        if (sec == 1)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostała " + sec_str + " sekunda", minuts);
                                        else if (sec >= 2 && sec < 5)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostały " + sec_str + " sekundy", minuts);
                                        else if (sec >= 5 && sec <= 59)
                                            nf.sendNotification("Czas: " + sec_str + " sec", "Do dzwonka pozostało " + sec_str + " sekund", minuts);
                                        else if (minuts == 1)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostała " + minuts + " minuta", minuts);
                                        else if (minuts >= 2 && minuts < 5)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostały " + minuts + " minuty", minuts);
                                        else if (minuts >= 5)
                                            nf.sendNotification("Czas: " + (minuts - 1) + ":" + (60 - ts.getTime().get(2)) + " min", "Do dzwonka pozostało " + minuts + " minut", minuts);
                                        Thread.sleep(wait);
                                    }
                                }
                                break;
                            }
                        }

                    }
                    Thread.sleep(wait);
                }
            } catch (InterruptedException ex) {
                return null;
            }
    }
}