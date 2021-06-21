package Learning.springboot.util;

import Learning.springboot.model.UserData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtilMethods {

    @Autowired
    ObjectMapper objectMapper;

    public UserData applyPatchToCustomer(JsonPatch patch, UserData targetCustomer) throws JsonPatchException, JsonProcessingException {

        JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));

        return objectMapper.treeToValue(patched, UserData.class);
    }
}
