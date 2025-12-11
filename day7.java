
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day7 {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("input7.txt");
        
        Scanner input = new Scanner(f);

        int len = (int) input.findAll("\n").count()+1;

        String[] lines = new String[len];

        int o = 0;

        input = new Scanner(f);

        while (input.hasNextLine())
        {
            lines[o] = input.nextLine();
            o += 1;
        }

        long[][] grid = new long[len][(int) lines[0].length()];

        o = 0;
        int p;
        for (String l : lines)
        {
            p = 0;
            for (char c : l.toCharArray())
            {
                if (c=='S')
                {
                    grid[o][p] = -1;
                }
                if (c=='.')
                {
                    grid[o][p] = 0;
                }
                if (c=='^')
                {
                    grid[o][p] = -2;
                }

                p+=1;
            }
            o += 1;
        }

        for (int x = 1; x < len-1; x++)
        {
            for (int y = 1; y < grid[y].length-1; y++)
            {
                if (grid[x][y]==-1)
                {
                    grid[x+1][y] = 1;
                }
                
                if ((grid[x][y]>0) && (grid[x+1][y]!=-2))
                {
                    grid[x+1][y] += grid[x][y];
                }
                if ((grid[x][y]>0) && (grid[x+1][y]==-2))
                {
                    grid[x+1][y-1] += grid[x][y];
                }
                if ((grid[x][y]>0) && (grid[x+1][y]==-2))
                {
                    grid[x+1][y+1] += grid[x][y];
                }
            }
        }

        
        long total = 0;

        for (int x = 1; x < len-1; x++)
        {
            for (int y = 1; y < grid[x].length-1; y++)
            {
                if ((grid[x][y]==-2) && (grid[x-1][y]>0))
                {
                    total += 1;
                }
            }
        }

        for (int x = 1; x < len-1; x++)
        {
            for (int y = 0; y < grid[x].length; y++)
            {
                if (grid[x][y]==-2)
                {
                    System.out.print('^');
                }
                else
                {
                    System.out.print(grid[x][y]);
                }
            }

            System.out.println();
        }
        System.out.println(total);

        total = 2;

        for (int y = 0; y < grid[0].length; y++)
        {
            if (grid[len-1][y]>0)
            {
                total += grid[len-1][y];
            }
        }
        
        System.out.println(total);

    }
}
