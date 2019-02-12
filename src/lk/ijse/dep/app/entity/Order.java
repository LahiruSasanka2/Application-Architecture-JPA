package lk.ijse.dep.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
public class Order extends SuperEntity{

    @Id
    private String id;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customerId;

    public Order() {

    }

    public Order(String id, Date date, Customer customerId) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
