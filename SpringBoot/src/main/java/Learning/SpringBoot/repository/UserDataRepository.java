package Learning.SpringBoot.repository;


import Learning.SpringBoot.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDataRepository {

    @Autowired
    List<UserData> dataList;

    public void saveUser(UserData data) {

//       boolean dataToSave = dataList.stream().anyMatch(p -> p.getRg().equals(data.getRg()));
        dataList.add(data);
    }

    public Boolean deleteUser(Integer id) {
        List<UserData> dataListToDelete = this.dataList.stream()
                .filter(data -> data.getAge().equals(id)).collect(Collectors.toList());
        return dataList.removeAll(dataListToDelete);
    }

    public List<UserData> getUser() { return dataList; }

    public List<UserData> getUser(Integer rg){

        return dataList.stream().filter( d -> d.getRg().equals(rg)).collect(Collectors.toList());
    }


    public List<UserData> getUserById(Integer rg) {

        return dataList.stream()
                .filter(data -> data.getRg().equals(rg))
                .collect(Collectors.toList());
    }

    public void changeUser(UserData data, Integer rg) {
        dataList = dataList.stream()
                .map(d -> {
                    if (d.getRg().equals(rg))
                        return data;
                    return d;
                })
                .collect(Collectors.toList());
    }

    public void partChangeUser(UserData data, Integer rg) {
        dataList = dataList.stream()
                .peek(d -> {
                    if (d.getRg().equals(rg)) {
                        if (data.getName() != null) d.setName(data.getName());
                        if (data.getAge() != null) d.setAge(data.getAge());
                        if (data.getRg() != null) d.setRg(data.getRg());
                    }
                })
                .collect(Collectors.toList());
    }
}
