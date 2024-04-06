package models;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * RMIT University Vietnam - Assignment 1
 * @author <Min Chi Gia Khiem - S3878280>
 * @version 1.0
 * @since 04/05/2024
 */

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    // Constructor used in PolicyHolderController when adding a new policyholder
    public PolicyHolder(String fullName) {
        super(fullName);
        dependents = new ArrayList<Dependent>();
    }

    // Constructor used in PolicyHolderController when parsing a line from the file
    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard) {
        super(id, fullName, insuranceCard);
        dependents = new ArrayList<Dependent>();
    }

    // Method to remove a dependent from the list of dependents
    public void removeDependent(Dependent dependent) {
        this.dependents.removeIf(d -> d.getId().equals(dependent.getId()));
    }
}
