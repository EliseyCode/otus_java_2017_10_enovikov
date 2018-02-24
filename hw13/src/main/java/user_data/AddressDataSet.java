package user_data;

import user_data.DataSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressDataSet extends DataSet {

    @Column(name = "address")
    private String address;

    public AddressDataSet() {

    }

    public AddressDataSet(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "user_data.AddressDataSet{" +
                "address='" + address + '\'' +
                '}';
    }
}
