package user_data;

import user_data.AddressDataSet;
import user_data.DataSet;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    public UserDataSet() {

    }

    public UserDataSet(String name, int age, AddressDataSet address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "user_data.UserDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
