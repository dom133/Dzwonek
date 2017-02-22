package pl.dom133.dzwonek;


import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Time {

    private SharedPreferences sPref;
    private Gson gson  = new Gson();

    public Time(SharedPreferences sPref) {this.sPref = sPref;}

    public ArrayList<String> getLesson(int lesson, int type) {
        ArrayList<String> list = new ArrayList<>();
        if(type==0) {
            switch (lesson) {
                case 1: {
                    list.add("  1. 08:00 --> 08:45");
                    break;
                }

                case 2: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:55 --> 09:40");
                    break;
                }

                case 3: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:55 --> 09:40");
                    list.add("  3. 09:50 --> 10:35");
                    break;
                }

                case 4: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:55 --> 09:40");
                    list.add("  3. 09:50 --> 10:35");
                    list.add("  4. 10:50 --> 11:35");
                    break;
                }

                case 5: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:55 --> 09:40");
                    list.add("  3. 09:50 --> 10:35");
                    list.add("  4. 10:50 --> 11:35");
                    list.add("  5. 11:55 --> 12:40");
                    break;
                }

                case 6: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:55 --> 09:40");
                    list.add("  3. 09:50 --> 10:35");
                    list.add("  4. 10:50 --> 11:35");
                    list.add("  5. 11:55 --> 12:40");
                    list.add("  6. 12:50 --> 13:35");
                    break;
                }

                case 7: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:55 --> 09:40");
                    list.add("  3. 09:50 --> 10:35");
                    list.add("  4. 10:50 --> 11:35");
                    list.add("  5. 11:55 --> 12:40");
                    list.add("  6. 12:50 --> 13:35");
                    list.add("  7. 13:40 --> 14:25");
                    break;
                }
            }
        } else {
            switch (lesson) {
                case 1: {
                    list.add("  1. 08:00 --> 08:45");
                    break;
                }

                case 2: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:50 --> 09:35");
                    break;
                }

                case 3: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:50 --> 09:35");
                    list.add("  3. 09:45 --> 10:30");
                    break;
                }

                case 4: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:50 --> 09:35");
                    list.add("  3. 09:45 --> 10:30");
                    list.add("  4. 10:45 --> 11:30");
                    break;
                }

                case 5: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:50 --> 09:35");
                    list.add("  3. 09:45 --> 10:30");
                    list.add("  4. 10:45 --> 11:30");
                    list.add("  5. 11:40 --> 12:25");
                    break;
                }

                case 6: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:50 --> 09:35");
                    list.add("  3. 09:45 --> 10:30");
                    list.add("  4. 10:45 --> 11:30");
                    list.add("  5. 11:40 --> 12:25");
                    list.add("  6. 12:35 --> 13:20");
                    break;
                }

                case 7: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:50 --> 09:35");
                    list.add("  3. 09:45 --> 10:30");
                    list.add("  4. 10:45 --> 11:30");
                    list.add("  5. 11:40 --> 12:25");
                    list.add("  6. 12:35 --> 13:20");
                    list.add("  7. 13:25 --> 14:10");
                    break;
                }

                case 8: {
                    list.add("  1. 08:00 --> 08:45");
                    list.add("  2. 08:50 --> 09:35");
                    list.add("  3. 09:45 --> 10:30");
                    list.add("  4. 10:45 --> 11:30");
                    list.add("  5. 11:40 --> 12:25");
                    list.add("  6. 12:35 --> 13:20");
                    list.add("  7. 13:25 --> 14:10");
                    list.add("  8. 14:15 --> 15:00");
                    break;
                }
            }
        }

        return list;
    }

    public ArrayList<Integer> getLessonArray(int lesson, String type, int les_type) {
        ArrayList<Integer> list = new ArrayList<>();
        if(les_type==0) {
            switch (lesson) {
                case 1: {
                    if (Objects.equals(type, "od")) {
                        list.add(8);
                        list.add(0);
                    } else {
                        list.add(8);
                        list.add(45);
                    }
                    break;
                }

                case 2: {
                    if (Objects.equals(type, "od")) {
                        list.add(8);
                        list.add(55);
                    } else {
                        list.add(9);
                        list.add(40);
                    }
                    break;
                }

                case 3: {
                    if (Objects.equals(type, "od")) {
                        list.add(9);
                        list.add(50);
                    } else {
                        list.add(10);
                        list.add(35);
                    }
                    break;
                }

                case 4: {
                    if (Objects.equals(type, "od")) {
                        list.add(10);
                        list.add(50);
                    } else {
                        list.add(11);
                        list.add(35);
                    }
                    break;
                }

                case 5: {
                    if (Objects.equals(type, "od")) {
                        list.add(11);
                        list.add(55);
                    } else {
                        list.add(12);
                        list.add(40);
                    }
                    break;
                }

                case 6: {
                    if (Objects.equals(type, "od")) {
                        list.add(12);
                        list.add(50);
                    } else {
                        list.add(13);
                        list.add(35);
                    }
                    break;
                }

                case 7: {
                    if (Objects.equals(type, "od")) {
                        list.add(13);
                        list.add(40);
                    } else {
                        list.add(14);
                        list.add(25);
                    }
                    break;
                }
            }
        } else {
            switch (lesson) {
                case 1: {
                    if (Objects.equals(type, "od")) {
                        list.add(8);
                        list.add(0);
                    } else {
                        list.add(8);
                        list.add(45);
                    }
                    break;
                }

                case 2: {
                    if (Objects.equals(type, "od")) {
                        list.add(8);
                        list.add(50);
                    } else {
                        list.add(9);
                        list.add(35);
                    }
                    break;
                }

                case 3: {
                    if (Objects.equals(type, "od")) {
                        list.add(9);
                        list.add(45);
                    } else {
                        list.add(10);
                        list.add(30);
                    }
                    break;
                }

                case 4: {
                    if (Objects.equals(type, "od")) {
                        list.add(10);
                        list.add(45);
                    } else {
                        list.add(11);
                        list.add(30);
                    }
                    break;
                }

                case 5: {
                    if (Objects.equals(type, "od")) {
                        list.add(11);
                        list.add(40);
                    } else {
                        list.add(12);
                        list.add(25);
                    }
                    break;
                }

                case 6: {
                    if (Objects.equals(type, "od")) {
                        list.add(12);
                        list.add(35);
                    } else {
                        list.add(13);
                        list.add(20);
                    }
                    break;
                }

                case 7: {
                    if (Objects.equals(type, "od")) {
                        list.add(13);
                        list.add(25);
                    } else {
                        list.add(14);
                        list.add(10);
                    }
                    break;
                }

                case 8: {
                    if (Objects.equals(type, "od")) {
                        list.add(14);
                        list.add(15);
                    } else {
                        list.add(15);
                        list.add(0);
                    }
                    break;
                }
            }
        }

        return list;
    }

    public ArrayList<Integer> getLessonByTime(int hour, int minut, int type) {

        ArrayList<Integer> list = new ArrayList<>();
        if(type==0) {
            //Lekcje
            if (hour == 8 && minut < 45) {list.add(1);list.add(0);
            } else if (hour == 8 && minut >= 55) {list.add(2);list.add(0);
            } else if (hour == 9 && minut < 40) {list.add(2);list.add(0);
            } else if (hour == 9 && minut >= 50) {list.add(3);list.add(0);
            } else if (hour == 10 && minut < 35) {list.add(3);list.add(0);
            } else if (hour == 10 && minut >= 50) {list.add(4);list.add(0);
            } else if (hour == 11 && minut < 35) {list.add(4);list.add(0);
            } else if (hour == 11 && minut >= 55) {list.add(5);list.add(0);
            } else if (hour == 12 && minut < 40) {list.add(5);list.add(0);
            } else if (hour == 12 && minut >= 50) {list.add(6);list.add(0);
            } else if (hour == 13 && minut < 35) {list.add(6);list.add(0);
            } else if (hour == 13 && minut >= 40) {list.add(7);list.add(0);
            } else if (hour == 14 && minut < 25) {list.add(7);list.add(0);}
            //Przerwy
            else if (hour == 8 && minut >= 45) {list.add(10);list.add(1);
            } else if (hour == 9 && minut >= 40) {list.add(10);list.add(2);
            } else if (hour == 10 && minut >= 35) {list.add(10);list.add(3);
            } else if (hour == 11 && minut >= 35) {list.add(10);list.add(4);
            } else if (hour == 12 && minut >= 40) {list.add(10);list.add(5);
            } else if (hour == 13 && minut >= 35) {list.add(10);list.add(6);
            } else {list.add(0);list.add(0);}
        } else {
            //Lekcje
            if (hour == 8 && minut < 45) {list.add(1);list.add(0);
            } else if (hour == 8 && minut >= 50) {list.add(2);list.add(0);
            } else if (hour == 9 && minut < 35) {list.add(2);list.add(0);
            } else if (hour == 9 && minut >= 45) {list.add(3);list.add(0);
            } else if (hour == 10 && minut < 30) {list.add(3);list.add(0);
            } else if (hour == 10 && minut >= 45) {list.add(4);list.add(0);
            } else if (hour == 11 && minut < 30) {list.add(4);list.add(0);
            } else if (hour == 11 && minut >= 40) {list.add(5);list.add(0);
            } else if (hour == 12 && minut < 25) {list.add(5);list.add(0);
            } else if (hour == 12 && minut >= 35) {list.add(6);list.add(0);
            } else if (hour == 13 && minut < 20) {list.add(6);list.add(0);
            } else if (hour == 13 && minut >= 25) {list.add(7);list.add(0);
            } else if (hour == 14 && minut < 10) {list.add(7);list.add(0);
            } else if (hour == 14 && minut >= 15) {list.add(8);list.add(0);
            } else if (hour == 15 && minut < 0) {list.add(8);list.add(0);}
            //Przerwy
            else if (hour == 8 && minut >= 45) {list.add(10);list.add(1);
            } else if (hour == 9 && minut >= 35) {list.add(10);list.add(2);
            } else if (hour == 10 && minut >= 30) {list.add(10);list.add(3);
            } else if (hour == 11 && minut >= 30) {list.add(10);list.add(4);
            } else if (hour == 12 && minut >= 25) {list.add(10);list.add(5);
            } else if (hour == 13 && minut >= 20) {list.add(10);list.add(6);
            } else if (hour == 14 && minut >= 10) {list.add(10);list.add(7);
            } else {list.add(0);list.add(0);}
        }

        return list;
    }

    public int getDay() {return (Calendar.getInstance().get(Calendar.DAY_OF_WEEK))-1;}

    public ArrayList<Integer> getTime() {
        ArrayList<Integer> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        if(sPref.getString("time", null)!=null) {
            List<String> text = Arrays.asList(gson.fromJson(sPref.getString("time", null), String[].class));
            int hour = Integer.valueOf(text.get(0));
            int min = Integer.valueOf(text.get(1));
            int sec = Integer.valueOf(text.get(2));
            int h_res = 0; int m_res = 0; int s_res = 0;
            //if(c.get(Calendar.HOUR_OF_DAY) + hour >= 24) { }
            Log.i("TIME", "H: "+(c.get(Calendar.HOUR_OF_DAY) + hour)+" M: "+(c.get(Calendar.MINUTE) + min)+" S: "+(c.get(Calendar.SECOND) + sec));
            list.add(c.get(Calendar.HOUR_OF_DAY) + hour);
            list.add(c.get(Calendar.MINUTE) + min);
            list.add(c.get(Calendar.SECOND) + sec);
        } else {
            list.add(c.get(Calendar.HOUR_OF_DAY));
            list.add(c.get(Calendar.MINUTE));
            list.add(c.get(Calendar.SECOND));
        }
        return list;
    }

    public String getString(int time) {
        if(time<=9) {return "0"+time;}
        else {return String.valueOf(time);}
    }
}