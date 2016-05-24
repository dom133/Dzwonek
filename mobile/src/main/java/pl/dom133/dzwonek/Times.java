package pl.dom133.dzwonek;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Times {

    //Day 1
    public ArrayList<String> od_time_list_1 = new ArrayList<>();
    public ArrayList<String> do_time_list_1 = new ArrayList<>();
    //Day 2
    public ArrayList<String> od_time_list_2 = new ArrayList<>();
    public ArrayList<String> do_time_list_2 = new ArrayList<>();
    //Day 3
    public ArrayList<String> od_time_list_3 = new ArrayList<>();
    public ArrayList<String> do_time_list_3 = new ArrayList<>();
    //Day 4
    public ArrayList<String> od_time_list_4 = new ArrayList<>();
    public ArrayList<String> do_time_list_4 = new ArrayList<>();
    //Day 5
    public ArrayList<String> od_time_list_5 = new ArrayList<>();
    public ArrayList<String> do_time_list_5 = new ArrayList<>();

    public ArrayList<String> getLesson(int lesson, String type)
    {
        ArrayList<String> list = new ArrayList<>();
        switch(lesson)
        {
            case 1:
            {
                if(type=="od"){
                    list.add("8:0");
                } else {
                    list.add("8:45");
                }
                break;
            }
            case 2:
            {
                if(type=="od"){
                    list.add("8:0");
                    list.add("8:55");
                } else {
                    list.add("8:45");
                    list.add("9:40");
                }
                break;
            }
            case 3:
            {
                if(type=="od"){
                    list.add("8:0");
                    list.add("8:55");
                    list.add("9:50");
                } else {
                    list.add("8:45");
                    list.add("9:40");
                    list.add("10:35");
                }
                break;
            }
            case 4:
            {
                if(type=="od"){
                    list.add("8:0");
                    list.add("8:55");
                    list.add("9:50");
                    list.add("10:45");
                } else {
                    list.add("8:45");
                    list.add("9:40");
                    list.add("10:35");
                    list.add("11:30");
                }
                break;
            }
            case 5:
            {
                if(type=="od"){
                    list.add("8:0");
                    list.add("8:55");
                    list.add("9:50");
                    list.add("10:45");
                    list.add("11:50");
                } else {
                    list.add("8:45");
                    list.add("9:40");
                    list.add("10:35");
                    list.add("11:30");
                    list.add("12:35");
                }
                break;
            }
            case 6:
            {
                if(type=="od"){
                    list.add("8:0");
                    list.add("8:55");
                    list.add("9:50");
                    list.add("10:45");
                    list.add("11:50");
                    list.add("12:50");
                } else {
                    list.add("8:45");
                    list.add("9:40");
                    list.add("10:35");
                    list.add("11:30");
                    list.add("12:35");
                    list.add("13:35");
                }
                break;
            }
            case 7:
            {
                if(type=="od"){
                    list.add("8:0");
                    list.add("8:55");
                    list.add("9:50");
                    list.add("10:45");
                    list.add("11:50");
                    list.add("12:50");
                    list.add("13:40");
                } else {
                    list.add("8:45");
                    list.add("9:40");
                    list.add("10:35");
                    list.add("11:30");
                    list.add("12:35");
                    list.add("13:35");
                    list.add("14:25");
                }
                break;
            }
        }
        return list;
    }

    public int getDay()
    {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    public ArrayList<Integer> getTime()
    {
        ArrayList<Integer> list = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        String hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
        String minute = Integer.toString(c.get(Calendar.MINUTE));
        String sec = Integer.toString(c.get(Calendar.SECOND));
        list.add(Integer.valueOf(hour));
        list.add(Integer.valueOf(minute));
        list.add(Integer.valueOf(sec));
        return list;
    }

}
