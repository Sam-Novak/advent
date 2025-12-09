import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class day4
{
    public static void main(String[]args) throws FileNotFoundException
    {
        File f = new File("input4.txt");
        
        Scanner input = new Scanner(f);

        int h = 136;
        int w = 136;

        // int h = 10;
        // int w = 10;

        String[] grid = new String[w];

        int i= 0;
        while (input.hasNextLine())
        {
            grid[i] = input.nextLine();
            i++;
        }

        for (String L:grid)
        {
            System.out.println(L);
        }

        int total = 0;
        boolean running = true;
        while (running)
        {
            running = false;
            for (int x=0;x<w;x++)
            {
                for (int y=0;y<h;y++)
                {
                    int count = 0;
                    for (int subx=-1;subx<=1;subx++)
                    {
                        for (int suby=-1;suby<=1;suby++)
                        {
                            
                            if (x+subx<0 || x+subx>=w ||
                                y+suby<0 || y+suby>=h  )
                            {
                                continue;
                            }

                            else if ((grid[x+subx].charAt(y+suby)=='@' ))
                            {
                                count++;

                            }

                        }
                    }
                    if ((count<5)&& (grid[x].charAt(y)=='@'))
                    {
                        grid[x] = grid[x].substring(0,y)+"."+grid[x].substring(y+1);
                        total++;
                        running = true;
                    }
                }
            }
        }
        System.out.println(total);

    }
}