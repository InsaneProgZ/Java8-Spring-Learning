package yanzingra.learning.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import yanzingra.learning.model.UserData;

import javax.json.JsonPatch;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StaticDataRepo {

    List<UserData> dataList;

    @Autowired
    ObjectMapper mapper;

    public StaticDataRepo(List<UserData> dataList) {
        this.dataList = dataList;
    }

    @Valid
    public <T> T patch(JsonPatch patch, T targetBean, Class<T> beanClass) {

        // Convert the Java bean to a JSON document
        JsonStructure target = mapper.convertValue(targetBean, JsonStructure.class);

        // Apply the JSON Patch to the JSON document
        JsonValue patched = patch.apply(target);

        // Convert the JSON document to a Java bean and return it
        return mapper.convertValue(patched, beanClass);
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

    public List<UserData> getUserById(BigDecimal rg) {

        return dataList.stream()
                .filter(data -> data.getRg().equals(rg))
                .collect(Collectors.toList());
    }

    public void changeUser(UserData data, Integer rg) {
        dataList = dataList.stream()
                .map(d -> {
                    if (d.getRg().equals(BigDecimal.valueOf(rg)))
                        return data;
                    return d;
                })
                .collect(Collectors.toList());
    }

    public void partChangeUser(UserData data, Integer rg) {
        dataList = dataList.stream()
                .map(d -> {
                    if (d.getRg().equals(BigDecimal.valueOf(rg))) {
                        if (data.getName() != null) d.setName(data.getName());
                        if (data.getAge() != null) d.setAge(data.getAge());
                        if (data.getRg() != null) d.setRg(data.getRg());
                    }
                    return d;
                })
                .collect(Collectors.toList());
    }

    public void partChangeUserJsonPatch(JsonPatch patchDocument, Integer rg) {
        UserData originalData = dataList.stream().filter( data -> data.getRg().equals(BigDecimal.valueOf(rg))).collect(Collectors.toList()).get(0);

        UserData userPatched = patch(patchDocument, originalData, UserData.class);

        changeUser(userPatched, rg);
    }
}
