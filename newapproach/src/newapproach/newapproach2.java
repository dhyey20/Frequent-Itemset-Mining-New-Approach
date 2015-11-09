/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newapproach;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
import static newapproach.Newapproach.convert;

/**
 *
 * @author dhyey
 */
class Newapproach2 extends DefaultMutableTreeNode{
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        int size=20;                           //global parameter
        File fil= new File("data1.txt");    //transaction file
        int support=3,tsupport=3;                          //support in perc
        String line,c;                            //each line (transactions) in data
        String[] s;                             //strign per line seperate by space
        int[] in= new int[size];                //convert string to integer containing array per line
        int[][] mat = new int[size][size] ;     // global matrix
        int[] count=new int[size];
        int i,j,k=0,m=0,counter,validcounter=0;                //count[i]ers
        int con[],temp,l=0;
         String l1,l2;        
         int ct=0,et=0,tt=0,a=0,x=0,y=0,xyz;
        /*these are count[i]er for no of row and column that 
         * contains transactions
         */
        
        
        /*//this loops are to clear global matrix
        for(i=0;i<size;i++)
        {
            for(j=0;j<size;j++)
            {
                mat[i][j]=0;
            }
        }
        */
        i=0;
        
        BufferedReader data_in = new BufferedReader(new FileReader(fil));
        /*
         * following while loop is to get data from file
         * of transaction and reading it into matrix 
         * "mat[][]"(global matrix)
         */
        while(data_in.ready())
                {
                    
                    line = data_in.readLine();
                    tt++;
                    s=line.split(" ");
                    for(j=0;j<s.length;j++)
                    {
                        c = convert(s[j]);
                        in[j]=Integer.parseInt(c);
                        
                        if(k==0)
                        {
                             mat[k][0]=in[j];
                             mat[k][i+1]=1;
                        }
                        else
                        {
                            for(m=0;m<k;m++)
                            {
                                if(in[j]==mat[m][0])
                                {
                                    mat[m][i+1]=1;
                                    count[i]=1;
                                    break;

                                }

                            }
                        }
                        if(count[i]==0)
                        {
                            mat[k][0]=in[j];
                            mat[k][i+1]=1;
                            k++;
                        }
                        
                        count[i]=0;
                        in[j]=0;
                        
                        
                    }
                    i++;
                                   
                                       
                  
                }      //while ends
       
        tt++;//tt is similar to et
        /*
         * this following loops are to print global matrix
         */
        for(i=0;i<size;i++)
        {
      
            count[i]=0;
            for(j=0;j<size;j++)
            {
                if(j>0) // count[i]s number of items  
                {
                    count[i]=count[i]+mat[i][j];
                    if(mat[i][j]==1)
                    {
                        if(j>et)
                        {
                            et=j;
                        }
                    }
                }
                
                
                System.out.print(mat[i][j] + " ");
            }
            if(count[i]>0)
                {
                    ct++;  // ct - no of itemsets
                }
            System.out.print(count[i]);
            System.out.println();
        }
       et++; 
       System.out.println("First time ct, et "+ct+" "+et);
        tsupport= support;
        k=0;
       xyz=ct;
        for(i=0;i<xyz;i++)
       {
           if(count[i]<tsupport)
           {
               /* for(j=0;j<et;j++)
                {
                    if(mat[i][j]==0)
                    {    k++;}
                    
                }
                if(k<et)
                    { 
                        ct--;//changed from ct-- to et-- 
                    }
                k=0;
                 */
               ct--;
           }
       }
        tt=et;
    
       /* it is due to we want to print the itemset id on first row and 
        * in previous matrix print we are count[i][i]ing et after second row        
        */
         int[][] mats = new int[xyz][tt+1/*et*/] ;//changed ct to xyz
        
         System.out.println();
         System.out.println();
          System.out.println("the ct is " + ct);
           System.out.println("the et is " + et);
            System.out.println();
             System.out.println();
              System.out.println("final matrix");
            
              
            con=new int[xyz];
            for(i=0;i<xyz;i++)
                con[i]=0;
            
           
         //now the final matrix     
         for(i=0;i<xyz;i++)
        {
            //count[i]=0;
              
            
            for(j=0;j<tt/*et*/;j++)
            {
                mats[i][j]=mat[i][j];
                if(j>0)
                {
                    con[i]=con[i]+mat[i][j];
                }
                              
                System.out.print(mats[i][j] + " ");               
                
            }
            System.out.print(con[i]);
            System.out.println();
             
        }
         
