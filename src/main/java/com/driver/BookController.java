package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody() Book book){
       // new BookController();
        book.setId(id);
        id++;
        bookList.add(book);// Your code goes here.
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id)
    {
        for(int i=0; i<bookList.size(); i++)
        {
            if(bookList.get(i).getId() == id) return new ResponseEntity(bookList.get(i) , HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(new Book() , HttpStatus.ACCEPTED);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    @DeleteMapping("/delete-book-by-id/{id}")
     public ResponseEntity<String> deleteBookById(@PathVariable("id") int id)
     {
         for(int i=0; i<bookList.size(); i++) {
             if (bookList.get(i).getId() == id) {
                 bookList.remove(i);
             }
         }
         return new ResponseEntity("deleted book with given id" , HttpStatus.ACCEPTED);
     }

    // get request /get-all-books
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        List<Book> ans = new ArrayList<>();
        for(int i=0; i<bookList.size(); i++)
        {
            ans.add(bookList.get(i));
        }
        return new ResponseEntity(ans , HttpStatus.ACCEPTED);
    }

    // delete request /delete-all-books
    @DeleteMapping("/delete-all-books")
      public ResponseEntity<String> deleteAllBooks()
      {
          bookList.clear();
          return new ResponseEntity("all books are removed" , HttpStatus.ACCEPTED);
      }

    // get request /get-books-by-author
    // pass author name as request param
    @GetMapping("/get-books-by-author")
    public ResponseEntity<Book> getBooksByAuthor(@RequestParam("author") String author)
    {
        for(int i=0; i<bookList.size(); i++) {
            if (bookList.get(i).getAuthor().equals(author)) {
                return new ResponseEntity(bookList.get(i) , HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity(new Book() , HttpStatus.ACCEPTED);
    }

    // get request /get-books-by-genre
    // pass genre name as request param
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<Book> getBooksByGenre(@RequestParam("genre") String g)
    {
        for(int i=0; i<bookList.size(); i++) {
            if (bookList.get(i).getGenre().equals(g)) {
                return new ResponseEntity(bookList.get(i) , HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity(new Book() , HttpStatus.ACCEPTED);
    }
}
