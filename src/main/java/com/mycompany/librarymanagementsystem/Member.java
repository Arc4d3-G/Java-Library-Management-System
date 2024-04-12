/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author brdde
 */
public class Member {
    
    public String name;
    public String email;
    public List<Book> borrowedBooks = new ArrayList<>();
    
    
    /**
     * Book constructor contains input validation for each property 
     */
    public Member(){
        
        System.out.println("\n---- Registering New Member ----\n");
        System.out.println("Please provide the Member's Name & Email Address.");
        Scanner scan = new Scanner(System.in);
        
        /**
         *  Validation loop with error handling for Member Name.
         *  Name needs to be less than 50 characters and cannot be empty.
         */
        while(true){
            
            System.out.print("Enter Name: ");
            
            try {
                String input = scan.nextLine();
                
                if(input.length() > 0 && input.length() < 51){
                    this.name = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid Name. Please ensure the Name is not empty"
                        + " and is no more than 50 characters long.");
            }
        }
        
        /**
         *  Validation loop with error handling for Member Email.
         *  Email needs to be match the regex pattern to be valid.
         */
        while(true){
            
            System.out.print("Enter Email Address: ");
            
            try {
                String input = scan.nextLine();
                
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if(matcher.matches()){
                    this.email = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid Email. Please ensure the input is a valid email address.");
            }
        }
    }
}