         /*
          * this logic is to sort the matrix in ascending order and 
          * pruning
          */
         for(i=0;i<xyz-1;i++)//changed ct to xyz
         {
             for(j=i+1;j<xyz;j++)
             {
                 if(con[i]<con[j])
                 {
                        temp=con[i];
                        con[i]=con[j];
                        con[j]=temp;
                     for(int n=0;n<tt;n++)
                     {
                        temp=mats[i][n];
                        mats[i][n]=mats[j][n];
                        mats[j][n]=temp;
                     }
                 }
             }
         }
         
         // ct and tt to be used in loops 
         
         System.out.println();
         System.out.println();
          
            System.out.println();
             System.out.println();
              System.out.println("final sorted matrix");
         
              
              System.out.println("tsupport="+tsupport);
         for(i=0;i<ct;i++)
        {
            //count[i]=0;
            
         
            for(j=0;j<tt/*et*/;j++)
            {
                /*mats[i][j]=mat[i][j];
                if(j>0)
                {
                    con[i]=con[i]+mats[i][j];
                }
                 */            
                System.out.print(mats[i][j] + " ");               
                
            }
            System.out.print(con[i]);
            System.out.println();
        }

       
         /////////////////////////////////////////////////////////////////////////////////////
         
         ArrayList <group> groupsarr= new ArrayList();
         String grptrace = " ";
         for(i=1;i<tt;i++)
         {
             int flag=0;
             for(j=0;j<ct;j++)
             {
                 if(j==0)
                 {
                     if(mats[j][i]==1)
                     {
                          grptrace=grptrace+' '+mats[j][0];
                     }
                     else
                     {
                         grptrace=" ";
                     }
                 }
                 
                 
                 
                 else if(mats[j][i]==1)
                 {
                    grptrace=grptrace+' '+mats[j][0];
                 }
                 
                 else if(mats[j][i]==0 && flag==0)
                 {
                           flag=1;   
                        group g=new group();
                        g.name=grptrace;
                        g.name.trim();
                        g.colname=i;
                        g.endsat=j-1;
                        groupsarr.add(g);
                        grptrace=" ";
                        
                 }
                 if(j==ct-1)
                 {
                     if(mats[j][i]==1)
                     {
                        group g=new group();
                        g.name=grptrace;
                         g.colname=i;
                         g.name.trim();
                        g.endsat=j;
                         groupsarr.add(g);
                        grptrace=" ";
                     }
                 }
             }
         }
         group g1 = null;
         for(i=0;i<groupsarr.size();i++)
         {
             g1=groupsarr.get(i);
             g1.printgrp(g1);
         }
    
          String s1;
         for(i=0;i<groupsarr.size();i++)
         {
             g1=groupsarr.get(i);
             g1.parenttrace="0";
             s1=g1.name;
             char first=s1.charAt(2);    // always a 2
             int d=Character.getNumericValue(first);
             for(k=0;k<ct;k++)
             {
                 if(mats[k][0]==d)
                 {   d=k;
                     break;
                 }
             }
             System.out.println(d);
             
             int col=g1.colname;
             if(d!=0){
                 
             for(j=0;j<d;j++)
             {
              if(mats[j][col]==1)
              {
                  g1.parenttrace=g1.parenttrace+" "+mats[j][0];
                  g1.parent=g1.parent+" "+mats[j][0];
              }
             }
             }
             else
             {
                 g1.parenttrace="0";
             }
             //g1.parenttrace;
             
             
         }
  
         for(i=0;i<groupsarr.size();i++)
         {
              g1=groupsarr.get(i);
              g1.printtrace(g1);
         }
    
