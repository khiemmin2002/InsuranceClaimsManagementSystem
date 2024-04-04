package models;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;


    public PolicyHolder() {
        super();
        dependents = new ArrayList<Dependent>();
    }

    public PolicyHolder(String fullName) {
        super(fullName);
        dependents = new ArrayList<Dependent>();
    }

    public PolicyHolder(String fullName, InsuranceCard insuranceCard) {
        super(fullName, insuranceCard);
        dependents = new ArrayList<Dependent>();
    }

    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard) {
        super(id, fullName, insuranceCard);
        dependents = new ArrayList<Dependent>();
    }

    public PolicyHolder(String id, String fullName) {
        super(id, fullName);
        dependents = new ArrayList<Dependent>();
    }

    public void removeDependent(Dependent dependent) {
        this.dependents.removeIf(d -> d.getId().equals(dependent.getId()));
    }
}
