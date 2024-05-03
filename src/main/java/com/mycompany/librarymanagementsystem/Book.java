package com.mycompany.librarymanagementsystem;

import java.time.LocalDate;

/**
 * Book class containing relevant public properties.
 *
 * @author brdde
 */
public class Book {

    public long ISBN;
    public String title;
    public String author;
    public boolean isAvailable = true;
    
    public LocalDate dueDate;
    public boolean isOverDue;

    /**
     * Constructor method for new Book object.
     *
     * @param ISBN - 10-13 digit long number
     * @param title - String - Book title
     * @param author - String - Book author
     */
    public Book(long ISBN, String title, String author) {

        this.ISBN = ISBN;
        this.title = title;
        this.author = author;

    }

}
