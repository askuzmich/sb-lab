package com.example.demo.utis;

public class CustomLog {
    public static void printTitle(String title) {
        System.out.println(printHeader(title));
        System.out.println(printContent(title));
        System.out.println(printFooter(title));
    }

    public static String printHeader(String title) {
        String out = "╭───";

        out = out + stringLength(title);

        return out + "───╮";
    }

    public static String stringLength(String title) {
        String out = "";

        for (int i = 0; i < title.length(); i++) {
            out = out + "─";
        }

        return out;
    }

    public static String printFooter(String title) {
        String out = "╰───";

        out = out + stringLength(title);

        return out + "───╯";
    }

    public static String printContent(String title) {
        return "│   " + title + "   │";
    }
}
