package pokemon;

import java.util.Random;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author KaiBin
 */
public class Pokemon {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        
        
         //Showing available pokemon
        try{
            int i=0;
            int counter=0;
            BufferedReader pokemonName = new BufferedReader(new FileReader("c:/Users/User/Desktop/Programming text file/Pokemon.txt"));
            do 
            {
                if (counter%22==0)
                {
                    System.out.print(pokemonName.readLine()+"\t");
                    i=i+1;
                    if(i%6==0)
                    System.out.print("\n");
                }
                 counter++;
            }while ((pokemonName.readLine())!=null);
            pokemonList = new String[i];
            
            }
        
        catch (FileNotFoundException e)
        {
            System.out.print("File was not found");
        }
        
        saveList();
}
    //saving the names to an array for easy compare
    public static void saveList() throws IOException{
    try{    
        int i=0;
            //Showing all available pokemon
            int counter=0;
            BufferedReader enterList = new BufferedReader(new FileReader("c:/Users/User/Desktop/Programming text file/Pokemon.txt"));
            do 
            {
                if (counter%22==0)
                {
                    pokemonList[i]=enterList.readLine();
                    i=i+1;
                }
                 counter++;
            }while ((enterList.readLine())!=null);
    }
    catch (FileNotFoundException e)
        {
            System.out.print("File was not found");
        }
    }
}
