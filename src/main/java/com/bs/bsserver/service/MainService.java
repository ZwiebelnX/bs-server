package com.bs.bsserver.service;

import com.bs.bsserver.repository.docker.BookRepo;
import com.bs.bsserver.repository.docker.UserRepo;
import com.bs.bsserver.repository.entities.BookEntity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MainService {
    private final BookRepo bookRepo;
    private final UserRepo userRepo;

    @Autowired
    public MainService(BookRepo bookRepo, UserRepo userRepo){
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }


    //添加图书
    @Transactional
    public boolean addBook(JSONObject jsonObject){
        String bookName;
        String publisher;
        String publishTime;
        String author;
        String ISBN;
        String pages;
        try{
            bookName = jsonObject.getString("name");
            publisher = jsonObject.getString("publisher");
            publishTime = jsonObject.getString("publishTime");
            author = jsonObject.getString("author");
            ISBN = jsonObject.getString("ISBN");
            pages = jsonObject.getString("pages");
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        BookEntity newBook = new BookEntity();
        newBook.setName(bookName);
        newBook.setPublisher(publisher);
        newBook.setPublishTime(publishTime);
        newBook.setAuthor(author);
        newBook.setIsbn(ISBN);
        newBook.setPages(pages);

        bookRepo.save(newBook);
        return true;
    }
}
