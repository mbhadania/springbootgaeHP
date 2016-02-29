/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.controller;
import java.util.ArrayList;

/**
 *
 * @author Miten Bhadania
 */

public class OutputService {

    private ArrayList<String> combination;
    private int totalCombos;
    private int totalPages;

    public OutputService(ArrayList<String> combination, int totalCombos, int totalPages) {
        this.combination = combination;
        this.totalCombos = totalCombos;
        this.totalPages = totalPages;
    }

    public ArrayList<String> getCombination() {
        return combination;
    }

    public int getTotalCombos() {
        return totalCombos;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public String toString() {
        return "OutputService{" + "combination=" + combination + ", totalCombos=" + totalCombos + ", totalPages=" + totalPages + '}';
    } 
}
