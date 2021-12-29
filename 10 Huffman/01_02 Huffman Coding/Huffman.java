// name: J1-24        date: 4-14-2021
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
         //Make a frequency table of the letters
      Map<Character, Integer> m = new HashMap<Character, Integer>();
      for(int x = 0; x < message.length(); x++)
      {
         if(m.containsKey(message.charAt(x)))
            m.put(message.charAt(x), m.get(message.charAt(x)) + 1);
         else
            m.put(message.charAt(x), 1);
      }
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).   
      PriorityQueue<HuffmanTreeNode> p = new PriorityQueue<HuffmanTreeNode>();  
      for(Character c : m.keySet())
      {
         p.add(new HuffmanTreeNode(c, m.get(c)));
      }
      	//Use the priority queue of nodes to build the Huffman tree
      while(p.size() != 1)
      {
         HuffmanTreeNode h = new HuffmanTreeNode('*', 0);
         HuffmanTreeNode one = p.remove();
         HuffmanTreeNode two = p.remove();
         h.setFreq(one.getFreq() + two.getFreq());
         h.setRight(one);
         h.setLeft(two);
         p.add(h);
      }
      HuffmanTreeNode huff = p.peek();
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
      //File f1 = new File("message." + middlePart + ".txt");
      PrintWriter f = new PrintWriter(new FileWriter("message." + middlePart + ".txt"));
      for(int x = 0; x < message.length(); x++)
      {
         huffmanize1(message.charAt(x), huff, "", f);
      }
         //System.out.println the binary message
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      System.out.println();
      Set<Character> m2 = new HashSet<Character>();
      //File p1 = new File("scheme." + middlePart + ".txt");
      PrintWriter p2 = new PrintWriter(new FileWriter("scheme." + middlePart + ".txt"));
      for(int x = 0; x < message.length(); x++)
      {
         m2.add(message.charAt(x));
      }
      for(Character c : m2)
      {
         huffmanize2(c, huff, "", p2);
      }
      f.close();
      p2.close();
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
         
            
   }
   private static void huffmanize1(Character x, HuffmanTreeNode h, String s, PrintWriter f) 
   {
      if(h.getValue() == x)
      {
         System.out.print(s);
         f.print(s);
      }
      else if(h.getLeft().getValue() == x)
      {
         s += "0";
         huffmanize1(x, h.getLeft(), s, f);
      }
      else
      {
         s += "1";
         huffmanize1(x, h.getRight(), s, f);
      }
   }
   private static void huffmanize2(Character x, HuffmanTreeNode h, String s, PrintWriter f) 
   {
      if(h.getValue() == x)
      {
         System.out.println("" + x + "" + s);
         f.println("" + x + "" + s);
      }
      else if(h.getLeft().getValue() == x)
      {
         s += "0";
         huffmanize2(x, h.getLeft(), s, f);
      }
      else
      {
         s += "1";
         huffmanize2(x, h.getRight(), s, f);
      }
   }

}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private Character value;
   private int freq;
   private HuffmanTreeNode left;
   private HuffmanTreeNode right;
   public HuffmanTreeNode(Character c, int f)
   {
      value = c;
      freq = f;
      left = null;
      right = null;
   }
   public HuffmanTreeNode(Character c, int f, HuffmanTreeNode l, HuffmanTreeNode r)
   {
      value = c;
      freq = f;
      left = l;
      right = r;
   }
   public Character getValue() 
   {
      return value; 
   } 
   public HuffmanTreeNode getLeft() 
   {
      return left; 
   } 
   public HuffmanTreeNode getRight() 
   {
      return right; 
   } 
   public int getFreq()
   {
      return freq;
   }
   public void setValue(Character theNewValue)    
   {
      value = theNewValue; 
   }
   public void setLeft(HuffmanTreeNode theNewLeft)      
   {
      left = theNewLeft;
   }
   public void setRight(HuffmanTreeNode theNewRight)   
   {
      right = theNewRight;
   }
   public void setFreq(int f)
   {
      freq = f;
   }
   public String toString()
   {
      return "" + value + ":" + freq;
   }
   public int compareTo(HuffmanTreeNode f)
   {
      if(value == '*' && f.getValue() != '*')
         return -1;
      else if(value != '*' && f.getValue() == '*')
         return 1;
      else if(freq < f.getFreq())
         return -1;
      else if(freq > f.getFreq())
         return 1;
      else
      {
         if(value.compareTo(f.getValue()) < 0)
            return -1;
         else if(value.compareTo(f.getValue()) > 0)
            return 1;
      }
      return 0;
   }
}
