import java.util.ArrayList;

public class Game_Objects {
    static PlayerCharacter playerCharacter = new PlayerCharacter();
    static ArrayList<Room> room = new ArrayList<Room>();
    static ArrayList<NPC> NPCdataBase = new ArrayList<NPC>();
    static ArrayList<Object> ItemDataBase = new ArrayList<Object>();
    static RNG rng = new RNG();
    static Combat combat = new Combat();
    static ArrayList<Object> Interactables = new ArrayList<Object>();
    public static void initializeNPCArray() {
        NPCdataBase.add(new NPC());
        NPCdataBase.add(new Kobold());
        NPCdataBase.add(new Goblin());
        NPCdataBase.add(new Orc());
        NPCdataBase.add(new KoboldGroup());
        NPCdataBase.add(new KoboldChieftan());
    }

    public static void initializeItemArray() {
        ItemDataBase.add(new Item());
        ItemDataBase.add(new ShortSword());
        ItemDataBase.add(new koboldTalisman());
        ItemDataBase.add(new Chainmail());
        ItemDataBase.add(new RingOfMight());
        ItemDataBase.add(new GoblinEar());
        ItemDataBase.add(new Rations());
        ItemDataBase.add(new GreatAxe());
        ItemDataBase.add(new CottageKey());
    }

    public static void initializeInteractables() {
        Interactables.add(new Interactable());
        Interactables.add(new MaidenStatue());
        Interactables.add(new Ehleria());
        Interactables.add(new Merchant());
        Interactables.add(new Innkeeper());
        Interactables.add(new LockedDoor());
    }
}
