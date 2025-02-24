package com.univ.kandan.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Kanban implements Comparable<Kanban> {

  public Kanban() {
    members = new TreeSet<User>();
  }

  @Id
  @GeneratedValue
  @Column(name = "Id")
  private Long id;

  @Column(name = "Nom")
  private String nom;

  @Column(name = "Description")
  private String description;

  @Column(name = "isPublic")
  private boolean isPublic;

  @ManyToOne(optional = false)
  @Fetch(FetchMode.JOIN)
  private User creator;

  @ManyToMany(mappedBy = "kanban")
  private Set<User> members;

  @OneToMany(mappedBy = "kanban", orphanRemoval = true)
  private Set<com.univ.kandan.model.Column> columns;

  public Long getId() {
    return id;
  }

  public String getNom() {
    return nom;
  }

  public String getDescription() {
    return description;
  }

  public boolean getIsPublic() {
    return isPublic;
  }

  public User getCreator() {
    return creator;
  }

  public Set<User> getMembers() {
    return members;
  }

  public Set<com.univ.kandan.model.Column> getColumns() {
    return columns;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setIsPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  public void setCreator(User user) {
    this.creator = user;
  }

  public void setMembers(Set<User> users) {
    this.members = users;
  }

  public void addMember(User user) {
    this.members.add(user);
  }

  public void removeMember(User user) {
    this.members.remove(user);
  }

  public void setColumns(Set<com.univ.kandan.model.Column> columns) {
    this.columns = columns;
  }

  public void addColumns(com.univ.kandan.model.Column column) {
    this.columns.add(column);
  }

  public void removeColumns(com.univ.kandan.model.Column column) {
    this.columns.remove(column);
  }

  public int compareTo(Kanban kanban) {
    return this.id.compareTo(kanban.id);
  }

}
