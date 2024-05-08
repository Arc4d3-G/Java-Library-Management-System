package com.mycompany.librarymanagementsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.json.simple.JSONObject;

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
    public Member borrowedByMember;

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
    
    /**
     * Method creates a new JSONObject with each book property as
     * a key:value pair.
     * 
     * @return JSONObject
     */
    public JSONObject toJSON(){
        JSONObject bookObj = new JSONObject();
        bookObj.put("title", title);
        bookObj.put("ISBN",ISBN);
        bookObj.put("author", author);
        bookObj.put("isAvailable", isAvailable);
        bookObj.put("dueDate", dueDate != null ? dueDate.toString() : "none");
        bookObj.put("isOverDue", isOverDue);
        bookObj.put("borrowedByMember", borrowedByMember != null ? borrowedByMember.email : "none" );
        
        return bookObj;
    }
    
    public boolean checkIfOverDue(int checkOutPeriod) {
        return dueDate != null && LocalDate.now().isAfter(dueDate);
    }
    
    public long calcFees(int checkOutPeriod, int dayLateFee) {
        if (isOverDue){
            long daysLate = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            long totalFee = dayLateFee * daysLate;
            return totalFee;
        } else {
            return 0;
        }
        
    }

}
