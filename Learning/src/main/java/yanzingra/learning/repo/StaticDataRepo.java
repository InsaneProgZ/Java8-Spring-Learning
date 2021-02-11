package yanzingra.learning.repo;

import yanzingra.learning.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StaticDataRepo {

    List<Data> data;

    public StaticDataRepo() {

        this.data = new ArrayList<>();
    }

    public void save(List<Data> data) {

        this.data.addAll(data);
    }

    public List<Data> delete(List<Data> data) {

        List<Data> toDeleteData =this.data.stream()
                .filter(f -> data.stream()
                        .map(Data::getName)
                        .collect(Collectors.toList()).contains(f.getName()))
                .collect(Collectors.toList());
        this.data.removeAll(toDeleteData);

        return data;

    }

    public List<Data> getData() {
        return data;
    }
}
