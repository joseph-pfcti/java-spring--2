package com.pfcti.springreactive.api;

import com.pfcti.springreactive.model.Employee;
import com.pfcti.springreactive.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee employee) {
        employeeService.create(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Employee>> findById (@PathVariable("id") Integer id) {
        Mono<Employee> employee = employeeService.findById(id);
        return new ResponseEntity<Mono<Employee>>(employee, HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return employeeService.findByName(name);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> findAll() {
        return employeeService.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update (Employee employee) {
        return employeeService.update(employee);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete (Integer id) {
        employeeService.deleteById(id);
    }
}
