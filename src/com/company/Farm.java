package com.company;

import java.util.*;

public class Farm {
    int numberBooks;
    int numberLibraries;
    int scanningDays;
    Dictionary<Integer, Book> books = new Hashtable<Integer, Book>();
    Dictionary<Integer, Library> libraries = new Hashtable<Integer, Library>();
    public Farm(int numberBooks, int numberLibraries, int scanningDays) {
        this.numberBooks = numberBooks;
        this.numberLibraries = numberLibraries;
        this.scanningDays = scanningDays;
    }
    public void printFarmInfo(){
        System.out.println("The farm has " + this.numberBooks + " books and " + this.numberLibraries + " libraries");
        System.out.println("It takes " + this.scanningDays + " to be ready for scanning");
    }
    public void printBookInfo() {
        Iterator keys = this.books.keys().asIterator();
        while (keys.hasNext()) {
            int key = (int) keys.next();
            Book book = this.books.get(key);
//            System.out.println("The book id " + key + " has score " + Integer.toString(book.score));
        }
    }
    public void updateScores(int d){
        Enumeration keys = this.libraries.keys();
        while(keys.hasMoreElements()){
            this.libraries.get(keys.nextElement()).getScore(d);
        }
    }
    public Library getLibWithHighScore(){
        Enumeration keys = this.libraries.keys();
        Library lib = this.libraries.get(keys.nextElement());
        while(keys.hasMoreElements()){
            Library newLib = this.libraries.get(keys.nextElement());
            if(newLib.score > lib.score && newLib.is_sent == false){
                lib = newLib;
            }
        }
        return lib;
    }
    public List scanBooks(){
        List<Book> booksScanned = new ArrayList<Book>();
        Enumeration keys = this.libraries.keys();
        while(keys.hasMoreElements()){
            Library lib = this.libraries.get(keys.nextElement());
            if(lib.is_ready){
                int count = 0;

                while (count < lib.shipRate) {
                    Enumeration bookKeys = lib.books.keys();
                    Book selectBook = lib.books.get(bookKeys.nextElement());
                    while (bookKeys.hasMoreElements()) {
                        Book book = lib.books.get(bookKeys.nextElement());
                        if (!book.is_scanned) {
                            if (book.score > selectBook.score || selectBook.is_scanned) {
                                selectBook = book;
                            }
                        }
                    }
                    if(!selectBook.is_scanned){
                        System.out.println("The book score " + selectBook.score + " is gained.");
                        selectBook.is_scanned = true;
                        
                    }
                    count++;
                }
            }
        }
        return booksScanned;
    }
}
