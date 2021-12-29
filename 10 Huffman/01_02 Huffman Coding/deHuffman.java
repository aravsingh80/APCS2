// Name: J1-24             Date: 4-6-2021
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode t = new TreeNode("");
      while(huffLines.hasNextLine())
      {
         String s = huffLines.nextLine();
         String let = s.substring(0, 1);
         s = s.substring(1, s.length());
         if(s.charAt(0) == '0')
            t.setLeft(huffmanTree(t.getLeft(), s, let));
         else
            t.setRight(huffmanTree(t.getRight(), s, let));
      }
      return t;
   }
   private static TreeNode huffmanTree(TreeNode t, String s, String let)
   {
      if(t == null)
      {
         if(s.length() > 1)
         {
            t = new TreeNode("");
            if(s.charAt(1) == '0')
               t.setLeft(huffmanTree(t.getLeft(), s.substring(1, s.length()), let));
            else
               t.setRight(huffmanTree(t.getRight(), s.substring(1, s.length()), let)); 
         }
         else
         {
            t = new TreeNode(let);
         }
      }
      else
      {
         if(s.length() > 1)
         {
            if(s.charAt(1) == '0')
               t.setLeft(huffmanTree(t.getLeft(), s.substring(1, s.length()), let));
            else
               t.setRight(huffmanTree(t.getRight(), s.substring(1, s.length()), let)); 
         }
      }
      return t;
   }
   public static String dehuff(String text, TreeNode root)
   {
      String toReturn = "";
      TreeNode temp = root;
      for(int x = 0; x < text.length(); x++)
      {
         if(text.charAt(x) == '0')
         {
            temp = temp.getLeft();
            if(!(temp.getValue().equals("")))
            {
               toReturn += temp.getValue();
               temp = root;
            }
         }
         else
         {
            temp = temp.getRight();
            if(!(temp.getValue().equals("")))
            {
               toReturn += temp.getValue();
               temp = root;
            }
         }
      }
      return toReturn;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
