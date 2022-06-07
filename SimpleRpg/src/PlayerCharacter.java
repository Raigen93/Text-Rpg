import java.util.ArrayList;

public class PlayerCharacter {

    String name;
    int playerLevel = 1;
    int playerXp = 0;
    int strength = 2;
    int constitution = 2;
    int dexterity = 2;
    int charisma = 2;
    int baseHealth = 50;
    int maxHitPoints = baseHealth;
    int currentHitPoints = this.maxHitPoints;
    int inRoom = 0;
    int accuracy = 2;
    int goldCarried = 6;
    ArrayList<Item> itemsCarried = new ArrayList<Item>();
    ArrayList<Item> wornItems = new ArrayList<Item>();
    int damageBonus =  2;
    int playerArmor = 14;
    int xpToLevel = 100;
    int rations;


    public int setRations () {

        rations = 0;

        for (int a = 0; a < Game_Objects.playerCharacter.itemsCarried.size(); a++) {
            if (itemsCarried.get(a).itemName.equals("Rations")) {
                rations++;

            }
        } return rations;
    }


    public void look() {

        System.out.println("Your HitPoints are " + this.currentHitPoints);
        System.out.println("Your accuracy is " + this.accuracy);
        System.out.println("You have " + playerXp + " XP");
        System.out.println("Your armor is " + playerArmor);
        equipment();
    }


    public void wear(String[] x) {

        if (wornItems.size() == 0) {
            for (int i = 0; i < itemsCarried.size(); i++) {

                if (x[1].equalsIgnoreCase(itemsCarried.get(i).id) && itemsCarried.get(i).wearable) {
                    wornItems.add(itemsCarried.get(i));
                    System.out.println("You equip a " + itemsCarried.get(i).itemName);
                    itemsCarried.remove(i);

                    break;
                }
            }
        }
        else
        {
        boolean isWearing = false;
        for (int i = 0; i < wornItems.size(); i++) {
            for (int j = 0; j < itemsCarried.size(); j++) {

                if (x[1].equalsIgnoreCase(itemsCarried.get(j).id) && itemsCarried.get(j).wearable) {
                    if (itemsCarried.get(j).wearLoc.equals(wornItems.get(i).wearLoc)) {
                        System.out.println("You are already wearing something in that location.");
                        isWearing = true;
                    }
                }
            }
            if(!isWearing) {
                wornItems.add(itemsCarried.get(i));
                System.out.println("You unequip a " + itemsCarried.get(i).itemName);
                itemsCarried.remove(i);
                setPlayerArmor();
                break;
            }
        }
        }
    }

    

    public void setPlayerArmor() {
        for (int a = 0; a < Game_Objects.playerCharacter.wornItems.size(); a++) {
            if (Game_Objects.playerCharacter.wornItems.get(a).wearLoc.equals("Armor")) {
                playerArmor = playerArmor + Game_Objects.playerCharacter.wornItems.get(a).armorBonus;
            }
        }
    }

    public void equipment() {
        for (int i = 0; i < wornItems.size(); i++) {
            System.out.println(wornItems.get(i).itemName + " : " + wornItems.get(i).wearLoc);

        }
        System.out.println("You have " + goldCarried + " gold drachmas.");
    }

    public void remove(String[] x) {
        for (int i = 0; i < wornItems.size(); i++) {
            if (wornItems.get(i).id.equals(x[1])) {
                System.out.println("You doff a " + wornItems.get(i).id);
                itemsCarried.add(wornItems.get(i));
                wornItems.remove(i);
            }
        }
    }

    public void levelUp () {

        if (this.playerXp >= this.xpToLevel) {
            this.playerLevel++;
            this.strength++;
            this.constitution++;
            this.dexterity++;
            this.charisma++;
            this.maxHitPoints += 8;
            if (this.playerLevel % 2 == 0) {
                this.accuracy++;

            }

            this.xpToLevel = this.xpToLevel * 2;
            System.out.println("xp to level = " + xpToLevel);
            this.accuracy = this.accuracy + (this.dexterity / 2);
            this.damageBonus = this.damageBonus + (this.strength / 2);

            System.out.println("Your might has grown.");
        }
    }
}

