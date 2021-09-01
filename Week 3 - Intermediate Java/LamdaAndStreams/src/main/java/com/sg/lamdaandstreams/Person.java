/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.lamdaandstreams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Paul
 */
public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public List<Person> filterAge(List<Person> personList) {
        List<Person> filteredList = new ArrayList<>();
        filteredList = personList.stream()
                .filter((p) -> p.getAge() >= 18)
                .collect(Collectors.toList());
        return filteredList;
    }
    
    public Map<Integer, List<Person>> peopleAges(List<Person> allPeople) {
        Map<Integer, List<Person>> peopleByAges = new HashMap<>();
        peopleByAges = allPeople.stream()
                .collect(Collectors.groupingBy((p) -> p.getAge()));
        return peopleByAges;
    }
}
