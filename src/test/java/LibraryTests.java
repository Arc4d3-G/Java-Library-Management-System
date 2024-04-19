
import com.mycompany.librarymanagementsystem.Book;
import com.mycompany.librarymanagementsystem.Library;
import com.mycompany.librarymanagementsystem.Member;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class containing some unit tests for the Library Class. 
 */
public class LibraryTests {

    // Library instance for testing
    Library library = new Library();

    // Hard coded books and members for testing
    Book book1 = new Book(1111111111111L, "The Fellowship of the Ring", "J.R. Tolkien", true);
    Book book2 = new Book(222222222222L, "The Two Towers", "J.R. Tolkien", true);
    Book book3 = new Book(33333333333L, "The Return of the King", "J.R. Tolkien", true);
    Book book4 = new Book(4444444444L, "Moby Dick", "Herman Melville", false);
    Book book5 = new Book(5555555555555L, "1984", "George Orwell", false);
    Member member1 = new Member("Dewald Breed", "dewaldbreed@gmail.com");
    Member member2 = new Member("Leandri Breed", "leandribreed@gmail.com");
    Member member3 = new Member("Jade Hastings", "jadehastings@protonmail.com");
    Member member4 = new Member("Steve", "steve@stevemail.co.za");

    Book[] testingBooks = {book1, book2, book3, book4, book5};
    Member[] testingMembers = {member1, member2, member3, member4};

    /**
     * Loop through testingBooks, adding each book to the library via addBook(),
     * and asserting that the book added is the same book at the very end of
     * libraryBooks list.
     *
     * Next we attempt to add the same 5 books to the library and assert that
     * libraryBooks did not increase in size, indicating that the duplicates
     * were not added.
     */
    @Test
    @DisplayName("Adding Books to Library")
    void testAddingBooks() {
        for (int i = 0; i < 5; i++) {
            Book book = testingBooks[i];
            library.addBook(book);
            int length = library.libraryBooks.size();
            assertEquals(book, library.libraryBooks.get(length - 1), "Adding Failed");
        }
        for (int i = 0; i < 5; i++) {
            library.addBook(testingBooks[i]);
        }
        assertEquals(5, library.libraryBooks.size(), "Duplicate Added");
    }

    /**
     * Same as the previous test, but for Members.
     */
    @Test
    @DisplayName("Adding Member's to Library")
    void testAddingMembers() {
        for (int i = 0; i < 4; i++) {
            Member member = testingMembers[i];
            library.addMember(member);
            int length = library.libraryMembers.size();
            assertEquals(member, library.libraryMembers.get(length - 1), "Adding Failed");
        }

        for (int i = 0; i < 4; i++) {
            library.addMember(testingMembers[i]);
        }
        assertEquals(4, library.libraryMembers.size(), "Duplicate Added");
    }

    /**
     * When calling viewBooks() method with an empty libraryBooks, the output
     * should be "No Results Found". I could not figure out how to
     * programmatically assert this output, so It's best to just view the logs
     * to verify.
     *
     * We then add the testing books and call the method again. This time we
     * should see a table being printed displaying the books and their details.
     */
    @Test
    @DisplayName("Displaying Books in a table format")
    void testViewBooks() {
        // Output should be "No Results Found"
        library.viewBooks(library.libraryBooks);

        // Add books to library
        for (int i = 0; i < 5; i++) {
            Book book = testingBooks[i];
            library.addBook(book);
        }

        // Output should be a table displaying book info
        library.viewBooks(library.libraryBooks);

    }

    /**
     * Similar to the previous test, but for members. Again, review the log to
     * verify if the output is correct.
     */
    @Test
    @DisplayName("Displaying Members in a table format")
    void testViewMembers() {
        // Output should be "No Results Found"
        library.viewMembers(library.libraryMembers);

        // Add members to library
        for (int i = 0; i < 4; i++) {
            Member member = testingMembers[i];
            library.addMember(member);
        }

        // Output should be a table displaying member info
        library.viewMembers(library.libraryMembers);
    }

    /**
     * Test book search method by creating a mock searchParam[] and passing it
     * to searchBooks(). The matching books are added to a new list which can
     * use for assertions.
     */
    @Test
    @DisplayName("Search for Books")
    void testSearchBooks() {

        // Add books to library
        for (int i = 0; i < 5; i++) {
            Book book = testingBooks[i];
            library.addBook(book);
        }
        String[] searchParam = {"title", "the"};
        List<Book> bookSearchResults = library.searchBooks(searchParam);

        /**
         * 3 books in our test library contain the word "the", so we can assert
         * the size of our searchResults list is 3.
         */
        assertEquals(3, bookSearchResults.size(), "Search inacurate");
    }

    /**
     * Similar to the previous test, but for members instead.
     */
    @Test
    @DisplayName("Search for Members")
    void testSearchMembers() {

        // Add members to library
        for (int i = 0; i < 4; i++) {
            Member member = testingMembers[i];
            library.addMember(member);
        }

        String[] searchParam = {"email", "breed@gmail"};
        List<Member> memberSearchResults = library.searchMembers(searchParam);

        /**
         * 2 member's emails in our test library contain the string
         * "breed@gmail", so we can assert the size of our searchResults list is
         * 2.
         */
        assertEquals(2, memberSearchResults.size(), "Search inacurate");
    }

}
