package Learning.springboot.controller;

import Learning.springboot.model.UserData;
import Learning.springboot.repository.UserDataRepository;
import Learning.springboot.util.UtilMethods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private final UtilMethods utilMethods;

    public MainController(UserDataRepository userDAO, UtilMethods utilMethods) {
        this.userDAO = userDAO;
        this.utilMethods = utilMethods;
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

//    @PatchMapping("/{rg}")
//    @ResponseStatus(HttpStatus.OK)
//    public void changePartObject(@PathVariable("rg") Integer rg, @RequestBody UserData data) {
//        userDAO.partChangeUser(data, rg);
//    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserData> updateCustomer(@PathVariable int id, @RequestBody JsonPatch patch) {
        try {
            UserData customer = userDAO.getUser(id);
            UserData customerPatched = utilMethods.applyPatchToCustomer(patch, customer);
            userDAO.changeUser(customerPatched, id);
            return ResponseEntity.ok(customerPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


/*    @PatchMapping(path = "/{rg}/jsonPatch", consumes = "application/json-patch+json")
    @ResponseStatus(HttpStatus.OK)
    public void changePartObjectJsonPatch (@PathVariable("rg") Integer rg, @RequestBody JsonPatch patchDocument) {
        userDAO.partChangeUserJsonPatch(patchDocument, rg);
    }*/
}