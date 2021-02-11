package yanzingra.learning.repo;

import org.springframework.data.repository.CrudRepository;
import yanzingra.learning.model.UserData;

import java.util.List;

public interface UserDataRepository extends CrudRepository<UserData, Long> {
    List<UserData> findByName(String name);
}
