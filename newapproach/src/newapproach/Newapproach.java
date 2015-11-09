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

/**
 *
 * @author dhyey
 */
class Newapproach extends DefaultMutableTreeNode{
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        int size=20;                           //global parameter
        File fil= new File("data1.txt");    //transaction file
        int support=2,tsupport=2;                          //support in perc
        String line,c;                            //each line (transactions) in data
        String[] s;                             //strign per line seperate by space
        int[] in= new int[size];                //convert string to integer containing array per line
        int[][] mat = new int[size][size] ;     // global matrix
        int[] count=new int[size];
        int i,j,k=0,m=0,counter = 0,validcounter=0;                //count[i]ers
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
            for(j=0;j<size/*a*/;j++)
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
            
         
            for(j=0;j<tt/*et*/;j++)
            {            
                System.out.print(mats[i][j] + " ");               
                
            }
            System.out.print(con[i]);
            System.out.println();
        }

       
         /////////////////////////////////////////////////////////////////////////////////////
         int flag;
         ArrayList <group> groupsarr= new ArrayList();
         String grptrace="";
         /// added on feb 2
         int flag2=0;
        ////////////////////////// 
         for(i=1;i<tt;i++)
         {
             flag=0;
             //flag2=0;
             for(j=0;j<ct;j++)
             {
                 
                 if(j==0)
                 {
                     if(mats[j][i]==1)//start new grp frm first row
                     {
                         grptrace=String.valueOf(mats[j][0]);
                         flag=1;
                         //flag2++;
                         continue;
                     }
                  }
                 //or else when first 1 is encountered new group
                  
                 else
                 {
                 
                    if(mats[j][i]==1 && mats[j-1][i]==0/*flag==0*/)  
                     {
                         grptrace=String.valueOf(mats[j][0]);
                         
                         if(j==ct-1)
                         {
                             group g=new group();
                                g.name=String.valueOf(mats[j][0]);
                                g.colname=i;
                                g.endsat=j;
                                groupsarr.add(g);
                                grptrace="";
                         }
                         
                         flag=1;
                         //flag2++;
                         continue;
                     }
                     //or else 2nd 1 in the sequence
                     if(mats[j][i]==1 && mats[j-1][i]==1/*flag==1*/)
                     {
                         grptrace=grptrace+" "+String.valueOf(mats[j][0]);
                         
                         if(j==ct-1)
                         {
                             group g=new group();
                                g.name=grptrace;
                                g.colname=i;
                                g.endsat=j;
                                groupsarr.add(g);
                                grptrace="";
                                flag=0;
                         }
                         
                         //flag2++;

                     }
                     
                    if(mats[j][i]==0 && mats[j-1][i]==1)
                    {
                        //if(flag==1)
                        //{
                            //if(flag2>0)
                            //{
                                group g=new group();
                                  g.name=grptrace;
                                  g.colname=i;
                                  g.endsat=j-1;
                                  groupsarr.add(g);
                                  grptrace="";
                                  //flag2=0;
                                  flag=0;

                        
                   }
                  
                      }
             
             
             
             }
         }
         
         
         
         
         group g1 = null;
         for(i=0;i<groupsarr.size();i++)
         {
             g1=groupsarr.get(i);
             //commented
             g1.printgrp(g1);
         }
    
          String s1;
         for(i=0;i<groupsarr.size();i++)
         {
             g1=groupsarr.get(i);
             g1.parenttrace="0";
             g1.lastparent="";
             s1=g1.name;
             //char first=s1.charAt(0);// always a 2
             String[] first=s1.split(" ");
             int d=Integer.parseInt(first[0]);
             for(k=0;k<ct;k++)
             {
                 if(mats[k][0]==d)
                 {   
                     d=k;
                     break;
                 }
             }
             //System.out.print("===="+first[0]);
             //g1.printgrp(g1);
             //System.out.println("  "+d+"   "+k);
             
             int col=g1.colname;
             if(d!=0){
                 
                 //changed 6th feb
                 for(x=0;x<d;x++)
                 {
                     if(x==0)
                     {
                         if(mats[x][col]==1)
                         {
                            y=x;
                         }
                     }
                     else
                     {
                         if(mats[x][col]==1 && mats[x-1][col]==0)
                         {
                             y=x;
                             
                         }       
                     }
                             
                 }
             for(j=0;j<d;j++)
             {
                 
                if(mats[j][col]==1)
              {
                  g1.parenttrace=g1.parenttrace+" "+mats[j][0];
                  g1.parent=g1.parent+" "+mats[j][0];
                  
              }
             }
             
             for(j=y;j<d;j++)
             {
                 if(mats[j][col]==1)
                 g1.lastparent=g1.lastparent+" "+mats[j][0];
             }
             }
             else
             {
                 g1.parenttrace="0";
             }
             
         }
  
         for(i=0;i<groupsarr.size();i++)
         {
              g1=groupsarr.get(i);
              //commented   
              g1.printtrace(g1);
         }
    
         group g2=new group();
         for(i=0;i<groupsarr.size();i++)
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
                      if(g2.valid==-1)
                      {
                          g2.valid=1;
                      }
                  }
                  
              }
              
         }
         group tmp;
         
         for(i=0;i<groupsarr.size();i++)
         {
              g1=groupsarr.get(i);
              //commented   
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
                  n1.lastparent=g1.lastparent;
                  if(n1.lastparent!="")
                  {
                      n1.lastparent=n1.lastparent.substring(1);
                              
                  }
                  //n1.parent=n1.parent.substring(2);
                  nodearr.add(n1);
                  //commented   
                  n1.todoString1(n1);
                  
                }
              
                }
         }
         }
         groupsarr.removeAll(groupsarr);
         System.out.println(".............................................");
         ItemsetTree n1=new ItemsetTree();
         
         for(i=0;i<nodearr.size();i++)
         {
             n1=nodearr.get(i);
             //n1.nodename.trim();
             //commented   
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
                         
                         //commented 
                         ist.todoString1(ist);
                         String s3="   "+ist.nodename;
               
                           if(ist.nodename.equals(n2.lastparent))
                           {
                                     ist.add(n2);
                                     break;
                          }
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
                         
                         
                         //added on 28th jan
                             else
                             {
                                 ist.add(n2);
                             }
                  
             }
             
                
         }
         
         nodearr.removeAll(nodearr);
         
         System.out.println("\n\n\n\nFinal Tree with all nodes:");
        Enumeration e=root.postorderEnumeration();
        while(e.hasMoreElements())
                    {
                        ItemsetTree ist=(ItemsetTree)e.nextElement();
                        int countchild=ist.getChildCount();
                        //commented 
                        ist.todoString1(ist);
                        System.out.println("no of child:"+countchild);
                        ist.tempcount=ist.count;
                    }
         
    
    e=root.postorderEnumeration();
    while(e.hasMoreElements())
                    {
                        ItemsetTree ist=(ItemsetTree)e.nextElement();
                        //changed on 28 jan
                        Enumeration e1=ist.children();
                        while(e1.hasMoreElements())
                        {
                            ItemsetTree ist1=(ItemsetTree)e1.nextElement();
                            if(ist1.nodename.contains(ist.nodename))
                            {
                                ist.tempcount+=ist1.tempcount;
                            }
                            
                        }
                        
                    }
    System.out.println();
    System.out.println();
    System.out.println("\n\n\n\nAnswer");
     e=root.postorderEnumeration();
    while(e.hasMoreElements())
                    {
                        ItemsetTree ist=(ItemsetTree)e.nextElement();
                        
                        if(ist.tempcount>=support)
                        {
                             ist.flag=1;
                            //commented 
                             ist.todoString2(ist);           
                        }
                        else if(ist.nodename=="root")
                        {}
                        else
                            ist.flag=-1;
                         
                    }
    
     ArrayList<ItemsetTree> newtree=new ArrayList();
     
     
     
     
     
    e=root.preorderEnumeration();
     while(e.hasMoreElements())
                    {
                        ItemsetTree ist=(ItemsetTree)e.nextElement();
    
                                if(ist.isLeaf())
                                {
                                    newtree.add(ist);
                                    System.out.println("\nArraylist has:\n");
                                    ist.todoString3(ist);
                                    break;
                                }
                                else
                                {
                                    newtree.add(ist);
                                    System.out.println("Arraylist has :\n");
                                    ist.todoString3(ist);
                                   
                                }
                               
                    }
     int child=root.getChildCount();
     for(i=0;i<child;i++)
     {
         ItemsetTree ist=(ItemsetTree)root.getChildAt(i);
         if(!newtree.contains(ist))
         {
            newtree.add(ist);
            System.out.println("Arraylist has :\n");
            ist.todoString3(ist);
         }
     }
     
     //System.out.println(newtree);
     
     for(int ab=1;ab<newtree.size();ab++)
     {
         ItemsetTree ist2=newtree.get(ab);
         int countchild=ist2.getChildCount();
         ist2.todoString3(ist2);
         System.out.println("no of child:"+countchild);
         //feb 7
         
         if(countchild<=1)
             continue;
         int b;
         ItemsetTree ist5=(ItemsetTree) ist2.getChildAt(0);
         ItemsetTree ist6=(ItemsetTree)ist5.getParent();
         
         if(ist6.nodename.contains(ist5.nodename))
         {
             b = 0;
         }
         else
         {
             b=1;
         }
         for(int ina=b;ina<countchild;ina++)
         {
             ItemsetTree ist3=(ItemsetTree) ist2.getChildAt(ina);//changed 1 to i
         
         
                Enumeration e3=ist3.preorderEnumeration();
        
         while(e3.hasMoreElements())
         {
                            ItemsetTree ist4=(ItemsetTree)e3.nextElement();
                            String node=ist4.nodename;
                            //node=node.substring(2);
                            System.out.println("Print node:"+node);
                            
                            String[] elements=node.split(" ");
                            for(i=0;i<elements.length;i++)
                            {
                                int converted=Integer.parseInt(elements[i]);
                                if(ist2.nodesplit.contains(converted))
                                {
                                    int index=ist2.nodesplit.indexOf(converted);
                                    int countcs=ist2.nodesplitcount.get(index);
                                    countcs+=1;
                                    ist2.nodesplitcount.set(index,countcs);
                                    countcs=0;
                                    continue;
                                    
                                }
                                else
                                {
                                    ist2.nodesplit.add(converted);
                                    ist2.nodesplitcount.add(1);
                                    continue;
                                }
                            }
                           
                            
                            ist2.originalsplitsize=ist2.nodesplit.size();
                           
             
         }
         
     }}
     
    for(int ab=1;ab<newtree.size();ab++)
     {
                            ItemsetTree ist2=newtree.get(ab); 
                            String node=ist2.nodename;
                           //node=node.substring(2);
                           String[] elements=node.split(" ");
                           for(i=0;i<elements.length;i++)
                            {
                                int converted=Integer.parseInt(elements[i]);
                                //System.out.println(converted);
                                if(ist2.nodesplit.contains(converted))
                                {
                                    int index=ist2.nodesplit.indexOf(converted);
                                    int countcs=ist2.nodesplitcount.get(index);
                                    countcs+=ist2.count;
                                    ist2.nodesplitcount.set(index,countcs);
                                    countcs=0;
                                    continue;
                                    
                                }
                                else
                                {
                                    ist2.nodesplit.add(converted);
                                    //ist2.nodesplitcount.add(ist2.tempcount);
                                    //changed 7 feb
                                    ist2.nodesplitcount.add(ist2.count);
                                    continue;
                            
                                }
                            }
                           String[] ele=ist2.nodename.split(" ");
                           //ist2.originalsplitsize=ist2.nodesplit.size();
                            for(i=0;i<ele.length;i++)
                            {
                                int converted=Integer.parseInt(elements[i]);
                                
                                ist2.nodesplit1.add(converted);
                       
                            }
     }
     ArrayList<String> remain=new ArrayList();
     
     for(int ab=1;ab<newtree.size();ab++)
     {
         ItemsetTree ist2=newtree.get(ab);
         //int countchild=ist2.getChildCount();
         //System.out.println("no of child:"+countchild);
         //ist2.todoString3(ist2);
        
         
         for(i=0;i<ist2.nodesplit.size();i++)
         {
             int compare=ist2.nodesplit.get(i);
             for(j=ab+1;j<newtree.size();j++)
             {
                 ItemsetTree ist3=newtree.get(j);
                 int ccount=0;
                 if(ist3.nodesplit.contains(compare))
                 {
                     int ge=ist2.nodesplitcount.get(i);
                     //ge=ge+ist3.count;
                     //feb 8
                     /*for(int ij=0;ij<ist3.nodesplit.size();ij++)
                     {
                        int csplit=ist3.nodesplit.get(ij);
                        
                        if(csplit==compare)
                        {
                            ccount=ist3.nodesplitcount.get(ij);
                            //compare=0;
                            break;
                        }
                        }*/
                     int indexof=ist3.nodesplit.indexOf(compare);
                     ccount=ist3.nodesplitcount.get(indexof);
                     
                     
                        ge=ge+ccount;
                        ist2.nodesplitcount.set(i,ge);
                        //ccount=0;
                        ge=0;
                     
                 }
             }
         }
              
     }
    
     
      for(int ab=1;ab<newtree.size();ab++)
     {
            ItemsetTree ist2=newtree.get(ab);
            System.out.println("\n\nfinal count");
            ist2.todoString3(ist2);
            for(k=0;k<ist2.originalsplitsize;k++)
            {
                int abc=ist2.nodesplitcount.get(k);
                if(abc>=support)
            {
                        String remainadd=ist2.nodename;
                        String[] ele=remainadd.split(" ");
                        for(i=0;i<ele.length;i++)
                        {
                            String iele=String.valueOf(ist2.nodesplit.get(k));
                            if(iele!=ele[i])
                            {
                                remainadd=remainadd+" "+ist2.nodesplit.get(k);
                        remainadd+="        Count:"+ist2.nodesplitcount.get(k);
                        remain.add(remainadd);
                        break;
                            }
                        }
                        
                        /*String[] ele=ist2.nodename.split(" ");
                        for(i=0;i<ele.length;i++)
                        {
                            int iele=Integer.parseInt(ele[i]);
                            if(iele!=ist2.nodesplit.get(k))
                            {
                                
                            }
                        }
                        */
                        
                }
                
         }

     }
     e=root.postorderEnumeration();
     while(e.hasMoreElements())
                    {
                        ItemsetTree ist=(ItemsetTree)e.nextElement();
                        if(ist.tempcount>=support)
                            
                            ist.todoString3(ist);
                        
                    }

     System.out.println();
     System.out.println();
     System.out.println();
     System.out.println();
     System.out.println();
     
     System.out.println("Final Answer:");
     
     for(i=0;i<remain.size();i++)
     {
         System.out.println(remain.get(i));
     }
     
     e=root.postorderEnumeration();
     while(e.hasMoreElements())
                    {
                        ItemsetTree ist=(ItemsetTree)e.nextElement();
                        if(ist.tempcount>=support)
                            
                            ist.todoString4(ist);
                        
                    }
       
      
                                
                                    
                               
                        
                    
     
     
     
     
                    
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