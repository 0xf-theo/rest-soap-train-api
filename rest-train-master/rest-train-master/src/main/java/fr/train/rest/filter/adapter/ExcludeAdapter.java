package fr.train.rest.filter.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ExcludeAdapter implements JsonSerializer {
    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }
}
