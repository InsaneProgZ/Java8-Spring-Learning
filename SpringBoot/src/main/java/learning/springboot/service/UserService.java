package learning.springboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import learning.springboot.controller.MainController;
import learning.springboot.model.User;
import learning.springboot.repository.UserDataRepository;
import learning.springboot.util.UtilMethods;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class UserService {


    @Autowired
    private final UserDataRepository userDAO;

    @Autowired
    private final UtilMethods utilMethods;


    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public CollectionModel<User> getAllUsers() {

        List<User> userList = new ArrayList<>(userDAO.getUsers());

        userList.forEach(data -> data.add(linkTo(methodOn(MainController.class).showUserInfoById(data.getRg())).withRel("get")));

        var selfLink = linkTo(methodOn(MainController.class).showAllUsers()).withSelfRel();

        logger.info("m=getAllUsers  userList={} ", userList);

        return CollectionModel.of(userList, selfLink);
    }

    public EntityModel<User> getUserById(int rg) {
        var user = userDAO.getUser(rg);
        user.add(linkTo(methodOn(MainController.class).showAllUsers()).withRel("/user"));

        logger.info("m=getUserById  user={} ", user);
        return EntityModel.of(user);
    }

    public void SaveUser(@RequestBody @Valid List<User> userList) {
        userDAO.saveUsers(userList);
        logger.info("m=SaveUser  users={} ", userList);
    }

    public void deleteUser(@PathVariable("rg") Integer rg) {

        logger.info("m=deleteUser  rg={} ", rg);
        userDAO.deleteUser(rg);
    }

    public void updateUser(@PathVariable("rg") Integer rg, @RequestBody @Valid User user) {

        logger.info("m=updateUser  rg={} updatedUser={}", rg, user);
        userDAO.changeUser(user, rg);
    }

    public User updateCustomer(@PathVariable Integer id, @RequestBody JsonPatch patch) throws JsonPatchException {
        try {
            User customer = userDAO.getUser(id);
            User customerPatched = utilMethods.applyPatchToCustomer(patch, customer);
            userDAO.changeUser(customerPatched, id);
            return customerPatched;
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new JsonPatchException("JsonPatch exception");
        }
    }
}
