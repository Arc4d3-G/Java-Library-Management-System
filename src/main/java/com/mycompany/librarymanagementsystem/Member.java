package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


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
    
    /**
     * Method creates a new JSONObject with each member property as
     * a key:value pair.
     * 
     * @return JSONObject
     */
    public JSONObject toJSON(){
        JSONObject memberObj = new JSONObject();
        memberObj.put("name", name);
        memberObj.put("email",email);
        JSONArray borrowedBooksArr = new JSONArray();
        if(!borrowedBooks.isEmpty()){
            for(Book book : borrowedBooks){
                borrowedBooksArr.add(book.ISBN);
            }
            memberObj.put("borrowedBooks", borrowedBooksArr);
        } else {
            memberObj.put("borrowedBooks", "none");
        }

        return memberObj;
    }
}
