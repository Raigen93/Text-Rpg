import java.util.ArrayList;

public class Room {

    int number;
    String name;
    ArrayList<String> desc = new ArrayList<String>();
    ArrayList<String> exits = new ArrayList<String>();
    ArrayList<NPC> npc = new ArrayList<NPC>();
    ArrayList<Item> item = new ArrayList<Item>();
    ArrayList<Interactable> interactables = new ArrayList<Interactable>();
    public Room(int x) {
        number = x;
    }


}
