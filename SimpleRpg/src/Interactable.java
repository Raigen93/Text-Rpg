public class Interactable {
    String name;
    String desc;
    String id = "Interactable";

    public void look() {

        System.out.println(this.desc);
    }

    public void speak(){
        System.out.println("Your attempts at conversation illicit no response.");
    }
    public void interact(){
        System.out.println("You try to interact... it doesn't work");
    }

    public int collectibleCheck(String collectible) {
        int collectibleCount = 0;
        for (Item item : Game_Objects.playerCharacter.itemsCarried) {
            if (item.itemName.equalsIgnoreCase(collectible)) {
                collectibleCount++;
            }


        }return collectibleCount;
    }
}
