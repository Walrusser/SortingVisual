package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Screen extends JPanel {

    public ArrayList<Integer> lineArrayList = new ArrayList<>();

    public int greenIndex;
    public int pointerRIndex;
    public int pointerLIndex;

    public float yMultiplier = 3.5f;

    private float lineWidth;
    private float lineSpace = 2f;
    private float x;


    public void setArrayList(ArrayList<Integer> arrayList){
        lineArrayList.addAll(arrayList);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        x = 20;
        lineWidth = 4f;


        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(lineWidth));


        for(int i = 0; i < lineArrayList.size(); i++){

            g2.setColor(Color.DARK_GRAY);

            if(i == greenIndex - 1){
                g2.setColor(Color.GREEN);
                g2.draw(new Line2D.Float(x,230, x, 200 - lineArrayList.get(i)*yMultiplier));
            }

            if(i == pointerRIndex || i == pointerLIndex){
                g2.setColor(Color.red);
                g2.draw(new Line2D.Float(x,230, x, 200 - lineArrayList.get(i)*yMultiplier));
            }

            g2.draw(new Line2D.Float(x,230, x, 200 - lineArrayList.get(i)*yMultiplier));
            x = x + (int)lineWidth + lineSpace;
        }
    }
}
