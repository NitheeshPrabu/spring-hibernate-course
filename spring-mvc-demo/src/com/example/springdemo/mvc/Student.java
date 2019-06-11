package com.example.springdemo.mvc;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;

public class Student {

    private String firstName;

    @NotNull()
    private String lastName;
    private String country;
    private String language;
    private String[] operatingSystems;

    private LinkedHashMap<String, String> countries;

    public Student() {

        // populate country options
        countries = new LinkedHashMap<>();
        countries.put("BR", "Brazil");
        countries.put("FR", "France");
        countries.put("DE", "Germany");
        countries.put("IN", "India");
        countries.put("US", "United States of America");
    }

    public LinkedHashMap<String, String> getCountries() {
        return countries;
    }

    public String[] getOperatingSystems() {
        return operatingSystems;
    }

    public void setOperatingSystems(String[] operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
