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
public class Alice {
    ArrayList<Wire> connections;
    
    public Alice()
    {
        connections = new ArrayList();
        connections.add(new Wire("Fish"));
        connections.add(new Wire("Sandwitch"));

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
    
    public void setProb(String wireName,float newVal)
    {
         for(Wire search: connections)
        {
            if(search.getName().equals(wireName))
                search.setProbability(newVal);
        }
    }
    
    public ArrayList<Wire> getWires()
    {
        return connections;
    }
}
