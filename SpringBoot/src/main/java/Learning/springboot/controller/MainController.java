package Learning.springboot.controller;

import Learning.springboot.model.UserData;
import Learning.springboot.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/user")
public class MainController {
    // TODO(Criar atualizacao de tabela de objeto inteiro (PUT) e de parte de um objeto (PATCH) (Atrelar ao ID)   DONE
    // TODO(JSON PATCH) DONE MELHORAR/REIMPLEMENTAR
    // TODO(HATEOAS) DONE
    // TODO((Checked x Unchecked) exceptions) DONE
    // TODO(Tratamento de excecoes com o @ControllerAdvice)
    // TODO(reflection java)
    // TODO(LOGS)

    @Autowired
    private final UserDataRepository userDAO;

    public MainController(UserDataRepository userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<UserData> showDataBase() {
        List<UserData> users = new ArrayList<>();

        for (UserData userData : userDAO.getUser()) {
            userData.add(linkTo(methodOn(MainController.class).showDataId(userData.getRg())).withRel("/user/{rg}"));
            users.add(userData);
        }

        var selfLink = linkTo(methodOn(MainController.class).showDataBase()).withSelfRel();

        return CollectionModel.of(users, selfLink);
    }

    @GetMapping(value = "/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<UserData> showDataId( @PathVariable int rg) {
        var user = userDAO.getUser(rg);
        user.add(linkTo(methodOn(MainController.class).showDataBase()).withRel("/user"));

        return EntityModel.of(user);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void saveMultiple(@RequestBody @Valid List<UserData> data) {

        userDAO.saveUsers(data);
    }

    @DeleteMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@PathVariable("rg") Integer rg) {

        return userDAO.deleteUser(rg);
    }

    @PutMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public void changeObject(@PathVariable("rg") Integer rg, @RequestBody @Valid UserData data) {
        userDAO.changeUser(data, rg);
    }

    @PatchMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public void changePartObject(@PathVariable("rg") Integer rg, @RequestBody UserData data) {
        userDAO.partChangeUser(data, rg);
    }

/*    @PatchMapping(path = "/{rg}/jsonPatch", consumes = "application/json-patch+json")
    @ResponseStatus(HttpStatus.OK)
    public void changePartObjectJsonPatch (@PathVariable("rg") Integer rg, @RequestBody JsonPatch patchDocument) {
        userDAO.partChangeUserJsonPatch(patchDocument, rg);
    }*/
}