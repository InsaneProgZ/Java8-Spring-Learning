package yanzingra.learning.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import yanzingra.learning.model.UserData;
import yanzingra.learning.repo.StaticDataRepo;

import javax.json.JsonPatch;
import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class Controller {
    // TODO(Criar atualização de tabela de objeto inteiro (PUT) e de parte de um objeto (PATCH) (Atrelar ao ID)   DONE
    // TODO(JSON PATCH) DONE MELHORAR
    // TODO(HATEOAS)
    // TODO((Checked x Unchecked) exceptions)
    // TODO(Tratamento de exceções com o @ControllerAdvice)
    // TODO(reflection java)
    // TODO(LOGS)
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
    public void changeObject (@PathVariable("rg") Integer rg, @RequestBody @Valid UserData data) {
       userDAO.changeUser(data, rg);
    }

    @PatchMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public void changePartObject (@PathVariable("rg") Integer rg, @RequestBody UserData data) {
        userDAO.partChangeUser(data, rg);
    }

    @PatchMapping(path = "/{rg}/jsonPatch", consumes = "application/json-patch+json")
    @ResponseStatus(HttpStatus.OK)
    public void changePartObjectJsonPatch (@PathVariable("rg") Integer rg, @RequestBody JsonPatch patchDocument) {
        userDAO.partChangeUserJsonPatch(patchDocument, rg);
    }

}