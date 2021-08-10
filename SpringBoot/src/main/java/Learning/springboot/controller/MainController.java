package Learning.springboot.controller;

import Learning.springboot.model.UserData;
import Learning.springboot.repository.UserDataRepository;
import Learning.springboot.util.UtilMethods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/user")
@Validated
@AllArgsConstructor()
public class MainController {

    @Autowired
    private final UserDataRepository userDAO;

    @Autowired
    private final UtilMethods utilMethods;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(produces = {"application/hal+json"})
    @ResponseStatus(HttpStatus.OK)
    public List <UserData> showDataBase() {

//        List<UserData> users = new ArrayList<>(userDAO.getUsers());
//
//        users.forEach(data -> data.add(linkTo(methodOn(MainController.class).showDataId(data.getRg())).withRel("get")));
//
//        var selfLink = linkTo(methodOn(MainController.class).showDataBase()).withSelfRel();

//        return CollectionModel.of(users, selfLink);
       return userDAO.getUsers();
    }

    @GetMapping(value = "/{rg}")
    public EntityModel<UserData> showDataId(@PathVariable int rg) {
        var user = userDAO.getUser(rg);
        user.add(linkTo(methodOn(MainController.class).showDataBase()).withRel("/user"));

        return EntityModel.of(user);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMultiple(@RequestBody @Valid List<UserData> data) {
        userDAO.saveUsers(data);
        logger.info("Se esta lendo isso, essas informacoes foram salvas : {} ", data);
    }

    @DeleteMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("rg") Integer rg) {

        userDAO.deleteUser(rg);
    }

    @PutMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public void changeObject(@PathVariable("rg") Integer rg, @RequestBody @Valid UserData data) {
        userDAO.changeUser(data, rg);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserData> updateCustomer(@PathVariable Integer id, @RequestBody JsonPatch patch) throws JsonPatchException {
        try {
            UserData customer = userDAO.getUser(id);
            UserData customerPatched = utilMethods.applyPatchToCustomer(patch, customer);
            userDAO.changeUser(customerPatched, id);
            return ResponseEntity.ok(customerPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new JsonPatchException("JsonPatch exception");
        }
    }
}