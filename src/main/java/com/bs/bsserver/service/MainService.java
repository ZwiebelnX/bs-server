package com.bs.bsserver.service;

import com.bs.bsserver.repository.docker.BookRepo;
import com.bs.bsserver.repository.docker.UserRepo;
import com.bs.bsserver.repository.entities.BookEntity;
import com.bs.bsserver.repository.entities.UserEntity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MainService {
    private final BookRepo bookRepo;
    private final UserRepo userRepo;

    @Autowired
    public MainService(BookRepo bookRepo, UserRepo userRepo){
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public Iterable<UserEntity> findallUsers(){
        return userRepo.findAll();
    }
    public boolean login(JSONObject jsonObject){
        long id;
        String password;
        try{
            id=Long.parseLong(jsonObject.getString("id"));
            password=jsonObject.getString("password");

        }catch (Exception e){
            return false;
        }
        UserEntity user=userRepo.findById(id).get();
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }
    public  boolean deleteBook(long id){

        try {

            BookEntity newBook = bookRepo.findById(id).get();
            bookRepo.delete(newBook);
        }catch (Exception e){
            return false;
        }
        return true;


    }


    public boolean modifyBook(JSONObject jsonObject){
        String bookName;
        String publisher;
        String publishTime;
        String author;
        String ISBN;
        String pages;
        long id;
        try{
            id=Long.parseLong(jsonObject.getString("id"));
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
        BookEntity newBook=bookRepo.findById(id).get();
        newBook.setId(id);
        newBook.setName(bookName);
        newBook.setPublisher(publisher);
        newBook.setPublishTime(publishTime);
        newBook.setAuthor(author);
        newBook.setIsbn(ISBN);
        newBook.setPages(pages);


        bookRepo.save(newBook);
        return true;
    }
    public Iterable<BookEntity> findallBooks(){
        return bookRepo.findAll();
    }
    //按书名查找
    public List<BookEntity> findBooks(JSONObject jsonObject){
        try{
            String name=jsonObject.getString("name");
            return bookRepo.findAllByNameLike("%"+name+"%");
        }catch (Exception e){
            return null;
        }
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
