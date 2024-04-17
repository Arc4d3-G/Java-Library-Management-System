package com.mycompany.librarymanagementsystem;

/**
 * Book class containing relevant public properties.
 *
 * @author brdde
 */
public class Book {

    public long ISBN;
    public String title;
    public String author;
    public boolean isAvailable;

    /**
     * Constructor method for new Book object.
     *
     * @param ISBN - 10-13 digit long number
     * @param title - String - Book title
     * @param author - String - Book author
     * @param isAvailable - Boolean - Book's availability status
     */
    public Book(long ISBN, String title, String author, boolean isAvailable) {

        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

}
