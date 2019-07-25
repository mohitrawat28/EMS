package com.airtel.mongo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.airtel.mongo.models.Employee;

@Repository
public class EmployeeDaoImp implements EmployeeDao {

    @Autowired
    MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "employee";

    public List<Employee> listEmployee() {
        BasicQuery query1 = (BasicQuery) new BasicQuery("{  }");
        return mongoTemplate.find(query1, Employee.class);
    }

    public void add(Employee empl) {
        if (!mongoTemplate.collectionExists(Employee.class)) {
            mongoTemplate.createCollection(Employee.class);

        }
        
        empl.setId(UUID.randomUUID().toString());
        mongoTemplate.insert(empl, COLLECTION_NAME);
    }

    public void update(Employee empl) {
        mongoTemplate.save(empl);
    }

    public void delete(Employee empl) {
        mongoTemplate.remove(empl, COLLECTION_NAME);
    }

    public Employee findEmployeeById(String id) {
        return mongoTemplate.findById(id, Employee.class);
    }
}