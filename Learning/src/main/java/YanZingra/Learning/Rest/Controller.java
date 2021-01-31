package YanZingra.Learning.Rest;

import YanZingra.Learning.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController("/agenda")
public class Controller {

    @GetMapping("/show")
    public List<Data> getResult (@RequestBody Data data)
    {
        List<Data> dataList = Arrays.asList(new Data("Yan contructor" ,"Software Engineer",25),data,data);

        return dataList;

    }
}
