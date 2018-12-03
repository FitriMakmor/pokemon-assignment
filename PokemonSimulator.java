package pokemonsimulator;

import java.util.Random;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Fitri
 */
public class PokemonSimulator {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        PokemonGUI g = new PokemonGUI();
        Random r = new Random();
        Scanner input = new Scanner(System.in);
        int move; //skills (0,1,2,3)
        int pokeslot1; //which pokemon (pokemon 0,1,2)
        int pokeslot2;
        double player1stats[][]= new double [3][5];
        double player2stats[][]= new double [3][5];
        String pokename1[]=new String[3];
        String pokename2[]=new String[3];
        double speed1=0;
        double speed2=0;
        double accuracy1;
        double accuracy2;
        int weather; //weather (raining, sunny, nice)
        boolean state1[]={false,false,false,false,false}; //0-solarbeam,1-skullbash,2-rage,3-confused,4-burning
        boolean state2[]={false,false,false,false,false};
        String[] pokemonList = new String[19];
        //assigning the skills

        String name;
        System.out.println("Hi Trainer, Welcome to Pokemon World!");
        
        System.out.println("What should I call you ? ");
        
        name=input.nextLine();
        
         System.out.println("\nHi " +name+", Good to see you!");
         System.out.println("Your very own adventure is about to unfold. Are you ready?");
         System.out.println("Let's see what you are capable of. Let's battle!\n");
         System.out.println("Before that, choose your first Pokemon.");
         
    //saving the names to an array for easy compare
    
        try{
            int i=0;
            int counter=0;
            BufferedReader pokemonName = new BufferedReader(new FileReader("C:\\Users\\LEGION\\Documents\\Courses\\Fundamentals of Programming\\PokemonSimulator\\res\\Pokemon.txt"));
            do 
            {
                if (counter%22==0)
                {
                    pokemonList[i]=pokemonName.readLine();
                    i++;
                }
                 counter++;
            }while ((pokemonName.readLine())!=null);
            }
        catch (FileNotFoundException e)
        {
            System.out.print("File was not found");
        }
    
        int newline=0;
        for (int i=0;i<19;i++){
            System.out.print("["+i+"]"+pokemonList[i]+"\t");
            newline++;
            if(newline%6==0)
                System.out.println("");
        }
            System.out.println("");
        
        int answer=input.nextInt();
        pokename1[0]=pokemonList[answer];
        System.out.println("You chose "+pokename1[0]+" as your first Pokemon.\n");
        pokename2[0]=pokemonList[r.nextInt(19)];
        System.out.println("My turn now... "+pokename2[0]+", I choose you!");
        
        System.out.println("Which Pokemon would you like to choose next?");
        answer=input.nextInt();
        pokename1[1]=pokemonList[answer];
        System.out.println("You chose "+pokename1[1]+" as your second Pokemon.\n");
        pokename2[1]=pokemonList[r.nextInt(19)];
        System.out.println("As for me, "+pokename2[1]+" is my choice!");
        
        System.out.println("Your turn now. Which pokemon would you add to complete your team?");
        answer=input.nextInt();
        pokename1[2]=pokemonList[answer];
        System.out.println("You chose "+pokename1[2]+" as your third Pokemon.\n");
        pokename2[2]=pokemonList[r.nextInt(19)];
        System.out.println("I choose "+pokename2[2]+" as my final pokemon!\n");
        
        //weather generator
        weather = r.nextInt(3); //0=nice day, neutral, 1=sunny day, 2=rainy day
        switch (weather){
            case 0: System.out.println("What a nice day, perfect for a Pokemon battle!");
            break;
            case 1: System.out.println("It's a sunny day! With this weather, keep yourselves hydrated.");
            break;
            default: System.out.println("It's raining! Get shelter or you'll be soaking wet!");
        }        
        //switching pokemons
        System.out.println("Which Pokemon would you like to switch to?");
        int select=input.nextInt();
        do{
            pokeslot1=select;
            if (player1stats[pokeslot1][2]<0)
                    System.out.println("That pokemon has fainted, choose another pokemon!");
            else break;
            }while(true);
        System.out.println("You have chosen ...");//to be changed
        
        
    }
    
    
