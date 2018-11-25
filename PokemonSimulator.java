package pokemon.simulator;

/**
 *
 * @author Jinghong
 */
public class PokemonSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         Scanner s=new Scanner(System.in);
        String name;
        System.out.println("\n\t\t");
        
        System.out.println("Hi Trainer, Welcome to Pokemon World!");
        
        System.out.print("What should I call you ? ");
        
        name=s.nextLine();
        
         System.out.print("Hi " +name);
         System.out.print("! Good to see you.Are you ready ");
         
    }
