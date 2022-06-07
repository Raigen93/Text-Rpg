public class Main {
    static Game_Logic gL = new Game_Logic();

    public static void main(String[] args) {
       Game_Objects.initializeNPCArray();
       Game_Objects.initializeItemArray();
       Game_Objects.initializeInteractables();
       Enemy_Thread enemyThread = new Enemy_Thread(gL);
       enemyThread.startEnemyThread();
       Interact_Thread interact_thread = new Interact_Thread(gL);
       interact_thread.startInteractThread();

        while(true) {
            gameLoop();
        }
    }



    public static void gameLoop() {

        gL.waitForCommand();

    }

    }


