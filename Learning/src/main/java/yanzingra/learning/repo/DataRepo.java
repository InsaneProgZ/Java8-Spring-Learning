package yanzingra.learning.repo;

import yanzingra.learning.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataRepo {

    List<Data> data;

    public DataRepo() {

        this.data = new ArrayList<>();
    }

    public void save(List<Data> data) {

        this.data.addAll(data);
    }

    public List<Data> delete(List<Data> data) {

        this.data.removeIf(dell -> {
            for (Data deleteDatas : data) {
                if (dell.getName().equals(deleteDatas.getName())) {
                    return true;
                }
            }
            return false;
        });

        return data;

    }

    public List<Data> getData() {
        return data;
    }
}
