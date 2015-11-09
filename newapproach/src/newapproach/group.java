/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newapproach;
import java.util.*;

/**
 *
 * @author dhyey
 */
public class group {
    String name;
    int count=1;
    String parent="  ";
    String lastparent;
    int colname;
    String parenttrace;
    int valid=-1;
    int endsat;
    ArrayList <Integer> groups;
    
    
     public void printgrp(group g)
    {
        System.out.println("Group name: "+g.name+"   Colname: "+g.colname);
   
    }
     public void printtrace(group g)
    {
        System.out.println("Group name: "+g.name+"   Colname: "+g.colname+"  Parenttrace: "+g.parenttrace+"  Parent:"+g.parent);
   
    }
      public void printvalid(group g)
    {
        System.out.println("Group name: "+g.name+"   Colname: "+g.colname+"  Parenttrace: "+g.parenttrace+"  Valid: "+g.valid+"  Count:"+g.count+"   End:"+g.endsat+"  Lastparent:"+g.lastparent);
   
    }
    
}
