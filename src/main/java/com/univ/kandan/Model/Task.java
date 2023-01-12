package com.univ.kandan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Task implements Comparable<Task>{
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @Column(name = "nom")
    public String nom;

    @Column(name = "description", nullable = true)
    public String descirption;

    @Column(name = "order")
    public Integer order;

    @ManyToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private com.univ.kandan.model.Column column;

    @ManyToOne(optional = true)
    @Fetch(FetchMode.JOIN)
    private User user;

    public int compareTo(Task task) {
        return this.order.compareTo(task.order);
    }
    tfyfyhuv5ygihugu
}
