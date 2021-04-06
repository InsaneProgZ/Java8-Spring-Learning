package yanzingra.learning.rest;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import yanzingra.learning.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yanzingra.learning.repo.StaticDataRepo;

import javax.validation.Valid;
import java.util.List;


@RestController
public class Controller {

    @Autowired
    private final StaticDataRepo userDAO;

    public Controller(StaticDataRepo userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping(name = "/show", produces = {"application/json", "application/xml"})
    public ResponseEntity<Iterable<UserData>> showDataBase() {

        List<UserData> userDataList = userDAO.getData();

        return userDataList.isEmpty() ? new ResponseEntity<>(userDataList, HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(userDataList, HttpStatus.OK);

    }

    @PatchMapping("/save")
    public ResponseEntity<Boolean> saveMultiple(@RequestBody List<UserData> data) {

        try{
            return new ResponseEntity<>(userDAO.save(data) ,HttpStatus.CREATED);

        } catch(Exception saveErrorException) {
            return new ResponseEntity<>(userDAO.save(data) ,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/save")
    public ResponseEntity<Boolean> saveSingle(@RequestBody @Valid UserData data) {
        try{
            return new ResponseEntity<>(userDAO.save(data) ,HttpStatus.CREATED);

        } catch(Exception saveErrorException) {
            return new ResponseEntity<>(userDAO.save(data) ,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody List<UserData> data) {
        try {
            return new ResponseEntity<>(userDAO.delete(data), HttpStatus.OK);
        } catch (Exception cantDeleteException){
            return new ResponseEntity<>(userDAO.delete(data), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}