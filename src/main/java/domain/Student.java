package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private final String name;
    private final String surname;
    private final Integer year;
    private final JsonObject[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.exams = new JsonObject[exams.length];
        int i = 0;
        for (Tuple<String, Integer> tuple: exams) {
            String subject = tuple.key;
            Integer mark = tuple.value;
            boolean passed = mark >= 3;
            this.exams[i] = new JsonObject(new JsonPair("course", new JsonString(subject)),
                    new JsonPair("mark", new JsonNumber(mark)),
                    new JsonPair("passed", new JsonBoolean(passed)));
            i++;
        }
    }

    @Override
    public JsonObject toJsonObject() {
        return new JsonObject(new JsonPair("name", new JsonString(name)),
                new JsonPair("surname", new JsonString(surname)),
                new JsonPair("year", new JsonNumber(year)),
                new JsonPair("exams", new JsonArray(exams)));
    }
}