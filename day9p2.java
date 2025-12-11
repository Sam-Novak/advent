import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class day9p2 {
    public static void main(String[] args) throws FileNotFoundException 
    {
        File f = new File("input9.txt");
        
        Scanner input = new Scanner(f);

        int len  =(int) input.findAll("\n").count()+1;
        long[][] nums = new long[len][2];

        input = new Scanner(f);

        for (int i = 0; i<len; i++)
        {
            String nextlin = input.nextLine();
            nums[i][0] = Math.round(Long.parseLong(nextlin.split(",")[0])/200);
            nums[i][1] = Math.round(Long.parseLong(nextlin.split(",")[1])/200);
        }

        long minx = 100000;
        long miny = 100000;
        long manx = 0;
        long many = 0;
        long prevx = 100000;
        long prevy = 100000;
        long migx = 0;
        long migy = 0;


        for (long[] sq : nums)
        {
            if (sq[0]>manx)
            {
                manx = sq[0];
            }
            if (sq[0]<minx)
            {
                minx = sq[0];
            }
            if (sq[1]>many)
            {
                many = sq[1];
            }
            if (sq[1]<miny)
            {
                miny = sq[1];
            }
            if (Math.abs(sq[0]-prevx)<migx)
            {
                migx = Math.abs(sq[0]-prevx);
            }
            if (Math.abs(sq[1]-prevy)<migy)
            {
                migy = Math.abs(sq[1]-prevy);
            }
            prevx = sq[0];
            prevy = sq[1];
        }
        
        System.out.println(migx);
        System.out.println(migy);
        System.out.println(manx);
        System.out.println(many);
        byte[][] grid = new byte[(int) (manx+1)][(int) (many+1)];
        input.close();
        long[] prev = nums[0];
        for (long[] sq : nums)
        {   
            if (prev[0] < sq[0])
            {
                //diff x <
                for (long i=prev[0];i<=sq[0];i+=1)
                {
                    grid[(int) i][(int) sq[1]] = 1;
                }   
            }   
            else if (prev[0] > sq[0])
            {
                //diff x >
                for (long i=prev[0];i>=sq[0];i-=1)
                {
                    grid[(int) i][(int) sq[1]] = 1;
                }   
            } 
            else if (prev[1] < sq[1])
            {
                //diff y <
                for (long i=prev[1];i<=sq[1];i+=1)
                {
                    grid[(int) sq[0]][(int) i] = 1;
                }   
            } 
            else 
            {
                //diff y >
                for (long i=prev[1];i>=sq[1];i-=1)
                {
                    grid[(int) sq[0]][(int) i] = 1;
                }   
            }    
            prev = sq;
        }

        
        for (int i = 1; i< nums.length-1;i++)
        {
            long[] sq = nums[i];
            int x = (int)sq[0];
            int y = (int)sq[1];
            if ((nums[i-1][0]<nums[i][0]) && (nums[i+1][1]<nums[i][1])) { grid[x+1][y+1]=2; }//Right, Up.    more x or  more y
            if ((nums[i-1][0]<nums[i][0]) && (nums[i+1][0]>nums[i][0])) { grid[x  ][y+1]=2; }//Right, Right.            more y
            if ((nums[i-1][0]<nums[i][0]) && (nums[i+1][1]>nums[i][1])) { grid[x-1][y+1]=2; }//Right, Down.  less x and more y
            if ((nums[i-1][1]>nums[i][1]) && (nums[i+1][0]<nums[i][0])) { grid[x+1][y-1]=2; }//Up, Left.     more x or  less y
            if ((nums[i-1][1]>nums[i][1]) && (nums[i+1][1]<nums[i][1])) { grid[x+1][y  ]=2; }//Up, Up.       more x 
            if ((nums[i-1][1]>nums[i][1]) && (nums[i+1][0]>nums[i][0])) { grid[x+1][y+1]=2; }//Up, Right.    more x and more y
            if ((nums[i-1][0]>nums[i][0]) && (nums[i+1][1]>nums[i][1])) { grid[x-1][y-1]=2; }//Left, Down.   less x or  less y 
            if ((nums[i-1][0]>nums[i][0]) && (nums[i+1][0]<nums[i][0])) { grid[x  ][y-1]=2; }//Left, Left.              less y
            if ((nums[i-1][0]>nums[i][0]) && (nums[i+1][1]<nums[i][1])) { grid[x+1][y-1]=2; }//Left, Up.     more x and less y 
            if ((nums[i-1][1]<nums[i][1]) && (nums[i+1][0]>nums[i][0])) { grid[x-1][y+1]=2; }//Down, Right.  less x or  more y 
            if ((nums[i-1][1]<nums[i][1]) && (nums[i+1][1]>nums[i][1])) { grid[x-1][y  ]=2; }//Down, Down.   less x
            if ((nums[i-1][1]<nums[i][1]) && (nums[i+1][0]<nums[i][0])) { grid[x-1][y-1]=2; }//Down, Left.   less x and less y
        }
                    
                    

        boolean filling = true;

        Random r = new Random();
        while (filling)
        {
            filling = false;

            if (r.nextFloat()<0.1)
            {
                System.out.println(manx);
            }
            

            for (int x = 1; x < manx; x++)
            {
                
                for (int y = 1; y < many; y++)
                {
                    if (grid[x][y]==2)
                    {
                        if (grid[x-1][y]==0)
                        {
                            grid[x-1][y] = 2;
                            filling = true;
                        }
                        if (grid[x+1][y]==0)
                        {
                            grid[x+1][y] = 2;
                            filling = true;
                        }
                        if (grid[x][y-1]==0)
                        {
                            grid[x][y-1] = 2;  
                            filling = true; 
                        }
                        if (grid[x][y+1]==0)
                        {
                            grid[x][y+1] = 2;
                            filling = true;
                        }
                    }
                }
            }
        }

        System.out.println("done");

        for (byte[] lin : grid)
        {
            for (byte c : lin)
            {
                System.out.print((int) c);
            }
            System.out.println("");
        }




    }
}

