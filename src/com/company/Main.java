package com.company;


import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set number of tests");
        int n = scanner.nextInt();

        if (numberOfDrawings(n)) {
            for (int i = 1; i <= n; i++) {
                Scanner scanner1 = new Scanner(System.in);
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Set number coords of black figure");
                String cordsBlack = scanner1.nextLine();
                String cordsGrey = scanner2.nextLine();
                String[] cordsBlackTab = cordsBlack.split(" ");
                String[] cordsGreyTab = cordsGrey.split(" ");
                if (equation(line(blackPoints(cordsBlackTab)))) {
                    System.out.println(10 * blackField(blackPoints(cordsBlackTab)) + 6 * (greyField(greyPoints(cordsGreyTab)) - blackField(blackPoints(cordsBlackTab))));
                } else {
                    System.out.println("wrong defined field");
                }
            }


        }
    }


    static boolean numberOfDrawings(int n) {

        return (0 < n && n <= 100);
    }

    public static ArrayList<Point> blackPoints(String[] cordsBlackTab) {
        ArrayList<Point> blackPoints = new ArrayList<>();
        for (int i = 0; i < cordsBlackTab.length - 1; i = i + 2) {
            Point blackPoint = new Point();
            blackPoint.x = Integer.parseInt(cordsBlackTab[i], 10);
            blackPoint.y = Integer.parseInt(cordsBlackTab[i + 1], 10);
            blackPoints.add(blackPoint);

        }

        return blackPoints;
    }


    public static ArrayList<Point> line(ArrayList<Point> blackPoints) {
        int a = 0;
        int b = 0;

        ArrayList<Point> lineOfPoints = new ArrayList<>();
        for (int i = 0; i < blackPoints.size() - 1; i++) {
            Point line;
            try {
                a = (blackPoints.get(i).y - blackPoints.get(i + 1).y) / (blackPoints.get(i).x - blackPoints.get(i + 1).x);

            } catch (Exception e) {
                a = 0;
            }
            b = blackPoints.get(i).y - (a * blackPoints.get(i).x);
            if ((blackPoints.get(i).y - blackPoints.get(i + 1).y) == 0) {
                line = new Point(b, a);
            } else {
                line = new Point(a, b);
            }

            lineOfPoints.add(line);
        }
        return lineOfPoints;
    }

    public static boolean equation(ArrayList<Point> lineOfPoints) {
        int x;
        int y;
        int minX;
        int minY;
        int maxX;
        int maxY;
        for (int i = 0; i < lineOfPoints.size(); i++) {
            for (int j = i + 1; j < lineOfPoints.size(); j++) {
                try {
                    x = ((-lineOfPoints.get(i).y) + lineOfPoints.get(j).y) / (lineOfPoints.get(i).x - lineOfPoints.get(j).x);
                    y = (lineOfPoints.get(i).x * x) + lineOfPoints.get(i).y;
                } catch (Exception e) {
                    if (lineOfPoints.get(i).y == lineOfPoints.get(j).y && lineOfPoints.get(i).x == lineOfPoints.get(j).x) {
                        return false;
                    }
                    return true;
                }

                minX = Math.min(lineOfPoints.get(i).x, lineOfPoints.get(j).x);
                minY = Math.min(lineOfPoints.get(i).y, lineOfPoints.get(j).y);
                maxX = Math.max(lineOfPoints.get(i).x, lineOfPoints.get(j).x);
                maxY = Math.max(lineOfPoints.get(i).y, lineOfPoints.get(j).y);
                if (x < minX && x > maxX && y < minY && y > maxY) {
                    return false;
                }
            }

        }

        return true;
    }


    public static double blackField(ArrayList<Point> blackPoints) {
        double blackField = 0;
        blackField = blackField + blackPoints.get(0).y * (blackPoints.get(1).x - blackPoints.get(blackPoints.size() - 1).x);
        for (int i = 1; i < blackPoints.size() - 1; i++) {
            blackField = blackField + blackPoints.get(i).y * (blackPoints.get(i + 1).x - blackPoints.get(i - 1).x);
        }
        blackField = blackField + blackPoints.get(blackPoints.size() - 1).y * (blackPoints.get(0).x - blackPoints.get(blackPoints.size() - 2).x);
        blackField = blackField / 2;
        double field = Math.abs(blackField);
        return field;
    }

    public static ArrayList<Point> greyPoints(String[] cordsGrayTab) {
        ArrayList<Point> greyPoints = new ArrayList<>();
        for (int i = 0; i < cordsGrayTab.length - 1; i = i + 2) {
            Point greyPoint = new Point();
            greyPoint.x = Integer.parseInt(cordsGrayTab[i], 10);
            greyPoint.y = Integer.parseInt(cordsGrayTab[i + 1], 10);
            greyPoints.add(greyPoint);

        }

        return greyPoints;
    }


    public static double greyField(ArrayList<Point> greyPoints) {
        double greyField = 0;
        greyField = greyField + greyPoints.get(0).y * (greyPoints.get(1).x - greyPoints.get(greyPoints.size() - 1).x);
        for (int i = 1; i < greyPoints.size() - 1; i++) {
            greyField = greyField + greyPoints.get(i).y * (greyPoints.get(i + 1).x - greyPoints.get(i - 1).x);
        }
        greyField = greyField + greyPoints.get(greyPoints.size() - 1).y * (greyPoints.get(0).x - greyPoints.get(greyPoints.size() - 2).x);
        greyField = greyField / 2;
        double field = Math.abs(greyField);
        return field;
    }


}

