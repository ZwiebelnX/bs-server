package com.bs.bsserver.controller;

import com.bs.bsserver.service.MainService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService){
        this.mainService = mainService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestBody String jsonString){
        return null;
    }

    //添加图书
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity addBook(@RequestBody String jsonString){
        JSONObject json = new JSONObject(jsonString);
        if(MainService.addBook(json)) return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    
    //删除图书
    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBook(@PathVariable long id){
        long id = id;
        if(MainService.deleteBook(id))  return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String Search(@RequestParam(value = "search") String searchString){
        return null;
    }
}
