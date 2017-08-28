package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

class Screen extends JPanel {

    public final ArrayList<Integer> lineArrayList = new ArrayList<>();

    public int greenIndex;
    public int pointerRIndex;
    public int pointerLIndex;

    public void setArrayList(ArrayList<Integer> arrayList){
        lineArrayList.addAll(arrayList);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        float lineWidth = this.getWidth()/(lineArrayList.size());
        float lineSpace = (this.getWidth() - (lineWidth*lineArrayList.size())) / lineArrayList.size();
        float x = lineWidth/2 + (lineSpace/2);
        float yMultiplier = (this.getHeight()/1.5f)/lineArrayList.size();

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(lineWidth));


        for(int i = 0; i < lineArrayList.size(); i++){

            g2.setColor(Color.DARK_GRAY);

            if(i == greenIndex - 1){
                g2.setColor(Color.GREEN);
                g2.draw(new Line2D.Float(x, this.getHeight(), x, this.getHeight() - lineArrayList.get(i)*yMultiplier));
            }

            if(i == pointerRIndex || i == pointerLIndex){
                g2.setColor(Color.red);
                g2.draw(new Line2D.Float(x,this.getHeight(), x, this.getHeight() - lineArrayList.get(i)*yMultiplier));
            }

            g2.draw(new Line2D.Float(x,this.getHeight(), x, this.getHeight() - lineArrayList.get(i)*yMultiplier));
            x = x + (int) lineWidth + lineSpace;
        }
    }
}
