package pokemonsimulator;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author Fitri
 */
public class PokemonSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Random r = new Random();
        Scanner input = new Scanner(System.in);
        int pokeslot1 = 0; //which pokemon (pokemon 0,1,2)
        int pokeslot2 = 0;
        int botchoice;
        int listLength = 0;
        double player1stats[][] = new double[3][5]; //[][0]-atk [][1]-defense [][2]-hp [][3]-speed [][4]-type
        double player2stats[][] = new double[3][5];
        String pokename1[] = new String[3];
        String pokename2[] = new String[3];
        String movelist1[][] = new String[3][4];
        String movelist2[][] = new String[3][4];
        double[] speed = {0, 0};
        double[] accuracy = {0, 0};
        double[] botstartinghp = new double[3];
        int[] weather = new int[1]; //weather (raining, sunny, nice)
        boolean state1[] = {false, false, false, false, false}; //0-solarbeam,1-skullbash,2-rage,3-confused,4-burning
        boolean state2[] = {false, false, false, false, false};
        boolean bot[] = {false};
        boolean hasSwitched[] = {false, false, false};
        String[] pokemonList = new String[50];
        String temp;
        //assigning the skills

        String name;
        
        System.out.println("                                  ,'\\\n" +
"    _.----.        ____         ,'  _\\   ___    ___     ____\n" +
"_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\n" +
"\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\n" +
" \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |\n" +
"   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\n" +
"    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |\n" +
"     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\n" +
"      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\n" +
"       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\n" +
"        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\n" +
"                                `'                            '-._|");
        System.out.println("Hi Trainer, Welcome to Pokemon World!");

        System.out.println("What should I call you ? ");

        name = input.nextLine();

        System.out.println("\nHi " + name + ", Good to see you, I am Prof. Tongkat Ali!");
        System.out.println("Your very own adventure is about to unfold. Are you ready?");
        System.out.println("Let's see what you are capable of. Let's battle!\n");
        System.out.println("Before that, choose your first Pokemon.");

        //saving the names to an array for easy comparison
        try {
            int counter = 0;
            BufferedReader pokemonName = new BufferedReader(new FileReader("C:\\Users\\LEGION\\Documents\\Courses\\Fundamentals of Programming\\PokemonSimulator\\res\\Pokemon.txt"));
            do {
                if (counter % 22 == 0) {
                    pokemonList[listLength] = pokemonName.readLine();
                    listLength++;
                }
                counter++;
            } while ((pokemonName.readLine()) != null);
        } catch (FileNotFoundException e) {
            System.out.print("File was not found");
        }
        int newline = 0;
        for (int i = 0; i < listLength; i++) {
            System.out.print("[" + i + "]" + pokemonList[i] + "\t");
            newline++;
            if (newline % 6 == 0) {
                System.out.println("");
            }
        }
        System.out.println("");

        int answer = input.nextInt();
        while (answer >= listLength || answer < 0) {
            System.out.println("Invalid Pokemon, please select a pokemon in between the range given.");
            answer = input.nextInt();
        }
        pokename1[0] = pokemonList[answer];
        pokemonList[answer] = "MissingNo.";
        System.out.println("You chose " + pokename1[0] + " as your first Pokemon.\n");

        botchoice = r.nextInt(listLength);
        while (pokemonList[botchoice].equals("MissingNo.")) {
            botchoice = r.nextInt(listLength);
        }
        pokename2[0] = pokemonList[botchoice];
        pokemonList[botchoice] = "MissingNo.";
        System.out.println("My turn now... " + pokename2[0] + ", I choose you!");

        System.out.println("Which Pokemon would you like to choose next?");
        answer = input.nextInt();
        while (true) {
            if (answer > listLength || answer < 0) {
                System.out.print("Invalid Pokemon, please select a Pokemon in between the range given: ");
            } else if ("MissingNo.".equals(pokemonList[answer])) {
                System.out.print("Pokemon has already been chosen, please select a different Pokemon: ");
            } else {
                break;
            }
            answer = input.nextInt();
        }
        pokename1[1] = pokemonList[answer];
        pokemonList[answer] = "MissingNo.";
        System.out.println("You chose " + pokename1[1] + " as your second Pokemon.\n");

        botchoice = r.nextInt(listLength);
        while (pokemonList[botchoice].equals("MissingNo.")) {
            System.out.println("Oops, i picked the same pokemon");
            botchoice = r.nextInt(listLength);
        }
        pokename2[1] = pokemonList[botchoice];
        pokemonList[botchoice] = "MissingNo.";
        System.out.println("As for me, " + pokename2[1] + " is my choice!");

        System.out.println("Your turn now. Which pokemon would you add to complete your team?");
        answer = input.nextInt();
        while (true) {
            if (answer > listLength || answer < 0) {
                System.out.print("Invalid Pokemon, please select a Pokemon in between the range given: ");
            } else if ("MissingNo.".equals(pokemonList[answer])) {
                System.out.print("Pokemon has already been chosen, please select a different Pokemon: ");
            } else {
                break;
            }
            answer = input.nextInt();
        }
        pokename1[2] = pokemonList[answer];
        pokemonList[answer] = "MissingNo.";
        System.out.println("You chose " + pokename1[2] + " as your third Pokemon.\n");

        botchoice = r.nextInt(listLength);
        while (pokemonList[botchoice].equals("MissingNo.")) {
            System.out.println("Oops, i picked the same pokemon");
            botchoice = r.nextInt(listLength);
        }
        pokename2[2] = pokemonList[botchoice];
        pokemonList[botchoice] = "MissingNo.";
        System.out.println("I choose " + pokename2[2] + " as my final pokemon!\n");

        //Transferring pokemon.txt data to playerstats Array
        try {
            for (int counter = 0; counter < 3; counter++) {
                Scanner pokemonName = new Scanner(new FileReader("C:\\Users\\LEGION\\Documents\\Courses\\Fundamentals of Programming\\PokemonSimulator\\res\\Pokemon.txt"));

                while (true) {
                    if (pokemonName.nextLine().equals(pokename1[counter])) {
                        String element = pokemonName.nextLine();
                        switch (element) {
                            case "normal":
                                player1stats[counter][4] = 0;
                                break;
                            case "grass":
                                player1stats[counter][4] = 1;
                                break;
                            case "water":
                                player1stats[counter][4] = 2;
                                break;
                            case "fire":
                                player1stats[counter][4] = 3;
                                break;
                            default:
                                System.out.println("Element not found.");
                        }
                        player1stats[counter][0] = pokemonName.nextInt();
                        player1stats[counter][1] = pokemonName.nextInt();
                        player1stats[counter][2] = pokemonName.nextInt();
                        player1stats[counter][3] = pokemonName.nextInt();
                        pokemonName.nextLine();
                        movelist1[counter][0] = pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        movelist1[counter][1] = pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        movelist1[counter][2] = pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        movelist1[counter][3] = pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("File was not found");
        }

        try {
            for (int counter = 0; counter < 3; counter++) {
                Scanner pokemonName = new Scanner(new FileReader("C:\\Users\\LEGION\\Documents\\Courses\\Fundamentals of Programming\\PokemonSimulator\\res\\Pokemon.txt"));

                while (true) {
                    if (pokemonName.nextLine().equals(pokename2[counter])) {
                        String element = pokemonName.nextLine();
                        switch (element) {
                            case "normal":
                                player2stats[counter][4] = 0;
                                break;
                            case "grass":
                                player2stats[counter][4] = 1;
                                break;
                            case "water":
                                player2stats[counter][4] = 2;
                                break;
                            case "fire":
                                player2stats[counter][4] = 3;
                                break;
                            default:
                                System.out.println("Element not found.");
                        }
                        player2stats[counter][0] = pokemonName.nextInt();
                        player2stats[counter][1] = pokemonName.nextInt();
                        player2stats[counter][2] = pokemonName.nextInt();
                        player2stats[counter][3] = pokemonName.nextInt();
                        pokemonName.nextLine();
                        movelist2[counter][0] = pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        movelist2[counter][1] = pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        movelist2[counter][2] = pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        movelist2[counter][3] = pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        pokemonName.nextLine();
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("File was not found");
        }
        for (int i = 0; i < 3; i++) {
            botstartinghp[i] = player2stats[i][2];
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(pokename1[i]);
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    System.out.print("Atk: ");
                }
                if (j == 1) {
                    System.out.print("Def: ");
                }
                if (j == 2) {
                    System.out.print("HP: ");
                }
                if (j == 3) {
                    System.out.print("Spd: ");
                }
                if (j == 4) {
                    System.out.print("Type: ");
                }
                System.out.println(player1stats[i][j]);
            }
            System.out.println("");
        }

        System.out.println("");

        for (int i = 0; i < 3; i++) {
            System.out.println(pokename2[i]);
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    System.out.print("Atk: ");
                }
                if (j == 1) {
                    System.out.print("Def: ");
                }
                if (j == 2) {
                    System.out.print("HP: ");
                }
                if (j == 3) {
                    System.out.print("Spd: ");
                }
                if (j == 4) {
                    System.out.print("Type: ");
                }
                System.out.println(player2stats[i][j]);
            }
            System.out.println("");
        }

        System.out.println("Looks like we are both set and ready to go, press ENTER to start!\n");
        input.nextLine();
        String line=input.nextLine();
        if("dev".equals(line)){
            System.out.println("Dev mode activated.\n");
            player1stats[0][0]+=100000;
        }else if("doubleg".equals(line)){
            System.out.println("GG mode activated.\n");
            player2stats[0][0]+=100000;
            player2stats[1][0]+=100000;
            player2stats[2][0]+=100000;
        }
        line="";
        while (line.length() > 0) {
        }

        System.out.println("COMBAT START");
        TimeUnit.SECONDS.sleep(2);
        //weather generator
        weather[0] = r.nextInt(3); //0=nice day, neutral, 1=sunny day, 2=rainy day
        switch (weather[0]) {
            case 0:
                System.out.println("What a nice day, perfect for a Pokemon battle!");
                break;
            case 1:
                System.out.println("It's a sunny day! With this weather, keep yourselves hydrated.");
                break;
            default:
                System.out.println("It's raining! Get shelter or you'll be soaking wet!");
        }
        TimeUnit.SECONDS.sleep(2);

        do {
            CombatLoop(movelist1, movelist2, name, pokename1, pokename2, player1stats, pokeslot1, player2stats, pokeslot2, speed, accuracy, weather, state1, state2, bot, botstartinghp, hasSwitched);
        } while (((player1stats[0][2] > 0) || (player1stats[1][2] > 0) || (player1stats[2][2] > 0)) && ((player2stats[0][2] > 0) || (player2stats[1][2] > 0) || (player2stats[2][2] > 0)));

        //end of game
        System.out.println("\nCOMBAT END\n");
        if (((player1stats[0][2] <= 0) && (player1stats[1][2] <= 0) && (player1stats[2][2] <= 0))) {
            System.out.println("Woops! Apparently all 3 of your Pokemons have fainted.");
            System.out.println("What a great fight it was! Till we meet again!");
        } //player 2 loses
        else if (((player2stats[0][2] <= 0) && (player2stats[1][2] <= 0) && (player2stats[2][2] <= 0))) {
            System.out.println("Woops! Apparently all 3 of my Pokemons have fainted.");
            System.out.println("Congratulations on winning! Till we meet again!");
        }
        else System.out.println("???");
    }

    public static void CombatLoop(String movelist1[][], String movelist2[][], String name, String pokename1[], String pokename2[], double player1stats[][], int pokeslot1, double player2stats[][], int pokeslot2, double speed[], double accuracy[], int weather[], boolean state1[], boolean state2[], boolean[] bot, double[] startinghp, boolean[] hasSwitched) throws InterruptedException {

        double beforehp;
        Random r = new Random();

        double elementfaced = player1stats[0][4];
        double elementcounters;
        Scanner input = new Scanner(System.in);

        // random generator to decide who goes first
        int DecideFirstTurn = r.nextInt(2);
        // if 1, player 2's counter starts first
        if (DecideFirstTurn == 1) {
            System.out.println("Prof. Tongkat Ali gets to start first!");
            speed[1] += player2stats[0][3];
            System.out.println(pokename2[0] + "'s Speed -> " + speed[1]);
            TimeUnit.SECONDS.sleep(1);
        } else {
            System.out.println(name + " gets to start first!");
        }
        boolean playerturn = true;
        do {
            //Speed Counter loop
            do {
                if (playerturn == true) {
                    speed[0] += player1stats[0][3];
                    System.out.println(pokename1[0] + "'s Speed -> " + speed[0]);
                    playerturn = false;
                    TimeUnit.SECONDS.sleep(1);
                    if (speed[0] >= 100) {
                        break;
                    }
                }
                speed[1] += player2stats[0][3];
                System.out.println(pokename2[0] + "'s Speed -> " + speed[1]);
                playerturn = true;
                TimeUnit.SECONDS.sleep(1);
            } while ((speed[0] < 100) && (speed[1] < 100));

            //deduct speed, do damage
            if (speed[0] >= 100) {
                speed[0] -= 100;
                System.out.println("\n" + name + " attacks!");
                //prompt user to choose skill
                System.out.println("Which move would you like to use?");
                System.out.println("[0] " + movelist1[0][0]);   //insert the Move array
                System.out.println("[1] " + movelist1[0][1]);
                System.out.println("[2] " + movelist1[0][2]);
                System.out.println("[3] " + movelist1[0][3]);
                System.out.println("[4] Switch Pokemon");
                /*
                display moves available to select
                 */

                int MoveSelection = input.nextInt();
                if (MoveSelection == 4) {
                    switchPokemon(pokename1, pokeslot1, player1stats, movelist1, speed, state1);
                } else {
                    beforehp = player2stats[0][2];
                    Moveset(pokename1, movelist1, MoveSelection, pokeslot1, pokeslot2, player1stats, player2stats, speed, accuracy, weather, state1, state2, bot);
                    System.out.printf("It dealt %.2f damage!\n", (beforehp - player2stats[0][2]));
                    TimeUnit.SECONDS.sleep(1);
                }
            } else {
                speed[1] -= 100;
                System.out.println("\nProf. Tongkat Ali attacks!");
                int MoveSelection = r.nextInt(4);
                bot[0] = true;
                beforehp = player1stats[0][2];
                Moveset(pokename2, movelist2, MoveSelection, pokeslot2, pokeslot1, player2stats, player1stats, speed, accuracy, weather, state2, state1, bot);
                System.out.printf("It dealt %.2f damage!\n", (beforehp - player1stats[0][2]));
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.printf("%s HP: %.2f\n", pokename1[0], player1stats[0][2]);
            System.out.printf("%s HP: %.2f\n", pokename2[0], player2stats[0][2]);
            if (elementfaced == 1) {
                elementcounters = 2;
            } else if (elementfaced == 2) {
                elementcounters = 3;
            } else if (elementfaced == 3) {
                elementcounters = 1;
            } else {
                elementcounters = 5; //will not be in the range and therefore makes next statement false
            }

            if (player1stats[pokeslot1][2] <= 0 && (player1stats[1][2] > 0 || player1stats[2][2] > 0)) {
                switchPokemon(pokename1, pokeslot1, player1stats, movelist1, speed, state1);
            } //player 2's pokemon faints
            if (player2stats[pokeslot2][2] <= 0 && (player2stats[1][2] > 0 || player2stats[2][2] > 0)) {
                switchPokemonBot(pokename2, pokeslot2, player2stats, player1stats, movelist2, speed, hasSwitched, state2);
            } else if (player2stats[pokeslot2][2] <= (3 / 20) * startinghp[pokeslot2] && hasSwitched[0] == false && (player2stats[1][2] > 0 || player2stats[2][2] > 0)) {
                hasSwitched[0] = true;
                switchPokemonBot(pokename2, pokeslot2, player2stats, player1stats, movelist2, speed, hasSwitched, state2);
            } else if (player1stats[0][4] == elementfaced && player2stats[0][4] == elementcounters && (player2stats[0][4] != player2stats[1][4] && player2stats[0][4] != player2stats[2][4]) && (player2stats[1][2] > 0 || player2stats[2][2] > 0)) {
                int willSwitch = r.nextInt(10001);
                willSwitch /= 100;
                if (willSwitch >= 85) {
                    switchPokemonBot(pokename2, pokeslot2, player2stats, player1stats, movelist2, speed, hasSwitched, state2);
                }
            } else;
        } while ((player1stats[pokeslot1][2] > 0) && (player2stats[pokeslot2][2] > 0));
    }

    public static void Moveset(String pokename[], String[][] skill, int move, int pokeslot1, int pokeslot2, double[][] attackerstats, double[][] targetstats, double[] speed, double[] accuracy, int[] weather, boolean[] state1, boolean[] state2, boolean[] bot) {

        if (bot[0] == true) {
            double temp1 = speed[0];
            speed[0] = speed[1];
            speed[1] = temp1;
            temp1 = accuracy[0];
            accuracy[0] = accuracy[1];
            accuracy[1] = temp1;
        }
        double dmg;
        double flinch;
        double debuffacc;
        double confused;
        double burn;
        Random r = new Random();
        String temp = "";
        if (state1[0] == true)//solarbeam
        {
            temp = skill[pokeslot1][move];
            skill[pokeslot1][move] = "Solar Beam";
        }
        if (state1[1] == true)//skullbash
        {
            temp = skill[pokeslot1][move];
            skill[pokeslot1][move] = "Skull Bash";
        }
        if (state1[4] == true) {
            System.out.println(pokename[0] + " is burning!");
            attackerstats[pokeslot1][2] -= 2; //inflicts burn true damage and removes status
            System.out.printf("%s HP: %.2f\n", pokename[0], attackerstats[pokeslot1][2]);
            state1[4] = false;
        }
        state1[2] = false;//removes rage
        System.out.println(pokename[0] + " used " + skill[pokeslot1][move] + "!");
        switch (skill[pokeslot1][move]) {
            case "Take Down":
                dmg = damage(85 - accuracy[0], attackerstats[pokeslot1][0], 90, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }
                attackerstats[pokeslot1][2] -= dmg / 4;
                System.out.printf("%s hurt itself in the process with %.2f damage!\n", pokename[0], dmg / 4);

                break;
            case "Quick Attack":
                speed[0] += 50;//If possible speed is applied before attack phase
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 40, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Tail Whip":
                targetstats[pokeslot2][1] -= 20;
                if (targetstats[pokeslot2][1] < 0)//Apply this to every stat!
                {
                    targetstats[pokeslot2][1] = 0;
                }
                break;
            case "Sand Attack":
            case "Flash":
                accuracy[1] -= 15;
                break;
            case "Hyper Beam":
                speed[0] -= 50;
                dmg = damage(60 - accuracy[0], attackerstats[pokeslot1][0], 150, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Mega Punch":
                dmg = damage(85 - accuracy[0], attackerstats[pokeslot1][0], 80, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Rest":
                speed[0] -= 200;
                attackerstats[pokeslot1][2] += 20;//Only for Snorlax for now
                if (attackerstats[pokeslot1][2] > 160) {
                    attackerstats[pokeslot1][2] = 160;
                }
                break;
            case "Harden":
                attackerstats[pokeslot1][1] += 15;
                System.out.println(pokename[0] + "'s Defense increased!");
                break;
            case "Tackle":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 40, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Recover":
                attackerstats[pokeslot1][2] += 32.50;//Only for Porygon for now
                break;
            case "Agility":
                attackerstats[pokeslot1][3] += 15;
                System.out.println(pokename[0] + "'s Agility increased!");
                break;
            case "Leaf Storm":
                dmg = damage(60 - accuracy[0], attackerstats[pokeslot1][0], 130, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);//Actually lowers enemy sp. attack
                }

                break;
            case "Leaf Blade":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 90, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);//Actually increases critical hit chance
                }

                break;
            case "Giga Drain":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 75, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }
                attackerstats[pokeslot1][2] += dmg / 2;

                break;
            case "Absorb":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 20, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }
                attackerstats[pokeslot1][2] += dmg;//Doubled healing for balancing purposes
                System.out.printf("%s healed itself for %.2f damage!", pokename[0], dmg);

                break;
            case "Razor Leaf":
                dmg = damage(65 - accuracy[0], attackerstats[pokeslot1][0], 55, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);//Actually increases critical hit chance
                }

                break;
            case "Bite":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 60, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                flinch = r.nextInt(10001);
                flinch /= 100;
                if (flinch >= 70) {
                    speed[1] -= 100;
                    System.out.println("The target flinched with that attack!");
                }
                break;
            case "Magical Leaf":
                dmg = damage(100, attackerstats[pokeslot1][0], 60, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);//Sure hit move unless airborne, underground etc.
                }

                break;
            case "Sunny Day":
                weather[0] = 0;
                System.out.println("The sunlight got bright!");
                break;
            case "Solar Beam":
                if (state1[0] == true) {
                    dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 120, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                    if (state1[3] == true) {
                        System.out.println(pokename[0] + " is confused!");
                        System.out.println("It hit itself in the confusion!");
                        attackerstats[pokeslot1][2] -= dmg;
                        state1[3] = false;
                        break;
                    } else {
                        targetstats[pokeslot2][2] -= dmg;
                        rage(state2, targetstats, pokeslot2);//Executes in one turn when sunny, else two turns.
                    }

                    skill[pokeslot1][move] = temp;
                    temp = "MissingNo.";
                    state1[0] = false;
                } else if (weather[0] == 0) {
                    dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 120, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                    if (state1[3] == true) {
                        System.out.println(pokename[0] + " is confused!");
                        System.out.println("It hit itself in the confusion!");
                        attackerstats[pokeslot1][2] -= dmg;
                        state1[3] = false;
                        break;
                    } else {
                        targetstats[pokeslot2][2] -= dmg;
                        rage(state2, targetstats, pokeslot2);//Executes in one turn when sunny, else two turns.
                    }

                } else {
                    state1[0] = true;
                }
                break;
            case "Seed Bomb":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 80, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Vine Whip":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 45, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                targetstats[pokeslot2][2] -= dmg;
                rage(state2, targetstats, pokeslot2);

                break;
            case "Leaf Tornado":
                dmg = damage(60 - accuracy[0], attackerstats[pokeslot1][0], 65, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 1, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                debuffacc = r.nextInt(10001);
                debuffacc /= 100;
                if (debuffacc >= 70) {
                    accuracy[1] -= 10;
                    System.out.println("Enemy's accuracy has been lowered!");
                }
                break;
            case "Growth":
                attackerstats[pokeslot1][0] += 20;//Actually increases sp.attack as well
                System.out.println(pokename[0] + "'s Attack increases!");
                break;
            case "Ember":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 40, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 3, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Flare Blitz":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 120, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 3, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }
                attackerstats[pokeslot1][2] -= dmg / 3;
                System.out.printf("%s hurt itself in the process with %.2f damage!\n", pokename[0], dmg / 3);
                burn = r.nextInt(10001);
                burn /= 100;
                if (burn >= 90) {
                    state2[4] = true;
                    System.out.println("The attack caused the target to burn!");
                }

                break;
            case "Scratch":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 40, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Slash":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 70, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);//Actually increases critical hit chance
                }

                break;
            case "Double-Edge":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 120, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }
                attackerstats[pokeslot1][2] -= dmg / 3;
                System.out.printf("%s hurt itself in the process with %.2f damage!\n", pokename[0], dmg / 3);

                break;
            case "Blaze Kick":
                dmg = damage(60 - accuracy[0], attackerstats[pokeslot1][0], 85, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 3, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }
                burn = r.nextInt(10001);
                burn /= 100;
                if (burn >= 90) {
                    state1[4] = true;
                    System.out.println("The attack caused the target to burn!");
                }

                break;
            case "Flame Charge":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 50, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 3, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                attackerstats[pokeslot1][3] += 15;
                break;
            case "Fire Fang":
                dmg = damage(65 - accuracy[0], attackerstats[pokeslot1][0], 65, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 3, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                burn = r.nextInt(10001);
                burn /= 100;
                if (burn >= 90) {
                    state2[4] = true;
                    System.out.println("The attack caused the target to burn!");
                }
                flinch = r.nextInt(10001);
                flinch /= 100;
                if (flinch >= 90) {
                    speed[1] -= 100;
                    System.out.println("The target flinched with that attack!"); //Actually only applies when opponent has not moved
                }
                break;
            case "Bubble Beam":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 65, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 2, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                double debuffspd = r.nextInt(10001);
                debuffspd /= 100;
                if (debuffspd >= 90) {
                    targetstats[pokeslot2][3] -= 10;
                    System.out.println("Enemy's speed lowered!");
                }
                break;
            case "Water Gun":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 40, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 2, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Skull Bash":
                if (state1[1] == true) {
                    dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 120, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                    if (state1[3] == true) {
                        System.out.println(pokename[0] + " is confused!");
                        System.out.println("It hit itself in the confusion!");
                        attackerstats[pokeslot1][2] -= dmg;
                        state1[3] = false;
                        break;
                    } else {
                        targetstats[pokeslot2][2] -= dmg;
                        rage(state2, targetstats, pokeslot2);//Executes in two turns.
                    }

                    skill[pokeslot1][move] = temp;
                    temp = "MissingNo.";
                    state1[0] = false;
                } else {
                    System.out.println("Squirtle tucked in its head!"); //If possible, insert variable name instead
                    state1[1] = true;
                }
                break;
            case "Hydro Pump":
                dmg = damage(50 - accuracy[0], attackerstats[pokeslot1][0], 110, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 2, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Rage":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 20, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                state1[2] = true;
                break;
            case "Rain Dance":
                weather[0] = 1;
                System.out.println("It started to rain!");
                break;
            case "Water Pulse":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 60, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 2, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                confused = r.nextInt(10001);
                confused /= 100;
                if (confused >= 80) {
                    System.out.println("Opponent is now confused!");
                    state2[3] = true;
                }
                break;
            case "Body Slam":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 60, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                flinch = r.nextInt(10001);
                flinch /= 100;
                if (flinch >= 70) {
                    speed[1] -= 110;
                    System.out.println("The target is paralyzed with that attack!"); //borrows flinch variable
                }
                break;
            case "Flamethrower":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 90, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 3, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }
                burn = r.nextInt(10001);
                burn /= 100;
                if (burn >= 90) {
                    state2[4] = true;
                    System.out.println("The attack caused the target to burn!");
                }

                break;
            case "Swift":
                dmg = damage(100, attackerstats[pokeslot1][0], 60, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Aqua Tail":
                dmg = damage(60 - accuracy[0], attackerstats[pokeslot1][0], 90, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 2, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Cut":
                dmg = damage(65 - accuracy[0], attackerstats[pokeslot1][0], 50, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Facade":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 70, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true || state1[4] == true) {
                    targetstats[pokeslot2][2] -= 2 * dmg;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Pound":
                dmg = damage(65 - accuracy[0], attackerstats[pokeslot1][0], 50, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
            case "Bubble":
                dmg = damage(70 - accuracy[0], attackerstats[pokeslot1][0], 40, targetstats[pokeslot2][1], targetstats[pokeslot2][4], 0, state1);
                if (state1[3] == true) {
                    System.out.println(pokename[0] + " is confused!");
                    System.out.println("It hit itself in the confusion!");
                    attackerstats[pokeslot1][2] -= dmg;
                    state1[3] = false;
                    break;
                } else {
                    targetstats[pokeslot2][2] -= dmg;
                    rage(state2, targetstats, pokeslot2);
                }

                break;
        }

        if (bot[0] == true) {
            double temp1 = speed[0];
            speed[0] = speed[1];
            speed[1] = temp1;
            temp1 = accuracy[0];
            accuracy[0] = accuracy[1];
            accuracy[1] = temp1;
            bot[0] = false;
        }
    }

    public static void rage(boolean[] state2, double player2stats[][], int pokeslot2) {
        if (state2[2] == true) {
            player2stats[pokeslot2][0] += 10;
            System.out.println("Poliwhirl's rage is building!");
        }
    }

    public static double damage(double accuracy, double attack, double power, double defense, double type_enemy, int type, boolean[] state) {
//0 normal, 1 grass, 2 water, 3 fire
        Random r = new Random();
        double hit = r.nextInt(10001);
        hit /= 100;
        if (hit <= accuracy && state[3] == false) {
            if (type_enemy == 1 && type == 3) //fire attack grass
            {
                System.out.println("It's super effective!");
                return (((attack * power / defense) / 20) + 2) * 2;
            } else if (type_enemy == 2 && type == 3) //fire attack water
            {
                System.out.println("It's not very effective....");
                return (((attack * power / defense) / 20) + 2) / 2;
            } else if (type_enemy == 3 && type == 1) //grass attack fire
            {
                System.out.println("It's not very effective...");
                return (((attack * power / defense) / 20) + 2) / 2;
            } else if (type_enemy == 2 && type == 1) //grass attack water
            {
                System.out.println("It's super effective!");
                return (((attack * power / defense) / 20) + 2) * 2;
            } else if (type_enemy == 3 && type == 2) //water attack fire
            {
                System.out.println("It's super effective");
                return (((attack * power / defense) / 20) + 2) * 2;
            } else if (type_enemy == 1 && type == 2) //water attack grass
            {
                System.out.println("It's not very effective...");
                return (((attack * power / defense) / 20) + 2) / 2;
            } else {
                return (((attack * power / defense) / 20) + 2);
            }
        } else {
            System.out.println("But it missed!");
            return 0;
        }
    }

    public static void switchPokemon(String pokename1[], int pokeslot1, double player1stats[][], String movelist1[][], double speed[], boolean state[]) {
        Scanner input = new Scanner(System.in);
        String temp;
        double temp2;
        boolean temp3;
        System.out.println("Which Pokemon would you like to switch to?");
        System.out.println("[1]" + pokename1[1] + "\t[2]" + pokename1[2] + "\n");
        int select = input.nextInt();
        do {
            pokeslot1 = select;
            if (select > 2 || select < 1) {
                System.out.println("Invalid Pokemon, select either 1 or 2.");
                select = input.nextInt();
            } else if (player1stats[pokeslot1][2] < 0) {
                System.out.println("That pokemon has fainted, choose another pokemon!");
                select = input.nextInt();
            } else {
                break;
            }
        } while (true);
        System.out.println("You have chosen " + pokename1[select] + "!");
        temp = pokename1[select];
        pokename1[select] = pokename1[0];
        pokename1[0] = temp;
        for (int i = 0; i < 5; i++) {
            temp2 = player1stats[0][i];
            player1stats[0][i] = player1stats[select][i];
            player1stats[select][i] = temp2;
        }
        for (int i = 0; i < 4; i++) {
            temp = movelist1[0][i];
            movelist1[0][i] = movelist1[select][i];
            movelist1[select][i] = temp;
        }
        for (int i = 0; i < 3; i++) {
            temp3 = state[i];
            state[i]=state[select];
            state[select]=temp3;
        }
        speed[0] = 0;
    }

    public static void switchPokemonBot(String[] pokename, int pokeslot, double[][] botstats, double[][] ourstats, String[][] movelist, double[] speed, boolean hasSwitched[], boolean state[]) {
        Random r = new Random();
        String temp;
        double temp2, counterelement;
        boolean temp3;
        int select;
        double elementfaced = ourstats[pokeslot][4];
        if (elementfaced == 1) {
            counterelement = 3;
        } else if (elementfaced == 2) {
            counterelement = 1;
        } else if (elementfaced == 3) {
            counterelement = 2;
        } else {
            counterelement = 5; //will not be in the range and therefore makes next statement false
        }
        if ((botstats[1][4] == counterelement && botstats[1][2] > 0) && (botstats[2][4] == counterelement && botstats[2][2] > 0)) {
            select = r.nextInt(2) + 1;
        } else if (botstats[1][4] == counterelement && botstats[1][2] > 0) {
            select = 1;
        } else if (botstats[1][4] == counterelement && botstats[1][2] > 0) {
            select = 2;
        } else if ((botstats[1][4] == elementfaced || botstats[1][4] == 0) && botstats[1][2] > 0) {
            select = 1;
        } else if ((botstats[2][4] == elementfaced || botstats[2][4] == 0) && botstats[2][2] > 0) {
            select = 2;
        } else {
            select = r.nextInt(2) + 1;
        }

        do {
            pokeslot = select;
            if (botstats[pokeslot][2] < 0) {
                System.out.println("Oops, that pokemon has fainted, I shall choose another pokemon!");
                select = r.nextInt(2) + 1;
            } else {
                break;
            }
        } while (true);
        
        System.out.println("I switch my Pokemon, " + pokename[select] + ", I choose you!");
        temp = pokename[select];
        pokename[select] = pokename[0];
        pokename[0] = temp;
        for (int i = 0; i < 5; i++) {
            temp2 = botstats[0][i];
            botstats[0][i] = botstats[select][i];
            botstats[select][i] = temp2;
        }
        for (int i = 0; i < 4; i++) {
            temp = movelist[0][i];
            movelist[0][i] = movelist[select][i];
            movelist[select][i] = temp;
        }
        for (int i = 0; i < 3; i++) {
            temp3 = state[i];
            state[i]=state[select];
            state[select]=temp3;
        }

        temp3 = hasSwitched[0];
        hasSwitched[0] = hasSwitched[select];
        hasSwitched[select] = temp3;

        speed[1] = 0;
    }

    public static void music() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try {
            BGM = new AudioStream(new FileInputStream("/res/music.wav"));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
        } catch (IOException e) {
            System.out.println("cant find the file");
        }

        MGP.start(loop);
    }
}
