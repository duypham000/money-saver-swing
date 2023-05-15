/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ms.services;

/**
 *
 * @author khanh
 */
public class Converter {

    public static String pickerToTime(String picker) {
        var date = picker.split(" ")[0].split("/");
        var time = picker.split(" ")[1].split(":");
        String aa = picker.split(" ")[2];
        int h = Integer.parseInt(time[0]);
        if (aa.equals("PM")) {
            h += 12;
        }
        return date[2] + "-" + date[1] + "-" + date[0] + " " + h + ":" + time[1] + ":00";
    }

    public static String timeToPicker(String time) {
        var date = time.split(" ")[0].split("-");
        var timeP = time.split(" ")[1].split(":");
        int h = Integer.parseInt(timeP[0]);
        String aa = "AM";
        if (h > 12) {
            h -= 12;
            aa = "PM";
        }
        return date[2] + "/" + date[1] + "/" + date[0] + " " + h + ":" + timeP[1] + " " + aa;
    }

    public static void main(String[] args) {
//        String r = pickerToTime("5/8/2002 5:12 PM");
        String r = timeToPicker("2023-05-08 15:12:54");

        System.out.println(r);
    }
}
