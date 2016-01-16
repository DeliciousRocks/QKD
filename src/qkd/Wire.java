/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qkd;

import java.util.Arrays;

/**
 *
 * @author waltersquires
 */
public class Wire {
    private String name;
    private int dimension;
    float[] probability;
    
    public Wire()
    {
        name = "Default";
        dimension = 0;
        probability = new float[dimension];
        Arrays.fill(probability,-1);
    }
    
    public Wire(String temp, int d)
    {
        name = temp;
        dimension = d;
        probability = new float[dimension];
        Arrays.fill(probability,-1);    }
    
    public int getDimensions()
    {
        return dimension;
    }
    
    public String getName()
    {
        return name;  
    }
    
    public void setProbability(int index,float newOdds)
    {
        probability[index] = newOdds;
    }
    
    public float getProbability(int index)
    {
        return probability[index];
    }
}
