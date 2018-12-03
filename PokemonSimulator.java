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
        
         System.out.print("\nHi " +name);
         System.out.print("! Good to see you!");
         System.out.println("Your very own adventure is about to unfold. Are you ready?");
         System.out.println("Now, I'll let you to choose your very first pokemon!");
         
         System.out.println("\nWhich pokemon would you like to choose?Please type the number. \n1.CHARMANDER(FIRE) 2.CYNDAQUIL(FIRE) 3.BLAZIKEN(FIRE)\n"
                 + "4.EEVEE(NORMAL) 5.SNORLAX(NORMAL) 6.PORYGON(NORMAL) \n7.SCEPTILE(GRASS) 8.GROTLE(GRASS) 9.SNIVY(GRASS)"
                 + "\n10.SQUIRTLE(WATER) 11.TOTODILE(WATER) 12.MUDKIP(WATER)");
   
 int answer=s.nextInt();
   s.nextLine();
   while (!(answer>0 && answer<20))
   {
       System.out.println("You have enter a wrong number,Please enter again!");
       answer=s.nextInt();
       s.nextLine();
   }
   
     switch (answer)
  {
      case(1):
       System.out.println("Okay,so you've chose CHARMANDER as your first pokemon.");
        break;
      case(2):
      System.out.println("Okay,so you've chose CYNDAQUIL as your first pokemon.");
        break;
       case(3):
      System.out.println("Okay,so you've chose BLAZIKEN as your first pokemon.");
        break;
         case(4):
      System.out.println("Okay,so you've chose EEVEEE as your first pokemon.");
        break;
         case(5):
      System.out.println("Okay,so you've chose SNORLAX as your first pokemon.");
        break;
         case(6):
      System.out.println("Okay,so you've chose PORYGON as your first pokemon.");
        break;
         case(7):
      System.out.println("Okay,so you've chose SCEPTILE as your first pokemon.");
        break;
         case(8):
      System.out.println("Okay,so you've chose GROTLE as your first pokemon.");
        break;
         case(9):
      System.out.println("Okay,so you've chose SNIVY as your first pokemon.");
        break;
         case(10):
      System.out.println("Okay,so you've chose SQUIRTLE as your first pokemon.");
        break;
         case(11):
      System.out.println("Okay,so you've chose TOTODILE as your first pokemon.");
        break;
         case(12):
      System.out.println("Okay,so you've chose MUDKIP as your first pokemon.");
        break;
        case(13):
      System.out.println("Okay,so you've chose BULBASAUR as your first pokemon.");
        break;
        case(14):
      System.out.println("Okay,so you've chose CHIKORITA as your first pokemon.");
        break;
        case(15):
      System.out.println("Okay,so you've chose TURTWIG as your first pokemon.");
        break;
        case(16):
      System.out.println("Okay,so you've chose CHIMCHAR as your first pokemon.");
        break;
        case(17):
      System.out.println("Okay,so you've chose PIPLUP as your first pokemon.");
        break;
        case(18):
      System.out.println("Okay,so you've chose TEPIG as your first pokemon.");
        break;
        default:
      System.out.println("Okay,so you've chose OSHAWOTT as your first pokemon.");
        break;                
        
     }
 
      System.out.println("Would you like to give a nickname for your pokemon?");
        nickname=s.nextLine();
        System.out.println("You've named it to "+nickname+ " !");
        
        
        
        
        
        
        //Ending of game
        
        if()
        System.out.println("You win the battle!");
        else
        System.out.println("Your pokemon is fainted ! You have no more pokemon to battle ! You lose!");
        
        
    }
}

