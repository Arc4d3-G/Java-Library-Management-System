package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Book class containing relevant public properties.
 *
 * @author brdde
 */
public class Member {

    public String name;
    public String email;
    public List<Book> borrowedBooks = new ArrayList<>();

    /**
     * Constructor method for new Book object.
     *
     * @param name - String - Member full or partial Name
     * @param email - String - Member email address
     */
    public Member(String name, String email) {

        this.name = name;
        this.email = email;
    }
}
