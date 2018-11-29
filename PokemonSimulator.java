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
         
         System.out.println("\nWhich pokemon would you like to choose?Please type the full name. \n1.CHARMANDER(FIRE) 2.CYNDAQUIL(FIRE) 3.BLAZIKEN(FIRE)\n"
                 + "4.EEVEE(NORMAL) 5.SNORLAX(NORMAL) 6.PORYGON(NORMAL) \n7.SCEPTILE(GRASS) 8.GROTLE(GRASS) 9.SNIVY(GRASS)"
                 + "\n10.SQUIRTLE(WATER) 11.TOTODILE(WATER) 12.MUDKIP(WATER)");
        

      /*  String charmander = "charmander",cyndaquil="cyndaquil",blaziken="blaziken",eevee="eevee";
        String snorlax="snorlax",porygon="porygon",sceptile="sceptile",grotle="grotle";
        String snivy="snivy",squirtle="squirtle",totodile="totodile",mudkip="mudkip";
      
        String answer=s.nextLine();
        */
        
       
   /*     while(answer .equals(charmander)||answer.equals(cyndaquil)||answer.equals(blaziken)||answer.equals(eevee)||answer.equals(snorlax)||answer.equals(porygon)||answer.equals(sceptile)||answer.equals(grotle)||answer.equals(snivy)||answer.equals(squirtle)||answer.equals(totodile)||answer.equals(mudkip))
    {
        if(answer .equals(charmander))
        {
        System.out.println("Okay,so you've chose "+charmander+ " as your first pokemon.");
        break;
        } 
        
        else if(answer .equals(cyndaquil))
        {
        System.out.println("Okay,so you've chose "+cyndaquil+ " as your first pokemon.");
        break;
        }
        
        else if(answer .equals(blaziken))
        {
        System.out.println("Okay,so you've chose "+blaziken+ " as your first pokemon.");
        break;
        }
        
        else if(answer .equals(eevee))
        {
        System.out.println("Okay,so you've chose "+eevee+ " as your first pokemon.");
        break;
        }
        
        else if(answer .equals(snorlax))
        {
        System.out.println("Okay,so you've chose "+snorlax+ " as your first pokemon.");
        break;
        }
        
        else if(answer .equals(porygon))
        {
        System.out.println("Okay,so you've chose "+porygon+ " as your first pokemon.");
        break;
        }
        else if(answer .equals(sceptile))
        {
        System.out.println("Okay,so you've chose "+sceptile+ " as your first pokemon.");
        break;
        }
        else if(answer .equals(grotle))
        {
        System.out.println("Okay,so you've chose "+grotle+ " as your first pokemon.");
        break;
        }
        else if(answer .equals(snivy))
        {
        System.out.println("Okay,so you've chose "+snivy+ " as your first pokemon.");
        break;
        }
        else if(answer .equals(squirtle))
        {
        System.out.println("Okay,so you've chose "+squirtle+ " as your first pokemon.");
        break;
        }
        else if(answer .equals(totodile))
        {
        System.out.println("Okay,so you've chose "+totodile+ " as your first pokemon.");
        break;
        }
        else if(answer .equals(mudkip))
        {
        System.out.println("Okay,so you've chose "+mudkip+ " as your first pokemon.");
        break;
        }
        else
        {
        System.out.println("What ? Wrong pokemon name.Please type again.");
        answer=s.nextLine();
        }
      }
        */
   
   int answer=s.nextInt();
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
    }
        System.out.println("Would you like to give a nickname for your pokemon?");
        
    }
