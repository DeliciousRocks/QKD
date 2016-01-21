/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qkd;

import java.util.ArrayList;

/**
 *
 * @author waltersquires
 */
public class DensityList {
    private ArrayList<Wire> connections;
    private String name;
    private Alice alice;
    private Bob bob;
    private Eve eve;
    private boolean isPrimary;
    
    //for new
    public DensityList()
    {
        connections = new ArrayList();

        
        name = "Default";
        alice = new Alice();
        bob = new Bob();
        eve = new Eve();
        isPrimary = true;
    }
    
      public DensityList(ArrayList<Wire> wires)
    {
        connections = wires;

        
        name = "Default";
        alice = new Alice();
        bob = new Bob();
        eve = new Eve();
        isPrimary = true;
    }
    
      public boolean addConnection(Wire newConnection)
    {
        return connections.add(newConnection);
    }
    
    public boolean removeConnection(Wire removeConnection)
    {
        return connections.remove(removeConnection);
    }
    
    public Wire getWire(String wireName)
    {
        for(Wire search: connections)
        {
            if(search.getName().equals(wireName))
                return search;
        }
        return null;
    }
    
    public void setProb(String wireName,int index,float newVal)
    {
         for(Wire search: connections)
        {
            if(search.getName().equals(wireName))
                search.setProbability(index,newVal);
        }
    }
    
    public ArrayList<Wire> getWires()
    {
        return connections;
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
