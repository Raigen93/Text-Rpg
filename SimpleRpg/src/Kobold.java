
public class Kobold extends NPC {
    public Kobold() {

        name = "Kobold";
        desc = "A small, reptilian creature stands here, occasionally yapping to itself. It carries a shortsword in one hand. You recognise it as a kobold.";
        hitPoints = Game_Objects.rng.randomNumber(6) + 5;
        accuracy = 2;
        id = "Kobold";
        damageBonus = 0;
        armor = 0;
        EXPValue = 10;
        hostile = true;
        lootItem = "koboldTalisman";
    }

    public void setHostile() {
        if (Game_Objects.playerCharacter.playerLevel > 2) {
            hostile = false;
        }
    }


}
