import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;

public class Game_Logic {

    public Game_Logic() {
        Game_Objects.room.add(new Room(0));

        ArrayList<String> roomInfo = new ArrayList<>();
        try {
            roomInfo = readLines("TextFile/roomdescriptions.txt");
        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < roomInfo.size(); i++) {
            String[] firstWord = roomInfo.get(i).split(" ");
            String[] everyThingElse = roomInfo.get(i).split(":");

            if (firstWord[0].equals("RoomName:")) {
                int currentRoomSize = Game_Objects.room.size();
                Game_Objects.room.add(new Room(currentRoomSize));
                Game_Objects.room.get(Game_Objects.room.size() - 1).name = everyThingElse[1];
                Game_Objects.room.get(Game_Objects.room.size() - 1).number = currentRoomSize;

                int roomCount = 0;
                for (int k = 0; k < roomInfo.size(); k++) {
                    String[] nextFirstWord = roomInfo.get(k).split(" ");
                    if (nextFirstWord[0].equals("RoomName:")) {
                        roomCount++;
                    }
                    if (roomCount == currentRoomSize) {
                        if (nextFirstWord[0].equals("Desc:")) {
                            String[] nextEverythingElse = roomInfo.get(k).split(":");
                            Game_Objects.room.get(Game_Objects.room.size() - 1).desc.add(nextEverythingElse[1]);
                        }
                    }
                }
                roomCount = 0;
                for (int k = 0; k < roomInfo.size(); k++) {
                    String[] nextFirstWord = roomInfo.get(k).split(" ");
                    if (nextFirstWord[0].equals("RoomName:")) {
                        roomCount++;
                    }
                    if (roomCount == currentRoomSize) {
                        if (nextFirstWord[0].equals("Exit:")) {
                            String[] nextEverythingElse = roomInfo.get(k).split(":");
                            Game_Objects.room.get(Game_Objects.room.size() - 1).exits.add(nextEverythingElse[1]);
                        }
                    }
                }
            }
        }
    }

