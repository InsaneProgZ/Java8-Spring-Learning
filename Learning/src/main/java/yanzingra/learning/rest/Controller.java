package yanzingra.learning.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yanzingra.learning.model.UserData;
import yanzingra.learning.repo.StaticDataRepo;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class Controller {
    // TODO(Criar atualização de tabela de objeto inteiro (PUT) e de parte de um objeto (PATCH) (Atrelar ao ID)
    // TODO(JSON PATCH)
    // TODO(HATEOAS)
    // TODO(Tratamento de exceções com o @ControllerAdvice)
    // TODO((Checked x Unchecked) exceptions)
    @Autowired
    private final StaticDataRepo userDAO;

    public Controller(StaticDataRepo userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<UserData> showDataBase() {

        return userDAO.getUser();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public boolean saveSingle(@RequestBody @Valid UserData data) {

        return userDAO.saveUser(data);
    }


    @DeleteMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@PathVariable("rg") Integer rg) {

            return userDAO.deleteUser(rg);
    }
    @PutMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public void changeObjetc (@PathVariable("rg") Integer rg, @RequestBody @Valid UserData data) {
       userDAO.changeUser(data, rg);
    }


}