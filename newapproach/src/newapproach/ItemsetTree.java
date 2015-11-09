package newapproach;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

public class ItemsetTree extends DefaultMutableTreeNode{

    String nodename;
    String parent;
    String lastparent;
    int count;
    String parenttrace;
    int tempcount=0;
    int flag=0;
    int originalsplitsize=0;
    ArrayList<Integer> nodesplit=new ArrayList();
    ArrayList<Integer> nodesplit1=new ArrayList();
    ArrayList<Integer> nodesplitcount=new ArrayList();
    
    
    
    public void todoString1(ItemsetTree i)
{
   System.out.println("TreeNode:"+i.nodename+" Parent:"+i.parent+" Count:"+i.count+" Parenttrace:"+i.parenttrace+" Last:"+i.lastparent);
   
}   
    
    public void todoString2(ItemsetTree i)
{
   System.out.println("TreeNode:"+i.nodename+" Parent:"+i.parent+" Count:"+i.count+" TempCount:"+i.tempcount+" Flag:"+i.flag);
   
}   
    
    public void todoString3(ItemsetTree i)
{
   System.out.println("TreeNode:"+i.nodename+" Parent:"+i.parent+" Count:"+i.count+" TempCount:"+i.tempcount+" Flag:"+i.flag+" nodesplit:"+i.nodesplit+" nodesplitcount:"+i.nodesplitcount);
   
   
}  
    public void todoString4(ItemsetTree i)
    {
        System.out.println(i.nodename+" Count:"+i.tempcount);
    }
}
