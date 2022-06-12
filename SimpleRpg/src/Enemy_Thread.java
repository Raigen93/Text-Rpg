import java.io.IOException;
import java.util.ArrayList;

public class Enemy_Thread {
    Game_Logic currentGL;

    public Enemy_Thread(Game_Logic gL) {

        currentGL = gL;
    }

    public void startEnemyThread() {
        Thread one = new Thread() {
            public void run() {
                    try {
                        while (true) {

                            populateGame();
                            Thread.sleep(30000);

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
            int roomMobCount = 0;
            if (Game_Objects.NPCdataBase.size() < 1) {
                Game_Objects.NPCdataBase.add(new NPC());
            }
            ArrayList<String> lines = new ArrayList<String>();
            try {
                lines = currentGL.readLines("SimpleRPG/TextFile/enemylocations.txt");
            } catch (IOException e) {
                //TODO auto-generated catch block
                e.printStackTrace();
            }
            for (int i = 0; i < lines.size(); i++) {
                String[] words = lines.get(i).split(" ");

                if (words[0].equals("Name:")) {
                    for (int j = 0; j < Game_Objects.room.size(); j++) {
                        if (Game_Objects.room.get(j).number == Integer.parseInt(words[2])) {
                            for (int k = 0; k < Game_Objects.room.get(j).npc.size(); k++) {
                                if (Game_Objects.room.get(j).npc.get(k).id.equalsIgnoreCase(words[1])) {
                                    roomMobCount++;
                                }
                            }
                        }
                    }
                    if (roomMobCount == 0) {
                        for (int j = 0; j < Game_Objects.room.size(); j++) {
                            if (Game_Objects.room.get(j).number == Integer.parseInt(words[2])) {
                                try {
                                    Game_Objects.room.get(j).npc.add((NPC) Class.forName(words[1]).newInstance());
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
