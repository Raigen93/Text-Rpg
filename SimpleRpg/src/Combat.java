
public class Combat {

        public void attack(String[] x) {

            for (int i = 0; i < Game_Objects.room.size(); i++) {
                if (Game_Objects.room.get(i).number == Game_Objects.playerCharacter.inRoom) {
                    for (int j = 0; j < Game_Objects.room.get(i).npc.size(); j++) {

                        if (Game_Objects.room.get(i).npc.get(j).id.equalsIgnoreCase(x[1])) {

                            int npc_hit = Game_Objects.rng.randomNumber(20);
                            npc_hit = npc_hit + (Game_Objects.room.get(i).npc.get(j).accuracy / 2);

                            if (npc_hit > Game_Objects.playerCharacter.playerArmor) {
                                int npc_damage = Game_Objects.rng.randomNumber(6) + (Game_Objects.room.get(i).npc.get(j).damageBonus);
                                Game_Objects.playerCharacter.currentHitPoints = Game_Objects.playerCharacter.currentHitPoints - npc_damage;
                                System.out.println("The " + Game_Objects.room.get(i).npc.get(j).name + " hit you for " + npc_damage);
                            } else {
                                System.out.println("The " + Game_Objects.room.get(i).npc.get(j).name + " missed.");
                            }
                            int pc_hit = Game_Objects.rng.randomNumber(20);
                            pc_hit = (pc_hit + Game_Objects.playerCharacter.accuracy ) - Game_Objects.room.get(i).npc.get(j).armor;
                            for (int w = 0; w < Game_Objects.playerCharacter.wornItems.size(); w++) {
                                if (Game_Objects.playerCharacter.wornItems.get(w).wearLoc.equals("wield")) {
                                    pc_hit += Game_Objects.playerCharacter.wornItems.get(w).hitBonus;
                                }
                            }
                            if (pc_hit > 14) {
                                int pc_damage = (Game_Objects.rng.randomNumber(6) + Game_Objects.playerCharacter.damageBonus);
                                for (int a = 0; a < Game_Objects.playerCharacter.wornItems.size(); a++) {
                                    if (Game_Objects.playerCharacter.wornItems.get(a).wearLoc.equals("wield")) {
                                        pc_damage = pc_damage + (Game_Objects.playerCharacter.wornItems.get(a).damageBonus);
                                        int critCheck = Game_Objects.rng.randomNumber(20);
                                        if (critCheck > 19) {
                                            System.out.println("A critical hit!");
                                            pc_damage = pc_damage * 2;
                                        }
                                    }
                                }
                                Game_Objects.room.get(i).npc.get(j).hitPoints = Game_Objects.room.get(i).npc.get(j).hitPoints - pc_damage;
                                System.out.println("You dealt " + pc_damage + " points of damage!");
                                if (Game_Objects.room.get(i).npc.get(j).hitPoints <= 0) {
                                    npc_death(i, j);
                                }
                            } else {
                                System.out.println("You missed your target.");
                            }
                        }
                    }
                }
            }
        }
                public void npc_death(int i , int j) {
            if (Game_Objects.room.get(i).npc.get(j).deathText == null) {
                System.out.println("A " + Game_Objects.room.get(i).npc.get(j).name + " has been slain!");
            } else {
                System.out.println(Game_Objects.room.get(i).npc.get(j).deathText);
            }
                Game_Objects.playerCharacter.playerXp = Game_Objects.playerCharacter.playerXp + Game_Objects.room.get(i).npc.get(j).EXPValue;
                Game_Objects.NPCdataBase.get(j).lootDrop(Game_Objects.room.get(i).npc.get(j).lootItem);
                Game_Objects.room.get(i).npc.remove(j);


        }

}
