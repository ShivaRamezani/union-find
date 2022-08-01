import java.util.*;
import java.lang.*;
import java.io.*;

public class Project3 
{
    /* ROOT UNION and FIND were not made by us they were taken from the internet */
    /* https://www.hackerearth.com/practice/notes/disjoint-set-union-union-find/ */
    //finding root of an element.
    int root(int Arr[ ],int i)
    {
        while(Arr[ i ] != i)           //chase parent of current element until it reaches root.
        {
        i = Arr[ i ];
        }
        return i;
    }

    /*modified union function where we connect the elements by changing the root of one of the element */

    void union(int Arr[ ] ,int A ,int B)
    {
        int root_A = root(Arr, A);       
        int root_B = root(Arr, B);  
        Arr[ root_A ] = root_B ;       //setting parent of root(A) as root(B).
    }
    
    boolean find(int Arr[], int A,int B)
    {
        if (root(Arr, A) == root(Arr, B)) // if A and B have same root,means they are connected.
        return true;
        else
        return false;
    }

    // remove an element from a disjoint set
    int [] remove(int Arr[], int del)
    {
        if (Arr[del] == del)
        {
            int temp = 0;
            for(int pass = 0; pass<Arr.length; pass++)
            {
                if(pass == del){pass++;}
                if (Arr[pass] == del)
                {
                    temp = pass;
                    Arr[pass] = pass;
                    break;
                }
            }
            if (temp == 0){return Arr;}
            for(int pass = 0; pass<Arr.length; pass++)
            {
                if (del == pass){pass++;}
                if (pass == Arr.length){return Arr;}
                if (Arr[pass] == del)
                {
                    Arr[pass] = temp;
                }
            }
            return Arr;
        }
        else
        {
            int temp = Arr[del];
            Arr[del] = del; 
            for (int pass = 0; pass<Arr.length; pass++)
            {
                if (pass == del){pass++;}
                if (Arr[pass] == del){Arr[pass] = temp;}
            }
            return Arr;

        }
    }

    void unionFindDelete(int positions[], String elements[])
    {
        Scanner input = new Scanner(System.in);
        boolean again = true;
        while (again)
        {
            again = false;
            System.out.println("What do you want to do?");
            System.out.println("Press 1 for union, 2 for find, 3 to remove from union, 4 to exit");
            int a;
            try{
            a = input.nextInt();} catch(Exception e)
            {System.out.println("oops something went wrong please restart and try again"); return;}
            switch (a)
            {
                case 1:
                    System.out.println("You chose union!");
                    System.out.println("What two elements would you like to connect? (please enter the postition that they have in the array)");
                    int first = input.nextInt();
                    int second = input.nextInt();
                    union(positions,first,second);
                    System.out.printf("\n"+ elements[first+1] + " and " + elements[second+1] + " are now connected!\n\n");
                    again = true;
                    break;
                case 2:
                    System.out.println("You chose find!");
                    System.out.println("What elements would you like to check to see for a connection? (please enter the position that they have in the array)");
                    int findFirst = input.nextInt();
                    int findSecond = input.nextInt();
                    boolean result = find(positions,findFirst,findSecond);
                    if (result){System.out.printf(elements[findFirst+1] + " and " + elements[findSecond+1] + " are connected!\n\n");}
                    else{{System.out.printf(elements[findFirst+1] + " and " + elements[findSecond+1] + " are NOT connected!\n\n");}}
                    again = true;
                    break;
                case 3:
                    System.out.println("You chose delete!");
                    System.out.println("What element would you like to remove from a disjoint set? (please enter the position that it has in the array)");
                    int delEle = input.nextInt();
                    remove(positions,delEle);
                    System.out.printf("The element " + elements[delEle+1] + " was removed from the set if it was a part of one.\n\n");
                    again = true;
                    break;
                case 4:
                    System.out.printf("\nThank you for using this program!\n");
                    again = false;
                    return;
                default:
                    System.out.printf("\nERROR please try again.\n");
                    again = true;
                    break;
            }
        }
    }

    

    public static void main (String [] args)
    {
        Project3 test = new Project3();

         // creates the arrays that we need to set up our disjoint sets and one to store the elements
         Scanner input = new Scanner(System.in);
         int a = input.nextInt();
         int disjoint[] = new int[a];
         String disjointElements[] = new String[a+1];

         for (int k = 0; k < disjoint.length; k++)
         {
             disjoint[k] = k;
         }
 
         for (int i = 0; i<a+1; i++)
         {
             disjointElements[i] = input.nextLine();
         }
       
         for (int j = 1, n = 0; j<a+1; j++,n++)
         {
             if(n%5 == 0){System.out.println();}
             System.out.printf(j-1 + " element is |" + disjointElements[j] + "| ");
         }
         System.out.println();
         for (int l = 0; l<disjoint.length; l++)
         {
             System.out.printf(disjoint[l] + " ");
         }
         System.out.println();
         test.unionFindDelete(disjoint, disjointElements);
         input.close();
    }
}
