package com.example;

/**
 * Created by Robert on 03/04/2017.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * An entity User composed by two fields (id, name, pass).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 *
 * @author robert
 */
@Entity
@Table(name = "users")
public class User {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    // An autogenerated id (unique for each user in the db)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // The user's pass
    @NotNull
    private String pass;

    // The user's name
    @NotNull
    private String name;

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    public User() { }

    public User(long id) {
        this.id = id;
    }

    public User(String pass, String name) {
        this.pass = pass;
        this.name = name;
    }

    // Getter and setter methods

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String value) {
        this.pass = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

} // class User