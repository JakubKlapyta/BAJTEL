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
                System.out.println("Set number coords of black figure");
                String cordsBlack = scanner1.nextLine();
                String[] cordsBlackTab = cordsBlack.split(" ");
                System.out.println(blackPoints(cordsBlackTab));
                System.out.println(blackPointsCross(blackPoints(cordsBlackTab)));
                System.out.println(blackField(blackPoints(cordsBlackTab)));
            }


        }
    }


    static boolean numberOfDrawings(int n) {

        return (0 < n && n <= 100);
    }

    public static ArrayList<Point> blackPoints(String[] cordsBlackTab) {
        int x;
        int y;
        ArrayList<Point> blackPoints = new ArrayList<Point>();
        for (int i = 0; i < cordsBlackTab.length - 1; i = i + 2) {
            Point blackPoint = new Point();
            blackPoint.x = Integer.parseInt(cordsBlackTab[i], 10);
            blackPoint.y = Integer.parseInt(cordsBlackTab[i + 1], 10);
            blackPoints.add(blackPoint);

        }

        return blackPoints;
    }

    public static boolean blackPointsCross(ArrayList<Point> blackPoints) {
        int x;
        int y;
        int minY;
        int minX;
        int maxY;
        int maxX;
        for (int i = blackPoints.size()-1; i >= 0; i--) {
            for (int j = i - 1; j > 1; j--) {
                try {
                    x = (-blackPoints.get(i).y + blackPoints.get(j).y) / (blackPoints.get(i).x - blackPoints.get(j).x);
                }catch (java.lang.ArithmeticException e){
                   return true;
                }
                y = blackPoints.get(i).x * x + blackPoints.get(i).y;
                minX = Math.min(blackPoints.get(i).x, blackPoints.get(j).x);
                minY = Math.min(blackPoints.get(i).y, blackPoints.get(j).y);
                maxX = Math.max(blackPoints.get(i).x, blackPoints.get(j).x);
                maxY = Math.max(blackPoints.get(i).y, blackPoints.get(j).y);
                if ((x >= minX && x <= maxX || y >= minY && y <= maxY)) {
                    return true;
                }
            }

        }
   return false; }
   public static double blackField(ArrayList<Point> blackPoints){
        double blackField = 0;
        if(blackPointsCross(blackPoints)){
            for (int i = 1; i < blackPoints.size(); i++) {
                blackField = blackField + ((blackPoints.get(i).x - blackPoints.get((blackPoints.size()-1-i)).x)*(blackPoints.get(i-1).y));
                System.out.println(blackField);
            }
            for (int j = 1; j < blackPoints.size(); j++) {
                blackField = blackField + ((-blackPoints.get(j).x +blackPoints.get((blackPoints.size()-1-j)).x)*(blackPoints.get(j-1).y));
                System.out.println(blackField);
            }
        }
  return blackField/2; }
}