    public ArrayList<String> readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines;
    }

    public void waitForCommand() {
        if (Game_Objects.playerCharacter.inRoom == 0) {
            System.out.println("You push the door open, wind howling at your back.");
            System.out.println("You had been travelling for a fortnight, your home long behind you by now.");

            newGame();
            System.out.println(")(~-~-~-~-~-~-~-~-~-~)(");

            System.out.println("You are jerked from your slumber, lightning crashing through the sky above. Rain thunders down on the roof of your room.");
            System.out.println("Your heart races, and you spend several moments calming yourself, thankful you were able to find an inn before the storm was upon you.");
            System.out.println("The storm rages on, and you eventually return to a fitful slumber.");
            System.out.println("You rouse from your slumber as dawn breaks, and make your way down to the main room of the Inn.");

            Game_Objects.playerCharacter.inRoom = 1;

        }

        GameLoop:
        if (Game_Objects.playerCharacter.currentHitPoints > 0) {
            //  hostileNPCCheck();

            System.out.println("What action do you take?");
            Game_Objects.playerCharacter.setRations();
            Scanner sc = new Scanner(System.in);
            String com = sc.nextLine();
            //parse the command by spaces
            //read each word into an array valueString
            String[] words = com.split(" ");
            processCommand(words);
            System.out.println(")(~-~-~-~-~-~-~-~-~-~)(");

            if (Game_Objects.playerCharacter.currentHitPoints <= 0) {
                System.out.println("You succumb to your wounds and drift out of this world...");
                System.out.println("GAME OVER");
                break GameLoop;
            }
        }
    }
    public void worldItem() {
        //only spawns once in world

        //Items
        Game_Objects.playerCharacter.itemsCarried.add(new Rations());
        Game_Objects.playerCharacter.itemsCarried.add(new Rations());
        Game_Objects.room.get(2).item.add(new ShortSword());

        //Enemies
        Game_Objects.room.get(25).npc.add(new KoboldChieftan());
    }


   /* public void hostileNPCCheck() {
        hostileNPCCheckLoop:
        for (int i = 0; i < Game_Objects.room.size(); i++) {
            for (int j = 0; j < Game_Objects.room.get(i).npc.size(); j++) {
                Game_Objects.room.get(i).npc.get(j).hostileNPC();
            }
            break hostileNPCCheckLoop;
        }
    }*/

    public void processCommand(String[] x) {
        if (x[0].equalsIgnoreCase("look")) {
            look(x);
        }
        if (x[0].equalsIgnoreCase("summon")) {
            summon(x);
        }
        if (x[0].equalsIgnoreCase("create")) {
            createItem(x);
        }
        if (x[0].equalsIgnoreCase("get")) {
            get(x);
        }
        if (x[0].equalsIgnoreCase("bleed")) {
            bleed(x);
        }
        if (x[0].equalsIgnoreCase("wear")) {
            Game_Objects.playerCharacter.wear(x);
        }
        if (x[0].equalsIgnoreCase("equipment")) {
            Game_Objects.playerCharacter.equipment();
        }
        if (x[0].equalsIgnoreCase("remove")) {
            Game_Objects.playerCharacter.remove(x);
        }
        if (x[0].equalsIgnoreCase("move")) {
            move(x);
        }
        if (x[0].equalsIgnoreCase("attack")) {
            Game_Objects.combat.attack(x);
        }
        if (x[0].equalsIgnoreCase("wield")) {
            Game_Objects.playerCharacter.wear(x);
        }
        if (x[0].equalsIgnoreCase("equip")) {
            Game_Objects.playerCharacter.wear(x);
        }
        if (x[0].equalsIgnoreCase("interact")) {
            interact(x);
        }
        if (x[0].equalsIgnoreCase("speak")) {
            speak(x);
        }
        if (x[0].equalsIgnoreCase("eat")) {
            eat(x);
        }
        if (x[0].equalsIgnoreCase("commands")) {
            System.out.println("Look, Get, Equip, Interact, Speak, Eat, Attack, Move");
        }
    }

    public void interact(String[] x) {
        if (x.length == 1) {
            System.out.println("What would you like to interact with?");
        }
        if (x.length == 2) {
            interactLoop:
            for (int i = 0; i < Game_Objects.Interactables.size(); i++) {
                for (int j = 0; j < Game_Objects.room.size(); j++) {
                    if (Game_Objects.room.get(j).number == Game_Objects.playerCharacter.inRoom) {
                        for (int k = 0; k < Game_Objects.room.get(j).interactables.size(); k++) {
                            if (x[1].equalsIgnoreCase(Game_Objects.room.get(j).interactables.get(k).id)) {

                                Game_Objects.room.get(j).interactables.get(k).interact();
                                break interactLoop;
                            }
                        }
                    }
                }
            }
        }
    }


    public void move(String[] x) {
        if (x.length == 1) {
            System.out.println("Where would you like to move to?");
        }
        if (x.length == 2) {
            moveLoop:
            for (int i = 0; i < Game_Objects.room.size(); i++) {
                if (Game_Objects.room.get(i).number == Game_Objects.playerCharacter.inRoom) {
                    for (int j = 0; j < Game_Objects.room.get(i).exits.size(); j++) {
                        String exitRequested = Game_Objects.room.get(i).exits.get(j);
                        String[] exitArray = exitRequested.split(" ");
                        if (x[1].equalsIgnoreCase(exitArray[1])) {
                            Game_Objects.playerCharacter.inRoom = Integer.parseInt(exitArray[2]);
                            System.out.println("You leave " + exitArray[1]);
                            String[] badProgramming = new String[1];
                            badProgramming[0] = "nada";
                            look(badProgramming);
                            break moveLoop;
                        }
                    }
                }
            }
        }
    }

    public void eat(String[] x) {
        if (x.length == 1) {
            System.out.println("What would you like to eat?");
        }

        if (x.length == 2) {
            if (x[1].equalsIgnoreCase("Rations")) {
                if (Game_Objects.playerCharacter.rations > 0) {
                System.out.println("You eat some rations.");
                    Game_Objects.playerCharacter.rations--;
                    eatRationsLoop:
                    for (int a = 0; a < Game_Objects.playerCharacter.itemsCarried.size(); a++) {
                        Game_Objects.playerCharacter.itemsCarried.remove(a);
                        break eatRationsLoop;
                    }
                int rationsHeal = Math.round(Game_Objects.playerCharacter.maxHitPoints / 10);
                if (Game_Objects.playerCharacter.currentHitPoints < Game_Objects.playerCharacter.maxHitPoints) {
                    Game_Objects.playerCharacter.currentHitPoints += rationsHeal;

                    if (Game_Objects.playerCharacter.currentHitPoints > Game_Objects.playerCharacter.maxHitPoints) {
                        Game_Objects.playerCharacter.currentHitPoints = Game_Objects.playerCharacter.maxHitPoints;

                        }
                    }
                } else {
                    System.out.println("You don't have any rations...");
                }
            }
        }
    }

    public void bleed(String[] x) {
        if (x.length == 1) {
            System.out.println("No, no. Come on " + Game_Objects.playerCharacter.name + " do something other than bleed.");
        }
    }

        public void look(String[] x) {

            if (x.length == 1) {
                for (int i = 0; i < Game_Objects.room.size(); i++) {
                    if (Game_Objects.room.get(i).number == Game_Objects.playerCharacter.inRoom) {
                        System.out.println(Game_Objects.room.get(i).name);

                        for (int j = 0; j < Game_Objects.room.get(i).desc.size(); j++) {
                            System.out.println(Game_Objects.room.get(i).desc.get(j));

                        }

                        for (int j = 0; j < Game_Objects.room.get(i).npc.size(); j++) {
                            System.out.println(Game_Objects.room.get(i).npc.get(j).desc);

                        }
                        lookItemLoop:
                        for (int j = 0; j < Game_Objects.room.get(i).item.size(); j++) {
                            System.out.println(Game_Objects.room.get(i).item.get(j).desc);
                            break lookItemLoop;
                        }

                        for (int j = 0; j < Game_Objects.room.get(i).interactables.size(); j++) {
                            System.out.println(Game_Objects.room.get(i).interactables.get(j).desc);

                        }
                    }
                }
            }
            if (x.length == 2) {
                if (x[1].equals("self")) {
                    Game_Objects.playerCharacter.look();
                    System.out.println("You are carrying:");
                    int koboldTally = 0;
                    int goblinEar = 0;
                    for (int i = 0; i < Game_Objects.playerCharacter.itemsCarried.size(); i++) {

                        if (!Game_Objects.playerCharacter.itemsCarried.get(i).id.equals("Rations") && !Game_Objects.playerCharacter.itemsCarried.get(i).collectible) {
                            System.out.println(Game_Objects.playerCharacter.itemsCarried.get(i).itemName);
                        }

                        if (Game_Objects.playerCharacter.itemsCarried.get(i).itemName.equalsIgnoreCase("koboldTalisman")){
                            koboldTally++;

                        }

                        if (Game_Objects.playerCharacter.itemsCarried.get(i).itemName.equalsIgnoreCase("GoblinEar")) {
                            goblinEar++;

                        }

                    }

                    System.out.println("Kobold Talisman x " + koboldTally);

                    System.out.println("Goblin Ear x " + goblinEar);

                    System.out.println("You have " + Game_Objects.playerCharacter.rations + " rations.");
                }
                for (int j = 0; j < Game_Objects.room.size(); j++) {

                    if (Game_Objects.room.get(j).number == Game_Objects.playerCharacter.inRoom) {

                        for (int i = 0; i < Game_Objects.room.get(j).npc.size(); i++) {

                            if (x[1].equalsIgnoreCase(Game_Objects.room.get(j).npc.get(i).id)) {
                                Game_Objects.room.get(j).npc.get(i).look();
                            }
                        }
                    }
                }
                lookInteractableLoop:
                for (int j = 0; j < Game_Objects.room.size(); j++) {

                    if (Game_Objects.room.get(j).number == Game_Objects.playerCharacter.inRoom) {

                        for (int i = 0; i < Game_Objects.room.get(j).interactables.size(); i++) {

                            if (x[1].equalsIgnoreCase(Game_Objects.room.get(j).interactables.get(i).id)) {
                                Game_Objects.room.get(j).interactables.get(i).look();
                                break lookInteractableLoop;
                            }
                        }
                    }
                }
            }
        }


        public void speak(String[] x) {
        if (x.length == 1) {
            System.out.println("Who would you like to speak to?");
        }
        if (x.length == 2) {
            speakLoop:
            for (int j = 0; j < Game_Objects.room.size(); j++) {

                if (Game_Objects.room.get(j).number == Game_Objects.playerCharacter.inRoom) {

                    for (int i = 0; i < Game_Objects.room.get(j).interactables.size(); i++) {

                        if (x[1].equalsIgnoreCase(Game_Objects.room.get(j).interactables.get(i).id)) {
                            Game_Objects.room.get(j).interactables.get(i).speak();
                            break speakLoop;
                        }
                    }
                }
            }
        }
    }

         public void get(String[] x) {
        if (x.length == 1) {
            System.out.println("What would you like to get?");
        }
        if (x.length == 2) {
            getLoop:
            for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {
                for (int j = 0; j < Game_Objects.room.size(); j++) {
                    if (Game_Objects.room.get(j).number == Game_Objects.playerCharacter.inRoom) {
                        for (int k = 0; k < Game_Objects.room.get(j).item.size(); k++) {
                            if (x[1].equalsIgnoreCase(Game_Objects.room.get(j).item.get(k).id)) {
                                Item localItem = Game_Objects.room.get(j).item.get(k);

                                            Game_Objects.playerCharacter.itemsCarried.add(localItem);
                                            System.out.println("You pick up a " + localItem.itemName);
                                            Game_Objects.room.get(j).item.remove(k);
                                            break getLoop;
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
        public void summon(String[] x) {
        if (x.length == 1) {
            System.out.println("What were you trying to summon?");
        }
        if (x.length == 2) {
            for (int i = 0; i < Game_Objects.NPCdataBase.size(); i++) {
                NPC localNPC = Game_Objects.NPCdataBase.get(i);
                if (localNPC.id.equalsIgnoreCase(x[1])) {
                   for (int j = 0; j < Game_Objects.room.size(); j++) {
                        if (Game_Objects.room.get(j).number == Game_Objects.playerCharacter.inRoom) {
                            try {
                                Game_Objects.room.get(j).npc.add((NPC) Class.forName(localNPC.id).newInstance());
                                System.out.println("You Summon " + Game_Objects.room.get(j).npc.get(Game_Objects.room.get(j).npc.size() - 1).name);
                            } catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                                //TODO Auto-Generated catch block
                                e.printStackTrace();
                            }
                        }
                   }
                }
            }
        }
    }

        @SuppressWarnings("deprecation")
        public void createItem(String[] x) {
            if (x.length == 1) {
                System.out.println("What would you like to create?");
            }
            if (x.length == 2) {
                for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {
                    Item localItem = (Item) Game_Objects.ItemDataBase.get(i);
                    if (localItem.id.equalsIgnoreCase(x[1])) {
                        for (int j = 0; j < Game_Objects.room.size(); j++) {
                            if (Game_Objects.room.get(j).number == Game_Objects.playerCharacter.inRoom) {

                                try {
                                    Game_Objects.room.get(j).item.add((Item) Class.forName(localItem.id).newInstance());

                                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                                    //TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                System.out.println(("You create a ") + Game_Objects.room.get(j).item.get(Game_Objects.room.get(j).item.size() - 1).itemName);
                            }
                        }
                    }
                }
            }
    }

    public void GameOver() {
        if (Game_Objects.playerCharacter.currentHitPoints <= 0) {
            System.out.println("You succumb to your wounds and drift out of this world...");
            System.out.println("GAME OVER");

        }
    }
    public void newGame() {
        worldItem();

        System.out.println("The innkeeper grunts at you. \" Name?\"");
        Scanner sc = new Scanner(System.in);
        Game_Objects.playerCharacter.name = sc.next();
        System.out.println("Your eyes adjust and you make your way across the room.");
        System.out.println("\"Well, rooms'r 4 gold a night, you can get a room and rations for 5 .\"");
        System.out.println("You hand the required coins over and the innkeep motions over to a flight of stairs.");
        System.out.println("\"First one up on the right.\"");

    }
}
