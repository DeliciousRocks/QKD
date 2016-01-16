/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qkd;

/**
 *
 * @author waltersquires
 */
public class Wire {
    private String name;
    float probability;
    
    public Wire()
    {
        name = "Default";
        probability = -1;
    }
    
    public Wire(String temp)
    {
        name = temp;
        probability = -1;
    }
    
    public String getName()
    {
        return name;  
    }
    
    public void setProbability(float newOdds)
    {
        probability = newOdds;
    }
    
    public float getProbability()
    {
        return probability;
    }
}
