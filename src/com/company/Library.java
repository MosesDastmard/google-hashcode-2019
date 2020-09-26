package com.company;

import java.util.*;

public class Library {
    int libraryId;
    int dayReady = 2000000;
    boolean is_sent = false;
    boolean is_ready = false;
    int numberBooks;
    int signUp;
    int shipRate;
    double score;
    Dictionary<Integer, Book> books = new Hashtable<Integer, Book>();
    public void printInfo(){
        System.out.println("The library id " + Integer.toString(this.libraryId) +
                " has " + Integer.toString(this.numberBooks)+
                " books and it takes " + Integer.toString(this.signUp) +
                " days to get ready and the shipping rate is " + Integer.toString(this.shipRate) +
                " books per day");
    }
    public void printBookInfo() {
        Iterator keys = this.books.keys().asIterator();
        while (keys.hasNext()) {
            int key = (int) keys.next();
            Book book = this.books.get(key);
            System.out.println("The book id " + Integer.toString(key) + " has score " + Integer.toString(book.score));
        }
    }
    public double getScore(int d){
        double score = 0.0;
        int totalGain = 0;
        int totalRemainBooks = 0;
        Enumeration keys = this.books.keys();
        while(keys.hasMoreElements()){
            Book book = this.books.get(keys.nextElement());
            if(book.is_scanned == false){
                totalGain = totalGain + book.score;
                totalRemainBooks++;
            }
        }
        score = totalGain/(this.signUp + totalRemainBooks/this.shipRate);
//        System.out.println(score);
        this.score = score;
        if(this.dayReady == d){
            this.is_ready = true;
        }
        return score;
    }
}