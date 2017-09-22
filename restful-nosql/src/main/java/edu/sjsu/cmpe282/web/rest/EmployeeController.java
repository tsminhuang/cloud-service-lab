package edu.sjsu.cmpe282.web.rest;

import edu.sjsu.cmpe282.dao.EmployeeRepository;
import edu.sjsu.cmpe282.domain.Employee;
import edu.sjsu.cmpe282.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private EmployeeService service;

    @PostConstruct
    public void buildData() {
        repo.deleteAll();
        repo.save(new Employee(1, "A", "a"));
        repo.save(new Employee(2, "B", "b"));
        repo.save(new Employee(3, "C", "c"));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getAll() {

        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Employee delete(@PathVariable("id") int id) {
        return service.delete(id);
    }

}
