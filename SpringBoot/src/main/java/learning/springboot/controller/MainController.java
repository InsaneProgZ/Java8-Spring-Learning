package learning.springboot.controller;

import learning.springboot.model.User;
import learning.springboot.repository.UserDataRepository;
import learning.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


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

}