package learning.springboot.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import learning.springboot.model.User;
import learning.springboot.repository.UserDataRepository;
import learning.springboot.service.UserService;
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
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@AllArgsConstructor
public class MainController {

    @Autowired
    private final UserDataRepository userDAO;

    @Autowired
    private final UtilMethods utilMethods;

    @Autowired
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(produces = {"application/hal+json"})
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<User> showAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping(value = "/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<User> showUserInfoById(@PathVariable int rg) {
        return userService.getUserById(rg);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUsers(@RequestBody @Valid List<User> userList) {
        userService.SaveUser(userList);
    }

    @DeleteMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable("rg") Integer rg) {
        userService.deleteUser(rg);
    }

    @PutMapping("/{rg}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("rg") Integer rg, @RequestBody @Valid User user) {
        userService.updateUser(rg, user);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateCustomer(@PathVariable Integer id, @RequestBody JsonPatch patch) throws JsonPatchException {

        return new ResponseEntity<>(userService.updateCustomer(id, patch), HttpStatus.OK);
    }
}