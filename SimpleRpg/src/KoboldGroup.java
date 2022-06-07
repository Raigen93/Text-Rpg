public class KoboldGroup extends Kobold {

    public KoboldGroup() {
        name = "kobolds";
        desc = "Several kobolds are ready to fight together, making for a much more serious threat.";
        hitPoints = 20 + Game_Objects.rng.randomNumber(5) + Game_Objects.rng.randomNumber(5) + Game_Objects.rng.randomNumber(5);
        accuracy = 4;
        id = "Kobolds";
        damageBonus = 4;
        armor = 0;
        EXPValue = 30;
        lootItem = "Rations";
    }

}
