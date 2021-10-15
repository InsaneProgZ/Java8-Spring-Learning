package learning.springboot.service;

import learning.springboot.model.User;
import learning.springboot.repository.UserDataRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserDataRepository userDAO;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
}
