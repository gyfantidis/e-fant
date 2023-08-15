package com.efant.efant.services;

import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.entities.TestEntity;
import com.efant.efant.model.entities.User;
import com.efant.efant.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private TestRepository testRepository;


    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    public List<TestEntity> getAllTests() {
        return testRepository.findAll();
    }

    public TestEntity getTestById(Long id) throws Exception {
        return testRepository.findById(id)
                .orElseThrow(() -> new EfantException("USER_NOT_FOUND", "User not exists with id: " + id, HttpStatus.NOT_FOUND));

    }


    public TestEntity createTest(TestEntity testEntity) throws Exception {
        if (testEntity.getId() != null) {
            throw new EfantException("NEW_USER_ID_IS_NOT_NULL", "User id must be null", HttpStatus.BAD_REQUEST);
        }

        testEntity = testRepository.save(testEntity);
        return testEntity;
    }

}
