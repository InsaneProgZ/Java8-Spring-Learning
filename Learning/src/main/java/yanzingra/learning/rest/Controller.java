package yanzingra.learning.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import yanzingra.learning.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yanzingra.learning.repo.UserDataRepository;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private final UserDataRepository userDAO;

    public Controller(UserDataRepository userDAO) {
        this.userDAO = userDAO;
    }


    @GetMapping("/show")
    public ResponseEntity<Iterable<UserData>> showDataBase() {

        return new ResponseEntity<>(userDAO.findAll(), HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<Iterable<UserData>> save(@RequestBody List<UserData> data) {

        return new ResponseEntity<>(userDAO.saveAll(data), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
        public ResponseEntity<Void> delete(@RequestBody List<UserData> data) {
        userDAO.deleteAll(data);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}