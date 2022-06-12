
public class Orc extends NPC {

    public Orc() {
        name = "Orc";
        desc = "A large, bestial humanoid, it wields a large axe.";
        hitPoints = 30 + (Game_Objects.rng.randomNumber(30));
        accuracy = 4;
        id = "Orc";
        damageBonus = 4;
        armor = 10;
        EXPValue = 30;
    }
}

