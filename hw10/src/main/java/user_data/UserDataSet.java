package user_data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PhoneDataSet> phones = new ArrayList();

    public UserDataSet() {

    }

    public UserDataSet(String name, int age, AddressDataSet address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void addPhone(PhoneDataSet phone) {
        this.phones.add(phone);
        phone.setUser(this);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", phones=" + phones +
                '}';
    }
}
