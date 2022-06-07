public class Goblin extends NPC {

        public Goblin() {
            name = "A Goblin";
            desc = "A sickly looking humanoid with greenish brown skin and gnashing teeth. It wears tattered clothing and smells of sweat and rot.";
            hitPoints = 30;
            accuracy = 4;
            id = "Goblin";
            damageBonus = 2;
            armor = 0;
            hostile = true;
            EXPValue = 50;
            lootItem = "goblinEar";
        }
}
