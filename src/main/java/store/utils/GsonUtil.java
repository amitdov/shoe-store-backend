package store.utils;

import com.google.gson.*;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Gson library related utility methods
 */
public class GsonUtil {

    private static Set<Class<?>> javaPreDefinedTypes = getJavaPreDefinedTypes();

    /**
     * like
     * `
     * return jsonObject[objectName] =  jsonObject[objectName] | {}
     * `
     * in javascript
     */
    public static JsonObject getOrCreateJsonObject(Map<Object, Object> jsonObject, String objectName) {
        JsonObject newObject;
        if (jsonObject.containsKey(objectName))
            newObject = (JsonObject) jsonObject.get(objectName);
        else {
            newObject = new JsonObject();
            jsonObject.put(objectName, newObject);
        }

        return newObject;
    }

    /**
     * insert map data to a POJO
     *
     * @param argument  - the data to insert into the POJO
     * @param pojoClass - the class of the pojo
     *                  if the argument is a list use new {@link com.google.gson.reflect.TypeToken}<List<POJO>>(){}.getType()
     */
    public static <T> T fromArgument(Object argument, Class<T> pojoClass) throws JsonSyntaxException {
        if (argument == null) return null;
        Object o = parseArgument(argument, pojoClass);
        if (o == null) return null;
        assertNotNulls(o);
        return (T) o;
    }

    public static <T> T fromArgument(Object argument, Type pojoType) {
        if (argument == null) return null;
        Object o = parseArgument(argument, pojoType);
        if (o == null) return null;
        assertNotNulls(o);
        return (T) o;
    }

    /**
     * insert map data to a POJO
     *
     * @param argument  - the data to insert into the POJO
     * @param pojoType- the type of the pojo
     *                  if the argument is a list use new {@link com.google.gson.reflect.TypeToken}<List<POJO>>(){}.getType()
     */
    @SuppressWarnings("unchecked")
    private static <T> T parseArgument(Object argument, Type pojoType) throws JsonSyntaxException {
        if (argument instanceof Map) {
            Gson gson = getGson();
            JsonElement mapInput = gson.toJsonTree(argument, Map.class);
            return gson.fromJson(mapInput, pojoType);
        } else if (argument instanceof List) {
            // if its a list, try and find out its generic type
            if (pojoType instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) pojoType;

                if (pType.getActualTypeArguments().length == 1) {
                    Type type = pType.getActualTypeArguments()[0];

                    // map the list to the type parameter
                    return (T) ((List<T>) argument).stream()
                            .map(o -> parseArgument(o, type))
                            .collect(Collectors.toList());
                }
            }
        }

        // when all else fails return as is
        return (T) argument;
    }

    private static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ZoneId.class, new ZoneIdInstanceCreator());
        return gsonBuilder.create();
    }

    private static void assertNotNulls(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        assertNotNullField(object, fields);
    }

    private static void assertNotNullField(Object object, Field[] fields) {
        for (Field field : fields) {
            if (!field.isSynthetic()) {
                Object fieldObject;
                try {
                    fieldObject = field.get(object);
                } catch (Exception e) {
                    continue;
                }
                if (field.isAnnotationPresent(NotNull.class) && fieldObject == null)
                    throw new IllegalNullValueException(field.getName());
                if (fieldIsNotPrimitiveAndNotEnum(fieldObject)) {
                    if (!fieldIsTheSameClassAsSuperClass(object, field)) {
                        assertNotNullField(fieldObject, field.getType().getDeclaredFields());
                    }
                }
            }
        }
    }

    private static Set<Class<?>> getJavaPreDefinedTypes() {
        Set<Class<?>> primitivesTypes = new HashSet<>();
        primitivesTypes.add(Boolean.class);
        primitivesTypes.add(Character.class);
        primitivesTypes.add(String.class);
        primitivesTypes.add(Byte.class);
        primitivesTypes.add(Short.class);
        primitivesTypes.add(Integer.class);
        primitivesTypes.add(Long.class);
        primitivesTypes.add(Float.class);
        primitivesTypes.add(Double.class);

        return primitivesTypes;
    }

    private static Boolean fieldIsNotPrimitiveAndNotEnum(Object fieldObject) {
        return fieldObject != null && !javaPreDefinedTypes.contains(fieldObject.getClass()) && !(fieldObject instanceof Enum);
    }

    private static Boolean fieldIsTheSameClassAsSuperClass(Object superclass, Field field) {
        return field.getType().equals(superclass.getClass());
    }

    public static class IllegalNullValueException extends RuntimeException {

        private static final String exceptionMessage = "The field %s is annotated with a 'NotNull' constraint,but was received as null";

        public IllegalNullValueException(String fieldName) {
            super(String.format(exceptionMessage, fieldName));

        }
    }

    public static class ZoneIdInstanceCreator implements InstanceCreator<ZoneId> {
        public ZoneId createInstance(Type type) {
            return (ZoneId.systemDefault());
        }
    }
}