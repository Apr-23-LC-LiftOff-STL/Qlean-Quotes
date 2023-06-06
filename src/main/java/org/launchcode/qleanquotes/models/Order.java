package org.launchcode.qleanquotes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Or;

@Entity
public class Order extends AbstractEntity{

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @OneToOne(mappedBy = "order")
    private Quote quote;

    @ManyToOne
    private Customer customer;

    public Order(){
    }

//    public Order(Payment aPayment, Quote aQuote, Customer aCustomer){
//        this.payment = aPayment;
//        this.quote = aQuote;
//        this.customer = aCustomer;
//    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
