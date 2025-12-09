import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        File f = new File("input5.txt");
        
        Scanner input = new Scanner(f);

        int len  =(int) input.findAll("\n").count();
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

        ArrayList<pair> pair_list = new ArrayList<pair>();
        for (int x =0;x<len-1;x++)
        {
            for (int y =x+1;y<len;y++)
            {
                pair_list.add(new pair(nums[x], nums[y]));
            }
        }

        Collections.sort(pair_list, new Comparator<pair>() {
            @Override
            public int compare(pair a1, pair a2) {
            return Math.round(a1.dist - a2.dist);
            }
        });

        
    }
   
}
