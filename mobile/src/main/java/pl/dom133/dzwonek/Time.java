package pl.dom133.dzwonek;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Time {

    public ArrayList<String> getLesson(int lesson) {
        ArrayList<String> list = new ArrayList<>();
        switch(lesson) {
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
        return list;
    }

    public ArrayList<Integer> getLessonArray(int lesson, String type) {
        ArrayList<Integer> list = new ArrayList<>();
        switch(lesson) {
            case 1: {
                if(Objects.equals(type, "od")) {
                    list.add(8);
                    list.add(0);
                } else {
                    list.add(8);
                    list.add(45);
                }
                break;
            }

            case 2: {
                if(Objects.equals(type, "od")) {
                    list.add(8);
                    list.add(55);
                } else {
                    list.add(9);
                    list.add(40);
                }
                break;
            }

            case 3: {
                if(Objects.equals(type, "od")) {
                    list.add(9);
                    list.add(50);
                } else {
                    list.add(10);
                    list.add(35);
                }
                break;
            }

            case 4: {
                if(Objects.equals(type, "od")) {
                    list.add(10);
                    list.add(50);
                } else {
                    list.add(11);
                    list.add(35);
                }
                break;
            }

            case 5: {
                if(Objects.equals(type, "od")) {
                    list.add(11);
                    list.add(55);
                } else {
                    list.add(12);
                    list.add(40);
                }
                break;
            }

            case 6: {
                if(Objects.equals(type, "od")) {
                    list.add(12);
                    list.add(50);
                } else {
                    list.add(13);
                    list.add(35);
                }
                break;
            }

            case 7: {
                if(Objects.equals(type, "od")) {
                    list.add(13);
                    list.add(40);
                } else {
                    list.add(14);
                    list.add(25);
                }
                break;
            }
        }

        return list;
    }

    public ArrayList<Integer> getLessonByTime(int hour, int minut) {

        ArrayList<Integer> list = new ArrayList<>();
        //Lekcje
        if(hour==8 && minut < 45) {list.add(1); list.add(0);}
        else if(hour==8 && minut >= 55) {list.add(2); list.add(0);}
        else if(hour==9 && minut < 40) {list.add(2); list.add(0);}
        else if(hour==9 && minut >= 50) {list.add(3); list.add(0);}
        else if(hour==10 && minut < 35) {list.add(3); list.add(0);}
        else if(hour==10 && minut >= 50) {list.add(4); list.add(0);}
        else if(hour==11 && minut < 35) {list.add(4); list.add(0);}
        else if(hour==11 && minut >= 55) {list.add(5); list.add(0);}
        else if(hour==12 && minut < 40) {list.add(5); list.add(0);}
        else if(hour==12 && minut >= 50) {list.add(6); list.add(0);}
        else if(hour==13 && minut < 35) {list.add(6); list.add(0);}
        else if(hour==13 && minut >= 40) {list.add(7); list.add(0);}
        else if(hour==14 && minut < 25) {list.add(7); list.add(0);}
        //Przerwy
        else if(hour==8 && minut >= 45) {list.add(10); list.add(1);}
        else if(hour==9 && minut >= 40) {list.add(10); list.add(2);}
        else if(hour==10 && minut >= 35) {list.add(10); list.add(3);}
        else if(hour==11 && minut >= 35) {list.add(10); list.add(4);}
        else if(hour==12 && minut >= 40) {list.add(10); list.add(5);}
        else if(hour==13 && minut >= 35) {list.add(10); list.add(6);}
        else { list.add(0); list.add(0);}

        return list;
    }

    public int getDay() {return (Calendar.getInstance().get(Calendar.DAY_OF_WEEK))-1;}

    public ArrayList<Integer> getTime() {
        ArrayList<Integer> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        list.add(c.get(Calendar.HOUR_OF_DAY));
        list.add(c.get(Calendar.MINUTE));
        list.add(c.get(Calendar.SECOND));
        /*list.add(8);
        list.add(55);
        list.add(32);*/
        return list;
    }

    public String getString(int time) {
        if(time<=9) {return "0"+time;}
        else {return String.valueOf(time);}
    }

}