import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonWriter {

    private final static String OBJECT = "Object";

    public String serializeJSON(Object jsonObject) throws IllegalAccessException {

        StringBuilder sb = new StringBuilder();
        if (jsonObject == null) {
            return sb.append("").toString();
        } else if (serializeFieldValue(jsonObject).equals(OBJECT)) {
            sb.append("{");
            Field[] fields = jsonObject.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (sb.toString().endsWith("}")) {
                    sb.append(",\"").append(field.getName()).append("\":");
                } else {
                    sb.append("\"").append(field.getName()).append("\":");
                }
                sb.append(serializeJSON(field.get(jsonObject)));
            }

            if (sb.toString().endsWith(",")) {
                sb.deleteCharAt(sb.length() - 1).append("}");
            } else sb.append("}");
        } else sb.append(serializeFieldValue(jsonObject));

        if (sb.toString().contains(",]")) {
            sb.deleteCharAt(sb.length() - 3);
        }
        return sb.toString();
    }

    private String serializeFieldValue(Object o) throws IllegalAccessException {
        Class clazz = o.getClass();
        if (clazz.equals(String.class) || clazz.equals(char.class) || clazz.equals(Character.class))
            return serializeString(o);
        if (clazz.isPrimitive() || Number.class.isInstance(o) || Boolean.class.isInstance(o))
            return serializeNumberOrBoolean(o);
        if (clazz.isArray() || Collection.class.isInstance(o))
            return serializeCollection(o);
        else return OBJECT;
    }

    private String serializeString(Object jsonObject) throws IllegalAccessException {
        return "\"" + jsonObject.toString() + "\",";
    }

    private String serializeNumberOrBoolean(Object jsonObject) throws IllegalAccessException {
        return jsonObject.toString() + ",";
    }

    private String serializeCollection(Object jsonObject) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        List<Object> list = new ArrayList();

        if (Collection.class.isInstance(jsonObject)) {
            list = (List) jsonObject;
        } else {
            for (int i = 0; i < Array.getLength(jsonObject); i++) {
                list.add(Array.get(jsonObject, i));
            }
        }

        for (Object element : list) {
            sb.append(serializeFieldValue(element));
        }
        sb.append("],");
        return sb.toString();
    }
}
