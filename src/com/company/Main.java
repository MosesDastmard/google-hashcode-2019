package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        Reading text file includes farm information
        String fileName = "a_example.txt";
        File example = new File(fileName);
        Scanner reader = new Scanner(example);
        //FARM---------------------------------
        String[] farmInfo = reader.nextLine().split(" ");
        int numberBooks = Integer.parseInt(farmInfo[0]);
        int numberLibraries = Integer.parseInt(farmInfo[1]);
        int scanningDays = Integer.parseInt(farmInfo[2]);
        Farm farm = new Farm(numberBooks, numberLibraries, scanningDays);
        farm.printFarmInfo();
        //BOOKS--------------------------------
        int[] bookInfo = Arrays.stream(reader.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < bookInfo.length; i++) {
            Book book = new Book(bookInfo[i]);
            farm.books.put(i, book);
        }
        farm.printBookInfo();
        //LIBRARY------------------------------
        for (int i = 0; i < numberLibraries; i++) {
            int[] libInfo = Arrays.stream(reader.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Library lib = new Library();
            lib.libraryId = i;
            lib.numberBooks = libInfo[0];
            lib.signUp = libInfo[1];
            lib.shipRate = libInfo[2];
            lib.printInfo();
            int[] booksId = Arrays.stream(reader.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j: booksId) {
                lib.books.put(j, farm.books.get(j));
            }
            farm.libraries.put(i,lib);
            lib.printBookInfo();
        }


        // Test score
        farm.updateScores(0);
        int d = 0;
        Library lib;
        boolean[] machineState = new boolean[scanningDays];
        while(d < scanningDays){
            System.out.println("The day is " + d + " ------------- ");
            System.out.println(Arrays.toString(machineState));
            if(machineState[d] == false) {
                farm.updateScores(d);
                lib = farm.getLibWithHighScore();
                System.out.println(lib.libraryId);
                if(lib.is_sent == false) {
                    int end_day = 0;
                    if ((d + lib.signUp) > farm.scanningDays) {
                        end_day = farm.scanningDays;
                    } else {
                        end_day = d + lib.signUp;
                    }
                    for (int i = d; i < end_day; i++) {
                        machineState[i] = true;
                    }
                    lib.dayReady = d + lib.signUp;
                    lib.is_sent = true;
                }
            }
            System.out.println("###################################");
            farm.scanBooks();
            System.out.println("###################################");
            d++;
        }
    }
}
