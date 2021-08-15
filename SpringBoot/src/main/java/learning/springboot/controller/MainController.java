package learning.springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import learning.springboot.model.UserData;
import learning.springboot.repository.UserDataRepository;
import learning.springboot.util.UtilMethods;
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
    public CollectionModel<UserData> showAllUsers() {

        List<UserData> userList = new ArrayList<>(userDAO.getUsers());

        userList.forEach(data -> data.add(linkTo(methodOn(MainController.class).showUserInfoById(data.getRg())).withRel("get")));

        var selfLink = linkTo(methodOn(MainController.class).showAllUsers()).withSelfRel();

        logger.info("m=showAllUsers  userList={} ", userList);

        return CollectionModel.of(userList, selfLink);
    }

    @GetMapping(value = "/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<UserData> showUserInfoById(@PathVariable int rg) {
        var user = userDAO.getUser(rg);
        user.add(linkTo(methodOn(MainController.class).showAllUsers()).withRel("/user"));

        logger.info("m=showUserInfo  user={} ", user);
        return EntityModel.of(user);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUSers (@RequestBody @Valid List<UserData> userList) {
        userDAO.saveUsers(userList);
        logger.info("m=createUsers  users={} ", userList);
    }

    @DeleteMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable("rg") Integer rg) {

        logger.info("m=deleteUserById  rg={} ", rg);
        userDAO.deleteUser(rg);
    }

    @PutMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("rg") Integer rg, @RequestBody @Valid UserData user) {

        logger.info("m=updateUser  rg={} updatedUser={}", rg, user);
        userDAO.changeUser(user, rg);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
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