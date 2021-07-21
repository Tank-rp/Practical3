package sg.edu.rp.c346.id20022678.practical3;

import androidx.annotation.NonNull;

import java.io.Serializable;

/*
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * Student Name: Tan Ke Ting
 * Student ID: 200226678
 */

public class Client implements Serializable {

    private int id;
    private String name;
    private int salespotential;

    public Client(int id, String name, int salespotential) {
        this.id = id;
        this.name = name;
        this.salespotential = salespotential;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalespotential() {
        return salespotential;
    }

    public void setSalespotential(int salespotential) {
        this.salespotential = salespotential;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " (ID:" + id + ") $" + salespotential;
    }
}
