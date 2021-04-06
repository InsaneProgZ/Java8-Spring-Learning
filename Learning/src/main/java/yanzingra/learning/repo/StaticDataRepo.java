package yanzingra.learning.repo;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import yanzingra.learning.model.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StaticDataRepo {

    List<UserData> data = new ArrayList<>();

    public StaticDataRepo() {

        this.data = new ArrayList<>();
    }

    public Boolean save(List<UserData> data) {

        try{
            this.data.addAll(data);
            return true;
        } catch (Exception saveErrorException) {

            throw saveErrorException;
        }

    }
    public Boolean save(UserData data) {

        try{
            this.data.add(data);
            return true;
        } catch (Exception saveErrorException) {

            throw saveErrorException;
        }

    }

    public Boolean delete(List<UserData> data) {

        List<UserData> toDeleteData =this.data.stream()
                .filter(f -> data.stream()
                        .map(UserData::getName)
                        .collect(Collectors.toList()).contains(f.getName()))
                .collect(Collectors.toList());
        try {
            this.data.removeAll(toDeleteData);
            return true;
        } catch (Exception cantRemoveException) {
            throw  cantRemoveException;
        }

    }

    public List<UserData> getData() {
        return data;
    }
}
