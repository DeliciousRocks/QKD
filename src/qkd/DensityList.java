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
public class DensityList {
    private String name;
    private Alice alice;
    private Bob bob;
    private Eve eve;
    private boolean isPrimary;
    
    //for new
    public DensityList()
    {
        name = "Default";
        alice = new Alice();
        bob = new Bob();
        eve = new Eve();
        isPrimary = true;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String temp)
    {
        name = temp;
    }
    
    public Alice getAlice()
    {
        return alice;
    }
}