public static void Moveset(int move, int pokeslot1, int pokeslot2, double[][] player1stats, double[][] player2stats, double speed1,double speed2, double[] accuracy, int[] weather, boolean[] state1,boolean[] state2){
    String [][] skill= new String [3][4];
    double dmg;
    double flinch;
    double debuffacc;
    double confused;
    double burn;
    Random r = new Random ();
    String temp="";
    if (state1[0]==true)//solarbeam
    {
        temp=skill[pokeslot1][move];
        skill[pokeslot1][move]="Solar Beam";
    }
    if (state1[1]==true)//skullbash
    {
        temp=skill[pokeslot1][move];
        skill[pokeslot1][move]="Skull Bash";
    }
    if (state1[4]==true)
    {
        player1stats[pokeslot1][2]-=10; //inflicts burn true damage and removes status
        state1[4]=false;
    }
    state1[2]=false;//removes rage
    switch(skill[pokeslot1][move])
    {
        case "Take Down":       dmg=damage(85-accuracy[0],player1stats[pokeslot1][0],90,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]-=dmg/4;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Quick Attack":    speed1+=50;//If possible speed is applied before attack phase
                                dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Tail Whip":       player2stats[pokeslot2][1]-=20;
                                if(player2stats[pokeslot2][1]<0)//Apply this to every stat!
                                    player2stats[pokeslot2][1]=0;
                                break;
        case "Sand Attack":
        case "Flash":           accuracy[1]-=15;
                                break;
        case "Hyper Beam":      speed2-=100;
                                dmg=damage(60-accuracy[0],player1stats[pokeslot1][0],150,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Mega Punch":      dmg=damage(85-accuracy[0],player1stats[pokeslot1][0],80,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Rest":            speed1-=200;
                                player1stats[pokeslot1][2]=160;//Only for Snorlax for now
                                break;
        case "Harden":          player1stats[pokeslot1][1]+=15;
                                break;
        case "Tackle":          dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Recover":         player1stats[pokeslot1][2]+=32.50;//Only for Porygon for now
                                break;
        case "Agility":         player1stats[pokeslot1][3]+=15;
                                break;
        case "Leaf Storm":      dmg=damage(60-accuracy[0],player1stats[pokeslot1][0],130,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;//Actually lowers enemy sp. attack
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Leaf Blade":      dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],90,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;//Actually increases critical hit chance
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Giga Drain":      dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],75,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]+=dmg/2;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Absorb":          dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],20,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]+=dmg;//Doubled healing for balancing purposes
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Razor Leaf":      dmg=damage(65-accuracy[0],player1stats[pokeslot1][0],55,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;//Actually increases critical hit chance
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Bite":            dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],60,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                flinch = r.nextInt(10001);
                                flinch/=100;
                                if(flinch>=70){
                                    speed2-=100;
                                    System.out.println("The enemy flinched with that attack!");
                                }
                                break;
        case "Magical Leaf":    dmg=damage(100,player1stats[pokeslot1][0],60,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;//Sure hit move unless airborne, underground etc.
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Sunny Day":       weather[0]=0;
                                System.out.println("The sunlight got bright!");
                                break;
        case "Solar Beam":      if(state1[0]==true)
                                {
                                    dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                    if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                    }
                                    else player2stats[pokeslot2][2]-=dmg;//Executes in one turn when sunny, else two turns.
                                    rage(state2,player2stats,pokeslot2);
                                    skill[pokeslot1][move]=temp;
                                    temp=null;
                                    state1[0]=false;
                                }
                                else if (weather[0]==0){
                                    dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                    if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                    }
                                    else player2stats[pokeslot2][2]-=dmg;//Executes in one turn when sunny, else two turns.
                                    rage(state2,player2stats,pokeslot2);
                                }
                                else state1[0]=true;
                                break;
        case "Seed Bomb":       dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],80,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Vine Whip":       dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],45,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Leaf Tornado":    dmg=damage(60-accuracy[0],player1stats[pokeslot1][0],65,player2stats[pokeslot2][1],player2stats[pokeslot2][4],1);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                debuffacc=r.nextInt(10001);
                                debuffacc/=100;
                                if (debuffacc>=70){
                                    accuracy[1]-=10;
                                    System.out.println("Enemy's accuracy lowered!");}
                                break;
        case "Growth":          player1stats[pokeslot1][0]+=20;//Actually increases sp.attack as well
                                break;
        case "Ember":           dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],player2stats[pokeslot2][4],3);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Flare Blitz":     dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],player2stats[pokeslot2][4],3);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]-=dmg/3;
                                burn=r.nextInt(10001);
                                burn/=100;
                                if(burn>=90){
                                    state2[4]=true;
                                    System.out.println("The attack caused the enemy to burn!");
                                }
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Scratch":         dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Slash":           dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],70,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;//Actually increases critical hit chance
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Double-Edge":     dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]-=dmg/3;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Blaze Kick":      dmg=damage(60-accuracy[0],player1stats[pokeslot1][0],85,player2stats[pokeslot2][1],player2stats[pokeslot2][4],3);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                burn=r.nextInt(10001);
                                burn/=100;
                                if(burn>=90){
                                    state1[4]=true;
                                    System.out.println("The attack caused the enemy to burn!");
                                }
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Flame Charge":    dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],50,player2stats[pokeslot2][1],player2stats[pokeslot2][4],3);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                player1stats[pokeslot1][3]+=15;
                                break;
        case "Fire Fang":       dmg=damage(65-accuracy[0],player1stats[pokeslot1][0],65,player2stats[pokeslot2][1],player2stats[pokeslot2][4],3);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                burn=r.nextInt(10001);
                                burn/=100;
                                if(burn>=90){
                                    state2[4]=true;
                                    System.out.println("The attack caused the enemy to burn!");
                                }
                                flinch = r.nextInt(10001);
                                flinch/=100;
                                if(flinch>=90){
                                    speed2-=100;
                                    System.out.println("The enemy flinched with that attack!"); //Actually only applies when opponent has not moved
                                }
                                break;
        case "Bubble Beam":     dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],65,player2stats[pokeslot2][1],player2stats[pokeslot2][4],2);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                double debuffspd=r.nextInt(10001);
                                debuffspd/=100;
                                if (debuffspd>=90){
                                    player2stats[pokeslot2][3]-=10;
                                    System.out.println("Enemy's speed lowered!");}
                                break;
        case "Water Gun":       dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],player2stats[pokeslot2][4],2);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Skull Bash":      if(state1[1]==true)
                                {
                                    dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                    if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                    }
                                    else player2stats[pokeslot2][2]-=dmg;//Executes in two turns.
                                    rage(state2,player2stats,pokeslot2);
                                    skill[pokeslot1][move]=temp;
                                    temp=null;
                                    state1[0]=false;
                                }
                                else {
                                System.out.println("Squirtle tucked in its head!"); //If possible, insert variable name instead
                                state1[1]=true;
                                }
                                break;
        case "Hydro Pump":      dmg=damage(50-accuracy[0],player1stats[pokeslot1][0],110,player2stats[pokeslot2][1],player2stats[pokeslot2][4],2);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Rage":            dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],20,player2stats[pokeslot2][1],player2stats[pokeslot2][4],2);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                state1[2]=true;
                                break;
        case "Rain Dance":      weather[0]=1;
                                System.out.println("It started to rain!");
                                break;
        case "Water Pulse":     dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],60,player2stats[pokeslot2][1],player2stats[pokeslot2][4],2);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                confused=r.nextInt(10001);
                                confused/=100;
                                if(confused>=80)
                                    state2[3]=true;
                                break;
        case "Body Slam":       dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],60,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                flinch = r.nextInt(10001);
                                flinch/=100;
                                if(flinch>=70){
                                    speed2-=110;
                                    System.out.println("The enemy is paralyzed with that attack!"); //borrows flinch variable
                                }
                                break;
        case "Flamethrower":    dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],90,player2stats[pokeslot2][1],player2stats[pokeslot2][4],3);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                burn=r.nextInt(10001);
                                burn/=100;
                                if(burn>=90){
                                    state2[4]=true;
                                    System.out.println("The attack caused the enemy to burn!");
                                }
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Swift":           dmg=damage(100,player1stats[pokeslot1][0],60,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Aqua Tail":       dmg=damage(60-accuracy[0],player1stats[pokeslot1][0],90,player2stats[pokeslot2][1],player2stats[pokeslot2][4],2);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Cut":             dmg=damage(65-accuracy[0],player1stats[pokeslot1][0],50,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Facade":          dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],70,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if (state1[3]==true||state1[4]==true){
                                    player2stats[pokeslot2][2]-=2*dmg;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Pound":           dmg=damage(65-accuracy[0],player1stats[pokeslot1][0],50,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Bubble":          dmg=damage(70-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],player2stats[pokeslot2][4],0);
                                if(state1[3]==true){
                                    System.out.println("Your Pokemon is confused!");
                                    System.out.println("It hit itself in the confusion!");
                                    player1stats[pokeslot1][2]-=dmg;
                                    state1[3]=false;break;
                                }
                                else player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
    }
}

