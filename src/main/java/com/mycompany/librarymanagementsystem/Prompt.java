/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author brdde
 */
public class Prompt {

    /**
     * Prompts and validates user input to set the property values of a new Book
     * object. The values are then passed to the Book() constructor and the new
     * Book is returned by this function.
     *
     * @return Book newBook - A newly constructed Book.
     */
    public static Book ForNewBook() {

        Scanner scan = new Scanner(System.in);

        // Variables for book constructor
        long ISBN;
        String title;
        String author;

        System.out.println("""

            ---- Adding New Book ----

            Please provide the book's ISBN number, Title & Author.
            """);

        /**
         * Validation loop with error handling for book ISBN number. ISBN needs
         * to be 10-13 digits to be valid.
         */
        while (true) {

            System.out.print("ISBN Number (10-13 digits): ");

            try {
                String input = scan.nextLine();
                long inputAsLong = Long.parseLong(input);
                int length = input.length();

                if (length >= 10 && length <= 13 && inputAsLong > 0) {
                    ISBN = inputAsLong;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid ISBN. Please ensure the ISBN cosists"
                        + " of 10-13 digits without any letters/symbols."
                );
            }
        }

        /**
         * Validation loop with error handling for book Title number. Title is
         * valid so long as it's not empty.
         */
        while (true) {

            System.out.print("Enter Book Title: ");

            try {
                String input = scan.nextLine();

                if (input.trim().length() > 0) {
                    title = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid Title. Please ensure the Title is not empty."
                );
            }
        }

        /**
         * Validation loop with error handling for book Author number. Author is
         * valid so long as it's not empty.
         */
        while (true) {

            System.out.print("Enter Book Author: ");

            try {
                String input = scan.nextLine();

                if (input.trim().length() > 0) {
                    author = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid Author. Please ensure the Author is not empty."
                );
            }
        }

        Book newBook = new Book(ISBN, title, author);
        return newBook;
    }

    /**
     * Prompts and validates user input to set the property values of a new
     * Member object. The values are then passed to the Member() constructor and
     * the new Member is returned by this function.
     *
     * @return Member - new Member - A newly constructed Member
     */
    public static Member ForNewMember() {

        Scanner scan = new Scanner(System.in);

        // Variables for book constructor
        String name;
        String email;
        System.out.println("""
                           
                           ---- Registering New Member ----
                           
                           Please provide the Member's Name & Email Address.
                           """);

        /**
         * Validation loop with error handling for Member Name. Name needs to be
         * less than 50 characters and cannot be empty.
         */
        while (true) {

            System.out.print("Enter Name: ");

            try {
                String input = scan.nextLine();

                if (input.length() > 0 && input.length() < 51) {
                    name = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid Name. Please ensure the Name is not"
                        + " empty and is no more than 50 characters long.");
            }
        }

        /**
         * Validation loop with error handling for Member Email. Email needs to
         * be match the regex pattern to be valid.
         */
        while (true) {

            System.out.print("Enter Email Address: ");

            try {
                String input = scan.nextLine();

                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    email = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid Email. Please ensure the input is a"
                        + " valid email address.");
            }
        }

        Member newMember = new Member(name, email);
        return newMember;
    }

    /**
     * Prompts and validates user input for a book search. First we determine if
     * the search is by book title or author, and then we use a switch case to
     * prompt for the search keyword. Finally, both the search option and
     * keyword are placed in an array which is returned for searchBooks() to
     * use.
     *
     * @return searchParam[] - First element indicates if search is by title or
     * author, second element is the keyword provided by the user.
     */
    public static String[] ForBookSearch() {

        Scanner scan = new Scanner(System.in);
        String[] searchParam = new String[2];
        int choice;

        System.out.println("""
                               --- Search for Books ---
                           
                               Would you like to search by Author or Title?
                               
                               1. Title
                               2. Author
                               """);

        // Input validation loop
        while (true) {

            System.out.print("Option: ");

            try {
                choice = Integer.parseInt(scan.nextLine());
                if (choice > 2 || choice <= 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(
                        "Invalid Choice. Please input a valid digit (1 or 2)."
                );
            }
        }

        // Switch case which prompts for the appropriate search and validates input
        switch (choice) {

            case 1 -> {
                // Search by title
                searchParam[0] = "title";

                while (true) {

                    System.out.print("Enter Book Title: ");

                    // Input validation loop
                    try {
                        String input = scan.nextLine();

                        if (input.length() > 0) {
                            searchParam[1] = input;
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Invalid Title. Please ensure the Title is not empty."
                        );
                    }
                }
            }

            case 2 -> {
                // Search by author
                searchParam[0] = "author";

                // Input validation loop
                while (true) {

                    System.out.print("Enter Book Author: ");

                    try {
                        String input = scan.nextLine();

                        if (input.length() > 0) {
                            searchParam[1] = input;
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Invalid Author. Please ensure the Author is not empty."
                        );
                    }
                }
            }

        }

        return searchParam;
    }

    /**
     * Similar to promptForBookSearch(), but prompts for member search key
     * instead.
     *
     * @return searchParam[] - First element indicates if search is by name or
     * email, second element is the keyword provided by the user.
     */
    public static String[] ForMemberSearch() {

        Scanner scan = new Scanner(System.in);
        String[] searchParam = new String[2];
        int choice;

        System.out.println("""
                               --- Search for Members ---
                           
                               Would you like to search by Name or Email?
                               
                               1. Name
                               2. Email
                               """);

        // Input validation loop
        while (true) {

            System.out.print("Option: ");

            try {
                choice = Integer.parseInt(scan.nextLine());
                if (choice > 2 || choice <= 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(
                        "Invalid Choice. Please input a valid digit (1 or 2)."
                );
            }
        }

        // Switch case which prompts for the appropriate search and validates input
        switch (choice) {

            case 1 -> {
                // Search by Name
                searchParam[0] = "name";

                // Input validation loop
                while (true) {

                    System.out.print("Enter Member Name: ");

                    try {
                        String input = scan.nextLine();

                        if (input.length() > 0) {
                            searchParam[1] = input;
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Invalid Name. Please ensure the Name is not empty."
                        );
                    }
                }
            }

            case 2 -> {
                // Search by email
                searchParam[0] = "email";

                // Input validation using regex. 
                while (true) {

                    System.out.print("Enter Member Email: ");

                    try {
                        String input = scan.nextLine();
                        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
                        Matcher matcher = pattern.matcher(input);
                        if (matcher.matches()) {
                            String searchEmail = input;
                            searchParam[1] = searchEmail;
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Invalid Email. Please ensure the email is a"
                                + " valid email address."
                        );
                    }
                }
            }
        }
        return searchParam;
    }
}
