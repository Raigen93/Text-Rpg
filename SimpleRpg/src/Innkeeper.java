
public class Innkeeper extends Merchant {

    public Innkeeper() {
        name = "Durg";
        desc = "Durg, the owner of the Bronze Tankard Inn, he looks as though he would be more comfortable on a battlefield.";
        id = "Innkeeper";

    }

    @Override
    public void interact() {
        int koboldTaliCount = collectibleCheck("koboldTalisman");
        System.out.println("Durg counts out the poorly fabricated Talismans as you hand them to him.");
        if (koboldTaliCount >= 10) {

            System.out.println("He speaks, nodding in approval \"Pretty good work coming up with these so fast.\" ");

            for (int a = 10; a != 0; a--) {
                if (Game_Objects.playerCharacter.itemsCarried.get(a).itemName.equals("koboldTalisman")) {
                    Game_Objects.playerCharacter.itemsCarried.remove(a);
                }
            }

            Game_Objects.playerCharacter.playerXp += 100;
            Game_Objects.playerCharacter.goldCarried += 30;
            if (Game_Objects.playerCharacter.playerLevel >= 3) {
                System.out.println(" \"... Although this might be getting to easy for you... \" ");

            }


        } else {
            System.out.println("\"... it looks like you're short a few. \" ");
        }
    }

    public void speak () {
        int speakRoll = Game_Objects.rng.randomNumber(5);
        switch(speakRoll) {
            case 1:
                System.out.println("Durg takes a drink from his tankard.");
                System.out.println("...ye were lucky to make it here before that storm hit.");
                System.out.println("The forest north o' here is probably soaked");
                break;

            case 2:
                System.out.println("Durg looks at you, pondering for a moment.");
                System.out.println(" \"You look a bit young to be traveling alone...\" ");
                System.out.println("He grins and pats your shoulder, \"Not quite as young as I was though!\"");
                break;

            case 3:
                System.out.println("Durg grunts, acknowledging your presence.");
                break;

            case 4:
                System.out.println("Durg looks you over...");
                System.out.println("\"If you need some work you could clear out a bit of the forest north of here.\" ");
                System.out.println("\"Bring me ten of the talismans those kobolds carry, and there'll be a reward for you.\"");
                System.out.println("You turn to move away from the bar, and Durg grabs your shoulder.");
                System.out.println("\"A word of warning... stay out of the cave.\"");
                break;

            case 5:
                System.out.println("Durg smokes from a pipe, its aroma filling your nose, and nods in your direction.");
                break;

        }
    }

}
