package yanzingra.learning.repo;

import org.springframework.stereotype.Repository;
import yanzingra.learning.model.UserData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StaticDataRepo {

    List<UserData> dataList;

    public StaticDataRepo() {

        this.dataList = new ArrayList<>();
    }

    public Boolean saveUser(UserData data) {
        boolean dataToSave = dataList.stream().anyMatch(p -> p.getRg().equals(data.getRg()));

        if (dataToSave) return false;
        return dataList.add(data);
    }

    public Boolean deleteUser(Integer id) {
        List<UserData> dataListToDelete = this.dataList.stream()
                .filter(data -> data.getAge().equals(id)).collect(Collectors.toList());
        return dataList.removeAll(dataListToDelete);
    }

    public List<UserData> getUser() {
        return dataList;
    }

    public void changeUser (UserData data, Integer id){
        dataList = dataList.stream()
                .filter( dataFilter -> dataFilter.getRg().equals(BigDecimal.valueOf(id)))
                .map(d -> d=data)
                .collect(Collectors.toList());
    }
}
