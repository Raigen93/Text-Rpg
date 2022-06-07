import java.io.IOException;
import java.util.ArrayList;

public class Interact_Thread {

    Game_Logic currentGL;

    public Interact_Thread(Game_Logic gL) {

        currentGL = gL;
    }

    public void startInteractThread() {
        Thread one = new Thread() {
            public void run() {
                try {
                    while (true) {

                        populateGame();
                        Thread.sleep(1000);

                    }
                } catch (InterruptedException v) {
                    System.out.println(v);
                }
            }
        }

                ;

        one.start();
    }


    @SuppressWarnings("deprecation")
    public void populateGame() {
        if (Game_Objects.playerCharacter.inRoom != 0) {
            int roomInteractCount = 0;
            if (Game_Objects.Interactables.size() < 1) {
                Game_Objects.Interactables.add(new Interactable());
            }
            ArrayList<String> interactLines = new ArrayList<String>();
            try {
                interactLines = currentGL.readLines("TextFile/interactLocs.txt");
            } catch (IOException e) {
                //TODO auto-generated catch block
                e.printStackTrace();
            }
            for (int i = 0; i < interactLines.size(); i++) {
                String[] words = interactLines.get(i).split(" ");

                if (words[0].equals("Name:")) {
                    for (int j = 0; j < Game_Objects.room.size(); j++) {
                        if (Game_Objects.room.get(j).number == Integer.parseInt(words[2])) {
                            for (int k = 0; k < Game_Objects.room.get(j).interactables.size(); k++) {
                                if (Game_Objects.room.get(j).interactables.get(k).id.equalsIgnoreCase(words[1])) {
                                    roomInteractCount++;
                                }
                            }
                        }
                    }
                    if (roomInteractCount == 0) {
                        for (int j = 0; j < Game_Objects.room.size(); j++) {
                            if (Game_Objects.room.get(j).number == Integer.parseInt(words[2])) {
                                try {
                                    Game_Objects.room.get(j).interactables.add((Interactable) Class.forName(words[1]).newInstance());
                                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                                    //TODO auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}