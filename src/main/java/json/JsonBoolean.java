package json;

/**
 * Created by Andrii_Rodionov on 1/4/2017.
 */
public class JsonBoolean extends Json {

    private final boolean statement;

    public JsonBoolean(Boolean bool) {
        this.statement = bool;
    }

    @Override
    public String toJson() {
        if (statement) {
            return "true";
        } else {
            return "false";
        }
    }
}
