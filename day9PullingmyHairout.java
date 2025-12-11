import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day9PullingmyHairout {
    public static void main(String[] args) throws FileNotFoundException 
    {
        File f = new File("input9.txt");
        Scanner input = new Scanner(f);
        int len  =(int) input.findAll("\n").count()+1;
        long[][] nums = new long[len][2];
        input.close();
        input = new Scanner(f);
        for (int i = 0; i<len; i++)
        {
            String nextlin = input.nextLine();
            nums[i][0] = Long.parseLong(nextlin.split(",")[0]);
            nums[i][1] = Long.parseLong(nextlin.split(",")[1]);
        }
        long max = 0;
        long abiding_max = 0;
        input.close();
        for (int x = 1; x<len-1;x++)
        {
            for (int y = x; y<len-1;y++)
            {
                long total = (Math.abs(nums[x][0]-nums[y][0])+1)*(Math.abs(nums[x][1]-nums[y][1])+1);
                if (total>max)
                {
                    max = total;
                }
                boolean good = true;
                if ((
                (nums[x-1][0]<nums[x][0]) && (nums[y][1]>nums[x][1]) ||
                (nums[x-1][0]>nums[x][0]) && (nums[y][1]<nums[x][1]) ||
                (nums[x-1][1]<nums[x][1]) && (nums[y][0]>nums[x][0]) ||
                (nums[x-1][1]>nums[x][1]) && (nums[y][0]<nums[x][0]))||
                (
                (nums[x][0]<nums[x+1][0]) && (nums[y][1]>nums[x][1]) ||
                (nums[x][0]>nums[x+1][0]) && (nums[y][1]<nums[x][1]) ||
                (nums[x][1]<nums[x+1][1]) && (nums[y][0]>nums[x][0]) ||
                (nums[x][1]>nums[x+1][1]) && (nums[y][0]<nums[x][0])))
                {
                    for (int z = 1; z<len-1;z++)
                    {
                        long minx = Math.min(nums[x][0],nums[y][0]);
                        long miny = Math.min(nums[x][1],nums[y][1]);
                        long manx = Math.max(nums[x][0],nums[y][0]);
                        long many = Math.max(nums[x][1],nums[y][1]);

                        boolean bx = (nums[z][0] > minx && nums[z][0] < manx);
                        boolean by = (nums[z][1] > miny && nums[z][1] < many);

                        if (bx && by)
                        {
                            good = false;
                        }
                        if (bx)
                        {
                            if (
                                (nums[z][1] > miny && nums[z-1][1] < miny) ||
                                (nums[z][1] > miny && nums[z+1][1] < miny) ||
                                (nums[z][1] < miny && nums[z-1][1] > miny) ||
                                (nums[z][1] < miny && nums[z+1][1] > miny))
                            {
                                good = false;
                            }
                        }
                        if (by)
                        {
                            if (
                                (nums[z][0] > minx && nums[z-1][0] < minx) ||
                                (nums[z][0] > minx && nums[z+1][0] < minx) ||
                                (nums[z][0] < minx && nums[z-1][0] > minx) ||
                                (nums[z][0] < minx && nums[z+1][0] > minx))
                            {
                                good = false;
                            }
                        }
                    }
                    if (good && total> abiding_max)
                    {
                        abiding_max= total;
                    }
                }
            }    
        }
        // no corners on inside
        // all corners on edges must have box to the left (oriented)
        // for all that have x or y within, doesnt cross y or x within
        System.out.println(max);
        System.out.println(abiding_max);
    }
}

