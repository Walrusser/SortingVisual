package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Frame_Main extends JFrame {

    private Screen _screen = new Screen();

    private int maxArraySize = 50;
    private int fps = 60;


    //TODO Make sure static is really needed
    public static ArrayList<Integer> array = new ArrayList<>();

    public Frame_Main(){
        this.setSize(350,270);
        this.setVisible(true);
        this.setTitle("Visual Array Sorting");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.add(_screen);

    }

    public static void main(String[] args){
            Frame_Main m = new Frame_Main();

            while(true){

            m.randomizeArray(array, m.maxArraySize);
            m.refreshVisuals();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            m._quickSort(array, 0 , array.size()-1);
            m.refreshVisuals();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

    }

    public void refreshVisuals(){

        _screen.lineArrayList.clear();
        _screen.setArrayList(array);
        _screen.repaint();

    }

    public void randomizeArray(ArrayList<Integer> arrayList, int maxSize){

        arrayList.clear();

        Random rnd = new Random();

        //Create a linear array
        for(int i = 1; i < maxSize; i++){
            arrayList.add(i);
        }

        //Randomize the array by switching indexes
        for(int i = 0; i < maxSize; i++){
            Collections.swap(arrayList, rnd.nextInt(arrayList.size()-1), rnd.nextInt(arrayList.size()-1));
        }
    }

    public void _quickSort(ArrayList<Integer> arrayList, int left, int right){

        if(right - left <= 0){
            return;
        } else{

            int pivot = arrayList.get(right);

            int pivotLocation = partition(arrayList, left, right, pivot);

            _quickSort(arrayList, left, pivotLocation - 1);
            _quickSort(arrayList, pivotLocation + 1, right);
        }

    }

    public int partition(ArrayList<Integer> arrayList, int left, int right, int pivot){

        int leftPointer  = left - 1;
        int rightPointer = right;

        while(true){

            while(arrayList.get(++leftPointer) < pivot){

                try {
                    Thread.sleep(1000/fps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                _screen.pointerLIndex = leftPointer;
                refreshVisuals();
            };
            while(rightPointer > 0 && arrayList.get(--rightPointer) > pivot){

                try {
                    Thread.sleep(1000/fps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                _screen.pointerRIndex = rightPointer;
                refreshVisuals();
            };

            if(leftPointer >= rightPointer){
                break;
            } else{

                _screen.greenIndex = leftPointer;
                refreshVisuals();

                Collections.swap(arrayList, leftPointer, rightPointer);

                _screen.greenIndex = rightPointer;
                refreshVisuals();
            }
        }

        _screen.greenIndex = leftPointer;
        refreshVisuals();

        Collections.swap(arrayList, leftPointer, right);

        _screen.greenIndex = rightPointer;
        refreshVisuals();
        return leftPointer;
    }

    public void _bubbleSort(ArrayList<Integer> arrayList){
        int n = arrayList.size();
        int x = 0;

        for(int i = 0; i < n; i++){
            for(int j = 1; j < n; j++){

                try {
                    Thread.sleep(1000/fps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(arrayList.get(j-1) > arrayList.get(j)){
                    x = arrayList.get(j-1);
                    arrayList.set(j-1, arrayList.get(j));
                    arrayList.set(j, x);

                    _screen.greenIndex = j;
                    _screen.pointerRIndex = i;
                    refreshVisuals();
                }

                _screen.pointerRIndex = i;
                refreshVisuals();
            }
        }

    }


    public void _insertionSort(ArrayList<Integer> arrayList){

        _screen.pointerLIndex = 0;
        _screen.pointerRIndex = 0;

        int i = 0;
        while (i < arrayList.size()){
            int j = i;
            while (j > 0 && arrayList.get(j-1) > arrayList.get(j)){

                try {
                    Thread.sleep(1000/fps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                _screen.greenIndex = j;
                Collections.swap(arrayList, j, j-1);

                refreshVisuals();
                j--;
            }
            i++;
        }

    }

}
