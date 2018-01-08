package user_data;

public class UserDataSet extends DataSet {
    private String name;
    private int age;

    public UserDataSet() {

    }

    public UserDataSet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("UserDataSet{name = '%s', age = %s}", name, age);
    }
}
