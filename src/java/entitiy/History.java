/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiy;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Вдуник
 */
@Entity
public class History implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Customer customer;
    private KitchenProduct kitchenProduct;
    private Date boughtDate;

    public History() {
    }

    public History(Customer buyeer, KitchenProduct kitchenProduct, Date boughtDate) {
        this.customer = customer;
        this.kitchenProduct = kitchenProduct;
        this.boughtDate = boughtDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setBuyeer(Customer customer) {
        this.customer = customer;
    }

    public KitchenProduct getKitchenProduct() {
        return kitchenProduct;
    }

    public void setKitchenProduct(KitchenProduct kitchenProduct) {
        this.kitchenProduct = kitchenProduct;
    }

    public Date getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(Date boughtDate) {
        this.boughtDate = boughtDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "History{" + "customer=" 
                + customer + ", kitchen=" 
                + kitchenProduct + ", boughtDate=" 
                + boughtDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.customer);
        hash = 47 * hash + Objects.hashCode(this.kitchenProduct);
        hash = 47 * hash + Objects.hashCode(this.boughtDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.kitchenProduct, other.kitchenProduct)) {
            return false;
        }
        if (!Objects.equals(this.boughtDate, other.boughtDate)) {
            return false;
        }
        return true;
    }  
}