public static void rage(boolean[] state2, double player2stats[][],int pokeslot2){
    if (state2[2]==true){
        player2stats[pokeslot2][0]+=10;
        System.out.println("Totodile's rage is building!");
    }
}

public static double damage(double accuracy,double attack,double power,double defense,double type_enemy,int type){
//0 normal, 1 grass, 2 water, 3 fire
      
    if (type_enemy==3&& type==3) //fire attack fire
            return(((attack*power/defense)/20)+2)/2;
    
    else if (type_enemy==1 && type==3) //fire attack grass
            return(((attack*power/defense)/20)+2)*2;
    
    else if (type_enemy==2 && type==3) //fire attack water
            return(((attack*power/defense)/20)+2)/2;
    
    else if (type_enemy==3 && type==1) //grass attack fire
            return(((attack*power/defense)/20)+2)/2;
    
    else if (type_enemy==1 && type==1) //grass attack grass
            return(((attack*power/defense)/20)+2)/2;
    
    else if (type_enemy==2 && type==1) //grass attack water
            return(((attack*power/defense)/20)+2)*2;
      
    else if (type_enemy==3 && type==2) //water attack fire
            return(((attack*power/defense)/20)+2)*2;
    
    else if (type_enemy==3 && type==2) //water attack fire
            return(((attack*power/defense)/20)+2)*2;
    
    else if (type_enemy==1 && type==2) //water attack grass
            return(((attack*power/defense)/20)+2)/2;
    
    else
            return(((attack*power/defense)/20)+2);
}
    
    public static void CombatLoop (int player1stats[][], int player2stats[][], int pokeslot1, int pokeslot2, int weather) {
        Random r = new Random();
        Scanner input = new Scanner(System.in);
        int SpeedCounter1 = 0;    //Player 1's Pokemon Speed Counter
        int SpeedCounter2 = 0;    //Player 2's Pokemon Speed Counter
        
        //Attack Counter loop
        do {
            // random generator to decide who goes first
            int DecideFirstTurn = r.nextInt(2);
            // if 1, player 2's counter starts first
            if (DecideFirstTurn == 1) {
                SpeedCounter2 += player2stats[pokeslot2][3];
                System.out.println("Speed Counter = "+SpeedCounter2);
            }

            //Speed Counter loop
            do {
                SpeedCounter1 += player2stats[pokeslot2][3];
                System.out.println("Speed Counter = "+SpeedCounter1);
                if (SpeedCounter1 >=100)break;
                SpeedCounter2 += player2stats[pokeslot2][3];
                System.out.println("Speed Counter = "+SpeedCounter2);
            } while ((SpeedCounter1 < 100) || (SpeedCounter2 < 100));
            
            //deduct speed, do damage
            if (SpeedCounter1 >=100){
                SpeedCounter1-=100;
                System.out.println("Player 1 attacks!");
                //prompt user to choose skill
                System.out.println("Which move would you like to use?");
                /*
                
                display moves available to select
                
                */
                int MoveSelection = input.nextInt();
                Moveset(MoveSelection,pokeslot1,pokeslot2,player1stats[][],player2stats[][],SpeedCounter1,SpeedCounter2, double[] accuracy, weather, boolean[] state1,boolean[] state2);
            }//the Moveset method's speed must be modified to 2 primitive data types, not arrays, and adjust accordingly
            // not sure what's State 1 and State 2, yet to change these 2 variables
            // double []accuracy? this depends on Moveset's choice right?
            
            else{
                SpeedCounter2-=100;
                System.out.println("Player 2 attacks!");
            }
            
            
        } while (player1stats[pokeslot1][2] > 0 || player2stats[pokeslot2][2] > 0);
    }
}
