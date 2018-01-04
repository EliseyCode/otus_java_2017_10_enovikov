import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        JsonWriter json = new JsonWriter();
        TestClass testClass = new TestClass();

        String myJson = json.serializeJSON(testClass);

        System.out.println(myJson);

        Gson gson = new Gson();
        String toGson = gson.toJson(testClass);

        System.out.println(toGson);

        System.out.println("" + myJson.equals(toGson));
    }
}
