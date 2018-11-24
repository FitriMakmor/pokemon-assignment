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
        // TODO code application logic here
    }
public static void Moveset(int move, int pokeslot1, int pokeslot2, double[][] player1stats, double[][] player2stats, double[] speed, double[] accuracy, int[] weather, boolean state[]){
    String [][] skill= new String [3][4];
    double dmg;
    Random r = new Random ();
    String temp;
    if (state[0]==true);//solarbeam
    {
        temp=skill[pokeslot1][move];
        skill[pokeslot1][move]="Solar Beam";
    }
    switch(skill[pokeslot1][move])
    {
        case "Take Down":       dmg=damage(85-accuracy[0],player1stats[pokeslot1][0],90,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]-=dmg/4;
                                break;
        case "Quick Attack":    speed[0]+=50;//If possible speed is applied before attack phase
                                dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                break;
        case "Tail Whip":        player2stats[pokeslot2][1]-=20;
                                if(player2stats[pokeslot2][1]<0)
                                    player2stats[pokeslot2][1]=0;
        case "Sand Attack":
        case "Flash":           accuracy[1]-=15;
                                break;
        case "Hyper Beam":      speed[1]-=100;
                                dmg=damage(90-accuracy[0],player1stats[pokeslot1][0],150,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                break;
        case "Mega Punch":      dmg=damage(85-accuracy[0],player1stats[pokeslot1][0],80,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                break;
        case "Rest":            speed[0]-=200;
                                player1stats[pokeslot1][2]=160;//Only for Snorlax for now
                                break;
        case "Harden":          player1stats[pokeslot1][1]+=15;
                                break;
        case "Tackle":          dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                break;
        case "Recover":         player1stats[pokeslot1][2]+=32.50;//Only for Porygon for now
                                break;
        case "Agility":         player1stats[pokeslot1][3]+=15;
                                break;
        case "Leaf Storm":      dmg=damage(90-accuracy[0],player1stats[pokeslot1][0],130,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;//Actually lowers enemy sp. attack
                                break;
        case "Leaf Blade":      dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],90,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;//Actually increases critical hit chance
                                break;
        case "Giga Drain":      dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],75,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]+=dmg/2;
                                break;
        case "Absorb":          dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],20,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]+=dmg;//Doubled healing for balancing purposes
                                break;
        case "Razor Leaf":      dmg=damage(95-accuracy[0],player1stats[pokeslot1][0],55,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;//Actually increases critical hit chance
                                break;
        case "Bite":            dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],60,player2stats[pokeslot2][1],0);
                                player2stats[pokeslot2][2]-=dmg;
                                double flinch = r.nextInt(10001);
                                flinch/=100;
                                if(flinch>=70)
                                    speed[1]-=100;
                                break;
        case "Magical Leaf":    dmg=damage(100,player1stats[pokeslot1][0],60,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;//Sure hit move unless airborne, underground etc.
                                break;
        case "Sunny Day":       weather[0]=0;
                                break;
        case "Solar Beam":      if(state[0]==true)
                                {
                                    dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],1);
                                    player2stats[pokeslot2][2]-=dmg;//Executes in one turn when sunny, else two turns.
                                    state[0]=false;
                                }
                                else if (weather[0]==0){
                                    dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],1);
                                    player2stats[pokeslot2][2]-=dmg;//Executes in one turn when sunny, else two turns.
                                }
                                else state[0]=true;
                                skill[pokeslot1][move]=temp;
                                temp=null;
                                break;
        case "Seed Bomb":       dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],80,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;
                                break;
        case "Leaf Tornado":    dmg=damage(90-accuracy[0],player1stats[pokeslot1][0],65,player2stats[pokeslot2][1],1);
                                player2stats[pokeslot2][2]-=dmg;
                                double lower=r.nextInt(10001);
                                lower/=100;
                                if (lower>=70)
                                    accuracy[1]-=10;
                                break;
        case "Growth":          player1stats[pokeslot1][0]+=20;
                                break;
        case "Ember":           dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],40,player2stats[pokeslot2][1],3);
                                player2stats[pokeslot2][2]-=dmg;
                                break;
        case "Flare Blitz":     dmg=damage(100-accuracy[0],player1stats[pokeslot1][0],120,player2stats[pokeslot2][1],3);
                                player2stats[pokeslot2][2]-=dmg;
                                player1stats[pokeslot1][2]-=dmg/3;
                                break;
    }
    }
}
