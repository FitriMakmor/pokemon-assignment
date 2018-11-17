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
    
public static void Moveset(int pokeslot1, int pokeslot2, int pokemon1[][], int pokemon2[][],double speed, int accuracy1, int accuracy2){
    String [][] skill= new String [3][4];
    double dmg;
    switch(skill[pokeslot1][pokeslot1])
    {
        case "takeDown":        dmg=damage(85-accuracy1,pokemon1[pokeslot1][0],90,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                pokemon1[pokeslot1][2]-=dmg/4;
                                break;
        case "quickAttack":     speed+=50;
                                dmg=damage(100-accuracy1,pokemon1[pokeslot1][0],40,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "tailWhip":        pokemon2[pokeslot2][1]-=20;
                                if(pokemon2[pokeslot2][1]<0)
                                    pokemon2[pokeslot2][1]=0;
        case "sandAttack":
        case "flash":           accuracy2-=15;
                                break;
        case "hyperBeam":       speed-=100;
                                dmg=damage(90-accuracy1,pokemon1[pokeslot1][0],150,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "megaPunch":       dmg=damage(85-accuracy1,pokemon1[pokeslot1][0],80,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "rest":            speed-=200;
                                pokemon1[pokeslot1][2]=160;//Only for Snorlax for now
                                break;
        case "harden":          pokemon1[pokeslot1][1]+=15;
                                break;
        case "tackle":          dmg=damage(100-accuracy1,pokemon1[pokeslot1][0],40,pokemon2[pokeslot2][1],0);
                                pokemon2[pokeslot2][2]-=dmg;
                                break;
        case "recover":         pokemon1[pokeslot1][2]+=32.50;//Only for Porygon for now
                                break;
        case "agility":         pokemon1[pokeslot1][3]+=15;
                                break;
        
    }
    }
}