         group g2=new group();
         for(i=0;i<groupsarr.size()-1;i++)
         {
              g1=groupsarr.get(i);
              for(j=i+1;j<groupsarr.size();j++)
              {
                  g2=groupsarr.get(j);
                  if(g1.name.equals(g2.name))
                  {
                      if(g1.parenttrace.equals(g2.parenttrace))
                      {
                          if(g2.count!=0)
                          {
                              
                         
                          g1.valid=1;
                          g1.count++;
                          g2.count=0;
                          g2.valid=0;
                          }
                      }
                  }
                  else 
                  {
                      if(g1.valid==-1)
                      {
                          g1.valid=1;
                      }
                  }
                  
              }
              
         }
         group tmp;
         /*for(i=0;i<groupsarr.size()-1;i++)
         {
             g1=groupsarr.get(i);
             for(j=i;j<groupsarr.size();j++)
             {
                 g2=groupsarr.get(j);
                 if(g1.endsat>g2.endsat)
                 {
                     tmp=
                     
                 }
             }
         }*/
         for(i=0;i<groupsarr.size();i++)
         {
              g1=groupsarr.get(i);
              g1.printvalid(g1);
         }
    
         ArrayList<ItemsetTree> nodearr=new ArrayList();
         
         //ItemsetTree n1=new ItemsetTree();
         //endswith
         for(j=0;j<ct;j++)
         {
         for(i=0;i<groupsarr.size();i++)
         {
              g1=groupsarr.get(i);
              if(g1.endsat==j)
              {
                  
              if(g1.valid==1)
              {
                  //make node
                 
                  ItemsetTree n1=new ItemsetTree();
                  n1.count=g1.count;
                  n1.parenttrace=g1.parenttrace;
                  n1.nodename=g1.name;
                  n1.parent=g1.parent;
                  nodearr.add(n1);
                  n1.todoString1(n1);
                  
                }
              
                }
         }
         }
         System.out.println(".............................................");
         ItemsetTree n1=new ItemsetTree();
         
         for(i=0;i<nodearr.size();i++)
         {
             n1=nodearr.get(i);
             n1.nodename.trim();
             n1.todoString1(n1);
             
             
         }
         System.out.println("///////////////////////////////////////");
         
         
         ItemsetTree root=new ItemsetTree();
         root.nodename="root";
         root.parenttrace="";
         
         root.add(nodearr.get(0));
         
          
         for(i=1;i<nodearr.size();i++)
         {
             ItemsetTree n2=new ItemsetTree();
             n2=nodearr.get(i);
                Enumeration e=root.postorderEnumeration();
                System.out.println();    
                while(e.hasMoreElements())
                    {
                         ItemsetTree ist=(ItemsetTree)e.nextElement();
                         
                         ist.todoString1(ist);
                         String s3=" "+ist.nodename;
                         if(ist.parenttrace.equals(n2.parenttrace))
                         {
                             if(n2.nodename.contains(ist.nodename))
                             {
                                 ist.add(n2);
                                 break;
                             }
                         }
                         
                         else if(s3.equals(n2.parent))
                         {
                             ist.add(n2);
                             break;
                         }
                  
             }
             
                
         }
         
         System.out.println("Final:");
    Enumeration e=root.postorderEnumeration();
    while(e.hasMoreElements())
                    {
                        ItemsetTree ist=(ItemsetTree)e.nextElement();
                        ist.todoString1(ist);
                        ist.tempcount=ist.count;
                    }
         
    String ab;
    e=root.postorderEnumeration();
    while(e.hasMoreElements())
                    {
                        ItemsetTree ist=(ItemsetTree)e.nextElement();
                        Enumeration e1=ist.children();
                        while(e1.hasMoreElements())
                        {
                            ItemsetTree ist1=(ItemsetTree)e1.nextElement();
                            //ab=ist1.nodename;
                            if(ist1.nodename.contains(ist.nodename))
                            {
                                ist.tempcount+=ist1.tempcount;
                            }
                            //if(ist1.nodename.contains())
                        }
                        
                    }
    System.out.println();
    System.out.println();
    System.out.println("Answer");
     e=root.postorderEnumeration();
    while(e.hasMoreElements())
                    {
                        ItemsetTree ist=(ItemsetTree)e.nextElement();
                        //ist.todoString2(ist);
                        if(ist.tempcount>=support)
                        {
                            System.out.println("node:---");
                            ist.todoString2(ist);
                                    
                                    
                        }
                        
                        
                    }
    
    //ArrayList <Integer>abc=new ArrayList();
    
    
    
}
     public static String convert(String atr)
    {
        int count=0;
        char c=atr.charAt(0);
        String s="";
        char[] ls = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for(int i=0;i<26;i++)
        {
            if(ls[i]==c)
            {
                 s= String.valueOf(i+1);
                count=1;
                break;
                
            }
            
        }
        if(count==1)
            return s;
        else
            return atr;
         
    }
}