public class NPC {
    public String name;
    public String desc;
    public int hitPoints;
    public int accuracy;
    public String id = "NPC";
    public int damageBonus;
    public int armor;
    public int EXPValue;
    public boolean hostile;
    public String lootItem;
    public String deathText;

    public void look() {

        System.out.println(this.name);
        System.out.println("Hit points " + this.hitPoints);
    }

    @SuppressWarnings("deprecation")
    public void lootDrop(String x) {

        for (int a = 0; a < Game_Objects.ItemDataBase.size(); a++) {
            Item lootItem = (Item) Game_Objects.ItemDataBase.get(a);

            for (int i = 0; i < Game_Objects.room.size(); i++) {

                if (Game_Objects.playerCharacter.inRoom == Game_Objects.room.get(i).number) {
                    if (lootItem.id.equals(x)) {
                        try {
                            Game_Objects.playerCharacter.itemsCarried.add((Item) Class.forName(lootItem.id).newInstance());
                            System.out.println("You looted " + x + " from the enemy.");
                        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                            //TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
            int lootRoll = Game_Objects.rng.randomNumber(2) +1;
            if (lootRoll > 0) {
                int goldDrop = Game_Objects.rng.randomNumber(3) + 1;
                System.out.println("You gain " + goldDrop + " Gold.");
                Game_Objects.playerCharacter.goldCarried = Game_Objects.playerCharacter.goldCarried + goldDrop;

            }
    }

    public void rationsLoot() {
        if (this.lootItem.equals("Rations")) {
            Game_Objects.playerCharacter.rations++;
        }
    }

    public void hostileNPC() {

    }
}