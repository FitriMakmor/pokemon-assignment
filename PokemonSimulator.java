package pokemon.simulator;

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
    
public static void Moveset(int move, int pokeslot1, int pokeslot2, int pokemon1[][], int pokemon2[][],double speed, int accuracy1, int accuracy2){
    String [][] skill= new String [3][4];
    double dmg;
    switch(skill[pokeslot1][move])
    {
        case "Take Down":        dmg=damage(85-accuracy1,pokemon1[pokeslot1][0],90,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                pokemon1[pokeslot1][2]-=dmg/4;
                                break;
        case "Quick Attack":     speed+=50;
                                dmg=damage(100-accuracy1,pokemon1[pokeslot1][0],40,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "Tail Whip":        pokemon2[pokeslot2][1]-=20;
                                if(pokemon2[pokeslot2][1]<0)
                                    pokemon2[pokeslot2][1]=0;
        case "Sand Attack":
        case "Flash":           accuracy2-=15;
                                break;
        case "Hyper Beam":       speed-=100;
                                dmg=damage(90-accuracy1,pokemon1[pokeslot1][0],150,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "Mega Punch":       dmg=damage(85-accuracy1,pokemon1[pokeslot1][0],80,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "Rest":            speed-=200;
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
        
        
    }
    }
}
