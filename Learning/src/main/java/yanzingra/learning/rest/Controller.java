package yanzingra.learning.rest;

import yanzingra.learning.Data;
import yanzingra.learning.repo.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    DataRepo repository;

    @GetMapping("/show")
    public List<Data> showDataBase() {

        return repository.getData();

    }

    @PutMapping("/save")
    public List<Data> save(@RequestBody List<Data> data) {

        repository.save(data);
        return data;

    }

    @DeleteMapping("/delete")
    public List<Data> delete(@RequestBody List<Data> data) {

        return repository.delete(data);

    }

}