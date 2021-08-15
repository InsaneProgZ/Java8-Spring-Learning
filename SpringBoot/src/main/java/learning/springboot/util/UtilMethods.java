package learning.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import learning.springboot.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtilMethods {

    @Autowired
    ObjectMapper objectMapper;

    public UserData applyPatchToCustomer(JsonPatch patch, UserData userData) throws JsonPatchException, JsonProcessingException {

        JsonNode patched = patch.apply(objectMapper.convertValue(userData, JsonNode.class));

        return objectMapper.treeToValue(patched, UserData.class);
    }
}
