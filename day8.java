import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class pair
{
    int[] p1 ;
    int[] p2 ;
    float dist ;

    public pair(int[] p1, int[] p2)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.dist = (float) Math.sqrt(Math.pow(p1[0]-p2[0],2)+Math.pow(p1[1]-p2[1],2)+Math.pow(p1[2]-p2[2],2));
    }
}


public class day8 {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("input8.txt");
        
        Scanner input = new Scanner(f);

        int len  =(int) input.findAll("\n").count()+1;
        int[][] nums = new int[len][3];

        input = new Scanner(f);

        for (int index = 0; index<nums.length;index++)
        {
            String lin = input.nextLine();
            nums[index] = new int[] {
            Integer.parseInt(lin.split(",")[0]),
            Integer.parseInt(lin.split(",")[1]),
            Integer.parseInt(lin.split(",")[2])
            };
        }

        input.close();

        ArrayList<pair> pair_list = new ArrayList<>();
        for (int x =0;x<len-1;x++)
        {
            for (int y =x+1;y<len;y++)
            {
                pair_list.add(new pair(nums[x], nums[y]));
            }
        }

        Collections.sort(pair_list, (pair a1, pair a2) -> Math.round(a1.dist - a2.dist));

        ArrayList<ArrayList<int[]>> circuts = new ArrayList<>();

        System.out.println(pair_list.get(6301).p1[0]);
        System.out.println(pair_list.get(6301).p2[0]);
        for (int i = 0; i<6302; i++)
        {
            int count = 0;
            for (ArrayList<int[]> circut : circuts)
            {
                if (circut.contains(pair_list.get(i).p1) || circut.contains(pair_list.get(i).p2))
                {
                    count += 1; 
                    if (circut.contains(pair_list.get(i).p1) && !circut.contains(pair_list.get(i).p2))
                    {
                        circut.add(pair_list.get(i).p2);
                    }
                    else if (circut.contains(pair_list.get(i).p2) && !circut.contains(pair_list.get(i).p1))
                    {
                        circut.add(pair_list.get(i).p1);
                    }
                }
            }
            if (count == 0)
            {
                ArrayList<int[]>  new_circut = new ArrayList<>();
                new_circut.add(pair_list.get(i).p1);
                new_circut.add(pair_list.get(i).p2);
                circuts.add(new_circut);
            }
            if (count > 1)
            {
                // if in two circuts
                boolean found = false;
                ArrayList<int[]> circut_one = null;
                // for (ArrayList<int[]> circut : circuts)
                for (int index = 0; index<circuts.size();index++)
                {
                    if (found && (circuts.get(index).contains(pair_list.get(i).p1) || circuts.get(index).contains(pair_list.get(i).p2)))
                    {
                        for (int[] c : circut_one)
                        {
                            if (!circuts.get(index).contains(c))
                            {
                                circuts.get(index).add(c);
                            }
                        }
                        
                        
                        circuts.remove(circut_one);
                    }
                    else if (circuts.get(index).contains(pair_list.get(i).p1) || circuts.get(index).contains(pair_list.get(i).p2))
                    {
                        circut_one = circuts.get(index);
                        found = true;
                    }
                }
            }
        }

        int[] lens = new int[circuts.size()];
        int Ii = 0;
        for (ArrayList<int[]> circut : circuts)
        {
            lens[Ii] = circut.size();
            Ii+=1;
        }
        Arrays.sort(lens);

        for (int l : lens)
        {
            System.out.println(l);
        }

        
    }
   
}