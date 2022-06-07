public class MaidenStatue extends Interactable{
    public MaidenStatue(){

        id = "MaidenStatue";
    }


    public void interact() {

        {
            System.out.println("You gently grasp the outstretched hand.");
            System.out.println("A soothing aura washes over you, and you feel the fatigue leave your body.");
            Game_Objects.playerCharacter.currentHitPoints = Game_Objects.playerCharacter.maxHitPoints;
            Game_Objects.playerCharacter.levelUp();
        }
    }

    public void speak() {
        System.out.println("... you feel as though the statue wishes it could reply to your voice...");
    }
}
