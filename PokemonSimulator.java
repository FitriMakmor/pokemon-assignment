package pokemon.simulator;

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
    
public static void Moveset(int move, int pokeslot1, int pokeslot2, int pokemon1[][], int pokemon2[][], double speed1, double speed2, int accuracy1, int accuracy2, int weather, boolean state[]){
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
        case "Take Down":       dmg=damage(85-accuracy1,pokemon1[pokeslot1][0],90,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                pokemon1[pokeslot1][2]-=dmg/4;
                                break;
        case "Quick Attack":    speed1+=50;//If possible speed is applied before attack phase
                                dmg=damage(100-accuracy1,pokemon1[pokeslot1][0],40,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "Tail Whip":        pokemon2[pokeslot2][1]-=20;
                                if(pokemon2[pokeslot2][1]<0)
                                    pokemon2[pokeslot2][1]=0;
        case "Sand Attack":
        case "Flash":           accuracy2-=15;
                                break;
        case "Hyper Beam":      speed2-=100;
                                dmg=damage(90-accuracy1,pokemon1[pokeslot1][0],150,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "Mega Punch":      dmg=damage(85-accuracy1,pokemon1[pokeslot1][0],80,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "Rest":            speed1-=200;
                                pokemon1[pokeslot1][2]=160;//Only for Snorlax for now
                                break;
        case "Harden":          pokemon1[pokeslot1][1]+=15;
                                break;
        case "Tackle":          dmg=damage(100-accuracy1,pokemon1[pokeslot1][0],40,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "Recover":         pokemon1[pokeslot1][2]+=32.50;//Only for Porygon for now
                                break;
        case "Agility":         pokemon1[pokeslot1][3]+=15;
                                break;
        case "Leaf Storm":      dmg=damage(90-accuracy1,pokemon1[pokeslot1][0],130,pokemon2[pokeslot2][1],1);
                                pokemon2[pokeslot2][2]-=dmg;//Actually lowers enemy sp. attack
                                break;
        case "Leaf Blade":      dmg=damage(100-accuracy1,pokemon1[pokeslot1][0],90,pokemon2[pokeslot2][1],1);
                                pokemon2[pokeslot2][2]-=dmg;//Actually increases critical hit chance
                                break;
        case "Giga Drain":      dmg=damage(100-accuracy1,pokemon1[pokeslot1][0],75,pokemon2[pokeslot2][1],1);
                                pokemon2[pokeslot2][2]-=dmg;
                                pokemon1[pokeslot1][2]+=dmg/2;
                                break;
        case "Absorb":          dmg=damage(100-accuracy1,pokemon1[pokeslot1][0],20,pokemon2[pokeslot2][1],1);
                                pokemon2[pokeslot2][2]-=dmg;
                                pokemon1[pokeslot1][2]+=dmg;//Doubled healing for balancing purposes
                                break;
        case "Razor Leaf":      dmg=damage(95-accuracy1,pokemon1[pokeslot1][0],55,pokemon2[pokeslot2][1],1);
                                pokemon2[pokeslot2][2]-=dmg;//Actually increases critical hit chance
                                break;
        case "Bite":            dmg=damage(100-accuracy1,pokemon1[pokeslot1][0],60,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                double flinch = r.nextInt(10001);
                                flinch/=100;
                                if(flinch>=70)
                                    speed2-=100;
                                break;
        case "Magical Leaf":    dmg=damage(100,pokemon1[pokeslot1][0],60,pokemon2[pokeslot2][1],1);
                                pokemon2[pokeslot2][2]-=dmg;//Sure hit move unless airborne, underground etc.
                                break;
        case "Sunny Day":       weather=0;
                                break;
        case "Solar Beam":      if(state[0]==true)
                                {
                                    dmg=damage(100,pokemon1[pokeslot1][0],120,pokemon2[pokeslot2][1],1);
                                    pokemon2[pokeslot2][2]-=dmg;//Executes in one turn when sunny, else two turns.
                                    state[0]=false;
                                }
                                else if (weather==0){
                                    dmg=damage(100,pokemon1[pokeslot1][0],120,pokemon2[pokeslot2][1],1);
                                    pokemon2[pokeslot2][2]-=dmg;//Executes in one turn when sunny, else two turns.
                                }
                                else state[0]=true;
                                skill[pokeslot1][move]=temp;
                                temp=null;
                                break;
        case "Seed Bomb":       dmg=damage(100,pokemon1[pokeslot1][0],80,pokemon2[pokeslot2][1],1);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;        
    }
    }
}
