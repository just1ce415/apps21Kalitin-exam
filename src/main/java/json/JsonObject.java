package json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    Map<String, Json> jsonMap = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair: jsonPairs) {
            String name = pair.key;
            Json jsonVal = pair.value;
            jsonMap.put(name, jsonVal);
        }
    }

    @Override
    public String toJson() {
        return "{" + getJsonMapBody() + "}";
    }

    private String getJsonMapBody() {
        StringBuilder jsonStr = new StringBuilder();
        Iterator<Map.Entry<String, Json>> iterator = jsonMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Json> tuple = iterator.next();
            String name = tuple.getKey();
            Json jsonVal = tuple.getValue();
            jsonStr.append(name).append(": ").append(jsonVal.toJson());
            if (iterator.hasNext()) {
                jsonStr.append(", ");
            }
        }
        return jsonStr.toString();
    }

    public void add(JsonPair jsonPair) {
        this.jsonMap.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return jsonMap.get(name);
    }

    public boolean contains(String name) {
        return this.jsonMap.get(name) != null;
    }

    public JsonObject projection(String... names) {
        List<JsonPair> newPairs = new ArrayList<>();
        for (String name: names) {
            Json value = this.jsonMap.get(name);
            if (value != null) {
                newPairs.add(new JsonPair(name, value));
            }
        }
        JsonPair[] newArr = new JsonPair[newPairs.size()];
        newArr = newPairs.toArray(newArr);
        return new JsonObject(newArr);
    }
}
