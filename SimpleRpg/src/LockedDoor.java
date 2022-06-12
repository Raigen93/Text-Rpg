public class LockedDoor extends Interactable {

    public LockedDoor() {
        String keyName = "CottageKey";
        name = "CottageDoor";
        id = "lockedDoor";
        boolean isLocked = true;


    }

    @Override
    public void interact() {
        for (int a = 0; a < Game_Objects.playerCharacter.itemsCarried.size(); a++) {
                if (Game_Objects.playerCharacter.itemsCarried.get(a).itemName.equals("CottageKey")) {
                    for (int b = 0; b < Game_Objects.Interactables.size(); b++) {


                    }
                }
        }
    }
}
