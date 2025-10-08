package risk.decision.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.InputStream;
import java.util.Set;

public class JsonValidator {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
    private static JsonSchema schema;

    static {
        try (InputStream schemaStream = JsonValidator.class.getResourceAsStream("/schemas/transaction-schema.json")) {
            schema = schemaFactory.getSchema(schemaStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON schema", e);
        }
    }

    public static boolean validateTransaction(String jsonString) {
        try {
            JsonNode jsonNode = mapper.readTree(jsonString);
            Set<ValidationMessage> errors = schema.validate(jsonNode);
            return errors.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
