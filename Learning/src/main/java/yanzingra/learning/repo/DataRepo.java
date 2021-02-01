package yanzingra.learning.repo;

import yanzingra.learning.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataRepo {

    List<Data> data;

    public DataRepo() {

        this.data = new ArrayList<>();
    }

    public void save(List<Data> data) {

        this.data.addAll(data);
    }

    public Boolean delete(List<Data> data) {

        this.data.stream().flatMap(names ->
                (data.stream().map(name -> name.name).collect(Collectors.toList())).stream().filter(filter ->
                        filter.equals(names.name))).iterator().remove();

        return true;

    }

    public List<Data> getData() {
        return data;
    }
}
