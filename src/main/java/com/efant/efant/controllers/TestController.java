package com.efant.efant.controllers;

import com.efant.efant.model.dtos.UserCriteria;
import com.efant.efant.model.entities.TestEntity;
import com.efant.efant.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    private TestService testService;

    @Autowired
    public TestController(TestService testService){
        this.testService = testService;
    }


    @GetMapping("/test")
    public List<TestEntity> getAllTests() throws Exception{


        // run code to get the user from the database
        return testService.getAllTests();
    }


    @GetMapping("test/{id}")
    public TestEntity getTestById(@PathVariable Long id) throws Exception{
        TestEntity testEntity = testService.getTestById(id);
        return testEntity;
    }

    @PostMapping("/test")
    @ResponseStatus(value = HttpStatus.CREATED)
    public TestEntity createTest(@RequestBody TestEntity testEntity) throws Exception{
        testEntity = testService.createTest(testEntity);
        return testEntity;
    }







}
