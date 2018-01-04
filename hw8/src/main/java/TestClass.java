import java.util.ArrayList;
import java.util.List;

public class TestClass {

    private String name = "Elisey";
    private String lastName = "Novikov";
    private Integer testInteger = 10;
    private Character testCharacter = 'C';
    private char testChar = 'c';
    private Double testDouble = 12.4;
    List<String> qualifications;
    private int age = 32;
    Boolean clever = true;
    int[] array = {1, 5, 3};

    public TestClass() {
        qualifications = new ArrayList();
        qualifications.add("JavaSE");
        qualifications.add("MySQL");
    }
}
