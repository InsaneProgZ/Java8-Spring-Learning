package learning.springboot.repository;


import learning.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDataRepository {

    @Autowired
    private List<User> dataList;

    public void saveUsers(@NotBlank @NotNull List<User> data) {

        var dataExists = false;

        for (User user : data) {
            if (dataList.stream().anyMatch(d -> d.getRg().equals(user.getRg()))) {
                dataExists = true;
                break;
            }
        }
        if (!dataExists) {
            dataList.addAll(data);
        } else {
            throw new KeyAlreadyExistsException("Este RG ja foi registrado no sistema senhor, ta tentando me enganar?");
        }
    }

    public Boolean deleteUser(Integer id) {
        List<User> dataListToDelete = this.dataList.stream()
                .filter(data -> data.getAge().equals(id)).toList();
        return dataList.removeAll(dataListToDelete);
    }

    public List<User> getUsers() {
        return dataList;
    }

    public User getUser(Integer rg) {

        List<User> users = dataList.stream().filter(d -> d.getRg().equals(rg)).toList();

        return users.isEmpty() ? null : users.get(0);
    }

    public List<User> getUserById(Integer rg) {

        return dataList.stream()
                .filter(data -> data.getRg().equals(rg)).toList();
    }

    public void changeUser(User data, Integer rg) {
        dataList = dataList.stream()
                .map(d -> {
                    if (d.getRg().equals(rg))
                        return data;
                    return d;
                }).collect(Collectors.toList());
    }

    public void partChangeUserWithoutPatch(User data, Integer rg) {
        dataList.forEach(d -> {
            if (d.getRg().equals(rg)) {
                if (data.getName() != null) d. setName(data.getName());
                if (data.getAge() != null) d.setAge(data.getAge());
                if (data.getRg() != null) d.setRg(data.getRg());
            }
        });
    }
}