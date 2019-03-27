package com.bs.bsserver.Controller;

import com.bs.bsserver.repository.docker.BookRepo;
import com.bs.bsserver.repository.entities.BookEntity;
import com.bs.bsserver.repository.entities.UserEntity;
import com.bs.bsserver.service.MainService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class MainController {
    private final MainService mainService;


    @Autowired
    public MainController(MainService mainService){
        this.mainService = mainService;
    }



    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity Login(@RequestBody String jsonString){
        JSONObject json = new JSONObject(jsonString);
        if(mainService.login(json)) return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    @RequestMapping(value = "/logins")
    public List<UserEntity> Logins(){
        return (List<UserEntity>) mainService.findallUsers();
    }
    //添加图书
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity addBook(@RequestBody String jsonString){
        JSONObject json = new JSONObject(jsonString);
        if(mainService.addBook(json)) return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    //修改图书
    @RequestMapping(value = "/modifyBook", method = RequestMethod.POST)
    public ResponseEntity modifyBook(@RequestBody String jsonString){
        JSONObject json = new JSONObject(jsonString);
        if(mainService.modifyBook(json)) return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity addBook(@PathVariable long id){


        if(mainService.deleteBook(id)) return new ResponseEntity<Boolean>(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);

    }

    //找出全部图书
    @RequestMapping(value = "/books")
    public List<BookEntity> findallBook(){

        return (List<BookEntity>) mainService.findallBooks();
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public List<BookEntity> Search(@RequestBody String jsonString){

        JSONObject json = new JSONObject(jsonString);
        return (List<BookEntity>) mainService.findBooks(json);
    }
}
