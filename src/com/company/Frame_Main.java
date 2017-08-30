package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Frame_Main extends JFrame {

    private final Screen_JPanel Screen = new Screen_JPanel();

    private final String APPLICATION_NAME = "Visual Array Sorting";
    private final int TICKRATE = 400;

    private static final Object[] DIALOG_STRINGS = {"Quicksort", "Bubblesort", "Insertionsort"};

    private static final ArrayList<Integer> ARRAY_LIST = new ArrayList<>();

    private Frame_Main() {
        this.setSize(450, 270);
        this.setVisible(true);
        this.setTitle(APPLICATION_NAME);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.add(Screen);

    }

    public static void main(String[] args) {
        Frame_Main m = new Frame_Main();

        while (true) {
            //Create a dialog
            String userSortingMethod = (String) JOptionPane.showInputDialog(null, "Please select sorting method: ",
                    m.APPLICATION_NAME, JOptionPane.QUESTION_MESSAGE, null, DIALOG_STRINGS, 1);

            int userArraySize = Integer.parseInt(JOptionPane.showInputDialog(null, "Please specify the ARRAY_LIST size: ",
                    m.APPLICATION_NAME, JOptionPane.PLAIN_MESSAGE));

            m.randomizeArray(ARRAY_LIST, userArraySize);

            switch (userSortingMethod) {
                case "Quicksort":
                    m._quickSort(ARRAY_LIST, 0, ARRAY_LIST.size() - 1);
                    break;

                case "Bubblesort":
                    m._bubbleSort(ARRAY_LIST);
                    break;

                case "Insertionsort":
                    m._insertionSort(ARRAY_LIST);
                    break;

            }

            JOptionPane.showConfirmDialog(null, "Sorting Complete!", m.APPLICATION_NAME, JOptionPane.PLAIN_MESSAGE);


        }

    }

    private void refreshVisuals() {

        Screen.lineArrayList.clear();
        Screen.setArrayList(ARRAY_LIST);
        Screen.repaint();

    }

    private void randomizeArray(ArrayList<Integer> arrayList, int maxSize) {

        arrayList.clear();

        Random rnd = new Random();

        //Create a linear ARRAY_LIST
        for (int i = 1; i < maxSize; i++) {
            arrayList.add(i);
        }

        //Randomize the ARRAY_LIST by switching indexes
        for (int i = 0; i < maxSize; i++) {
            Collections.swap(arrayList, rnd.nextInt(arrayList.size() - 1), rnd.nextInt(arrayList.size() - 1));
        }

        for (int i = 0; i < maxSize; i++) {
            Collections.swap(arrayList, rnd.nextInt(arrayList.size() - 1), rnd.nextInt(arrayList.size() - 1));
        }
    }

    private void _quickSort(ArrayList<Integer> arrayList, int left, int right) {


        if (right - left <= 0) {
            return;
        } else {

            int pivot = arrayList.get(right);

            int pivotLocation = partition(arrayList, left, right, pivot);

            _quickSort(arrayList, left, pivotLocation - 1);
            _quickSort(arrayList, pivotLocation + 1, right);
        }

    }

    private int partition(ArrayList<Integer> arrayList, int left, int right, int pivot) {

        int leftPointer = left - 1;
        int rightPointer = right;

        while (true) {

            while (arrayList.get(++leftPointer) < pivot) {

                try {
                    Thread.sleep(1000 / TICKRATE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Screen.pointerLIndex = leftPointer;
                refreshVisuals();
            }

            while (rightPointer > 0 && arrayList.get(--rightPointer) > pivot) {

                try {
                    Thread.sleep(1000 / TICKRATE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Screen.pointerRIndex = rightPointer;
                refreshVisuals();
            }

            if (leftPointer >= rightPointer) {
                break;
            } else {

                Screen.greenIndex = leftPointer;
                refreshVisuals();

                Collections.swap(arrayList, leftPointer, rightPointer);

                Screen.greenIndex = rightPointer;
                refreshVisuals();
            }
        }

        Screen.greenIndex = leftPointer;
        refreshVisuals();

        Collections.swap(arrayList, leftPointer, right);

        Screen.greenIndex = rightPointer;
        refreshVisuals();
        return leftPointer;
    }

    public void _bubbleSort(ArrayList<Integer> arrayList) {
        int n = arrayList.size();
        int x = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {

                try {
                    Thread.sleep(1000 / TICKRATE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (arrayList.get(j - 1) > arrayList.get(j)) {
                    x = arrayList.get(j - 1);
                    arrayList.set(j - 1, arrayList.get(j));
                    arrayList.set(j, x);

                    Screen.greenIndex = j;
                    Screen.pointerRIndex = i;
                    refreshVisuals();
                }

                Screen.pointerRIndex = i;
                refreshVisuals();
            }
        }

    }


    public void _insertionSort(ArrayList<Integer> arrayList) {

        Screen.pointerLIndex = 0;
        Screen.pointerRIndex = 0;

        int i = 0;
        while (i < arrayList.size()) {
            int j = i;
            while (j > 0 && arrayList.get(j - 1) > arrayList.get(j)) {

                try {
                    Thread.sleep(1000 / TICKRATE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Screen.greenIndex = j;
                Collections.swap(arrayList, j, j - 1);

                refreshVisuals();
                j--;
            }
            i++;
        }

    }

}
