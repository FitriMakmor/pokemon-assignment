package PokemonSimulator;

import java.util.Random;

/**
 *
 * @author Fitri
 */
public class PokemonSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        do{
            pokeslot1=select;
            if (player1stats[pokeslot1][2]<0)
                    System.out.println("That pokemon has fainted, choose another pokemon!");
            else break;
            }while(true);
    }
public static void Moveset(int move, int pokeslot1, int pokeslot2, double[][] player1stats, double[][] player2stats, double[] speed, double[] accuracy, int[] weather, boolean[] state1,boolean[] state2){
    String [][] skill= new String [3][4];
    double dmg;
    Random r = new Random ();
    String temp;
    if (state1[0]==true);//solarbeam
    {
        temp=skill[pokeslot1][move];
        skill[pokeslot1][move]="Solar Beam";
    }
    if (state1[1]==true);//skullbash
    {
        temp=skill[pokeslot1][move];
        skill[pokeslot1][move]="Skull Bash";
    }
    state1[2]=false;//removes rage
    switch(skill[pokeslot1][move])
    {
        case "Take Down":       dmg=damage(85-accuracy[0],player1stats[pokeslot1][0],90,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]-=dmg/4;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Quick Attack":    speed[0]+=50;//If possible speed is applied before attack phase
                                dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Tail Whip":       player2stats[pokeslot2][1]-=20;
                                if(player2stats[pokeslot2][1]<0)
                                    player2stats[pokeslot2][1]=0;
        case "Sand Attack":
        case "Flash":           accuracy[1]-=15;
                                break;
        case "Hyper Beam":      speed[1]-=100;
                                dmg=damage(90-accuracy[0],player1stats[pokeslot1][0],150,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Mega Punch":      dmg=damage(85-accuracy[0],player1stats[pokeslot1][0],80,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Rest":            speed[0]-=200;
                                player1stats[pokeslot1][2]=160;//Only for Snorlax for now
                                break;
        case "Harden":          player1stats[pokeslot1][1]+=15;
                                break;
        case "Tackle":          dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Recover":         player1stats[pokeslot1][2]+=32.50;//Only for Porygon for now
                                break;
        case "Agility":         player1stats[pokeslot1][3]+=15;
                                break;
        case "Leaf Storm":      dmg=damage(90-accuracy[0],player1stats[pokeslot1][0],130,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;//Actually lowers enemy sp. attack
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Leaf Blade":      dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],90,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;//Actually increases critical hit chance
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Giga Drain":      dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],75,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]+=dmg/2;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Absorb":          dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],20,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]+=dmg;//Doubled healing for balancing purposes
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Razor Leaf":      dmg=damage(95-accuracy[0],player1stats[pokeslot1][0],55,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;//Actually increases critical hit chance
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Bite":            dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],60,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                double flinch = r.nextInt(10001);
                                flinch/=100;
                                if(flinch>=70)
                                    speed[1]-=100;
                                break;
        case "Magical Leaf":    dmg=damage(100,player1stats[pokeslot1][0],60,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;//Sure hit move unless airborne, underground etc.
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Sunny Day":       weather[0]=0;
                                System.out.println("The sunlight got bright!");
                                break;
        case "Solar Beam":      if(state1[0]==true)
                                {
                                    dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],1);
                                    player2stats[pokeslot2][2]-=dmg;//Executes in one turn when sunny, else two turns.
                                    rage(state2,player2stats,pokeslot2);
                                    skill[pokeslot1][move]=temp;
                                    temp=null;
                                    state1[0]=false;
                                }
                                else if (weather[0]==0){
                                    dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],1);
                                    player2stats[pokeslot2][2]-=dmg;//Executes in one turn when sunny, else two turns.
                                    rage(state2,player2stats,pokeslot2);
                                }
                                else state1[0]=true;
                                break;
        case "Seed Bomb":       dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],80,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Leaf Tornado":    dmg=damage(90-accuracy[0],player1stats[pokeslot1][0],65,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                double debuffacc=r.nextInt(10001);
                                debuffacc/=100;
                                if (debuffacc>=70){
                                    accuracy[1]-=10;
                                    System.out.println("Enemy's accuracy lowered!");}
                                break;
        case "Growth":          player1stats[pokeslot1][0]+=20;//Actually increases sp.attack as well
                                break;
        case "Ember":           dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],3);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Flare Blitz":     dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],3);
                                player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]-=dmg/3;//Actually has a 10% chance of burning target
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Scratch":         dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Slash":           dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],70,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;//Actually increases critical hit chance
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Double-Edge":     dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]-=dmg/3;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Blaze Kick":      dmg=damage(90-accuracy[0],player1stats[pokeslot1][0],85,player2stats[pokeslot2][1],3);
                                player2stats[pokeslot2][2]-=dmg;//Actually has a 10% chance of burning target
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Flame Charge":    dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],50,player2stats[pokeslot2][1],3);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                player1stats[pokeslot1][3]+=15;
                                break;
        case "Bubble Beam":     dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],65,player2stats[pokeslot2][1],2);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                double debuffspd=r.nextInt(10001);
                                debuffspd/=100;
                                if (debuffspd>=90){
                                    player2stats[pokeslot2][3]-=10;
                                    System.out.println("Enemy's speed lowered!");}
                                break;
        case "Water Gun":       dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],2);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Skull Bash":      if(state1[1]==true)
                                {
                                    dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],1);
                                    player2stats[pokeslot2][2]-=dmg;//Executes in two turns.
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
        case "Hydro Pump":      dmg=damage(80-accuracy[0],player1stats[pokeslot1][0],110,player2stats[pokeslot2][1],2);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                break;
        case "Rage":            dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],20,player2stats[pokeslot2][1],2);
                                player2stats[pokeslot2][2]-=dmg;
                                rage(state2,player2stats,pokeslot2);
                                state1[2]=true;
                                break;
        case "Rain Dance":      weather[0]=1;
                                System.out.println("It started to rain!");
    }
}
public static void rage(boolean[] state2, double player2stats[][],int pokeslot2){
    if (state2[2]==true){
        player2stats[pokeslot2][0]+=10;
        System.out.println("Totodile's rage is building!");
    }
}
}
