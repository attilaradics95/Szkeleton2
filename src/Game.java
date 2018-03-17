import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    /**Singleton metódusok és attributumok*/
    private static Game instance = null;
    private Game(){
        boxes = new ArrayList<>();
    }
    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**Attributumok*/
    private Controller controller = null;
    private boolean roundover = false;
    private ArrayList<Box> boxes;

    /**Metódusok*/
    private void loadMap(){

    }

    public void decreaseBoxes(Box box){
        boxes.remove(box);
        if (boxes.isEmpty()){
            this.endRound();
        }
    }

    private void startRound(){
        System.out.println("startRound()");
        //MAP betöltese
        this.loadMap();

        //Loop az inputok kezelesere
        while(!roundover){
            //Menu kiirasa
            System.out.println("1.Irány megadása(W,A,S,D)");
            System.out.println("2.Raktáros kiválasztása(0,1,2...99)");
            System.out.println("3.Feladás(igiveup)");
            System.out.println("Kérem adja meg az inputot:");
            //Input beolvasása a konzolról
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Regularis kifejezesek az input csekkolasara
            Pattern selectPattern = Pattern.compile("^([0-9]|[1-9][0-9])$");
            Pattern movePattern = Pattern.compile("^(W|A|S|D|w|a|s|d)$");
            Matcher selectMatch = selectPattern.matcher(input);
            Matcher moveMatch = movePattern.matcher(input);

            if(selectMatch.matches()){
                controller.selectWorker(Integer.parseInt(input));
            }
            if(moveMatch.matches()){
                switch (input){
                    case "W":
                    case "w":
                        controller.moveWorker(Directions.NORTH);
                        break;
                    case "D":
                    case "d":
                        controller.moveWorker(Directions.EAST);
                        break;
                    case "S":
                    case "s":
                        controller.moveWorker(Directions.SOUTH);
                        break;
                    case "A":
                    case "a":
                        controller.moveWorker(Directions.WEST);
                        break;
                }
            }
            if (input.equals("igiveup")){
                this.endRound();
            }
            else{
                System.out.println("Az input nem valid!");
            }
        }
    }

    public void endRound(){
        System.out.println("endRound()");
        //Akkor lep ki a loopbol ha megkapja a megfelelo inputot
        while (true) {
            System.out.println("Vége van a körnek? (Y/N)");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = "";
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("Y") || input.equals("y")) {
                roundover = true;
                return;
            }
            if (input.equals("N") || input.equals("n")) {
                roundover = false;
                return;
            }
        }
    }

    public static void main(String[] args) {
        Game game = getInstance();
        game.controller = Controller.getInstance();
        TestCases tests = new TestCases();

        System.out.println("Teszt kiválasztása(1,2,...):");
        /**Teszt template */
        System.out.println("n. Teszt: Teszt leírása");
        System.out.println("1. Teszt: Worker pushes Box to Target");
        System.out.println("2. Teszt: Worker pushes Box to Column");
        System.out.println("3. Teszt: Select Worker In Action");
        System.out.println("4. Teszt: Trap Opens With A Box On It ");
        System.out.println("5. Teszt: Worker -> Box -> Box");
        System.out.println("6. Teszt: Worker -> Box -> Worker -> Worker -> Wall");
        System.out.println("7. Teszt: Worker on Column");
        System.out.println("8. Teszt: Worker on Hole");
        System.out.println("9. Teszt: Worker on Wall");
        System.out.println("10. Teszt: Worker pushes Box");
        System.out.println("11. Teszt: Worker stands on a Trap");
        System.out.println("12. Teszt: Worker steps on active Trap");
        System.out.println("13. Teszt: Worker steps on inactive Trap");
        System.out.println("14. Teszt: Worker steps onTarget");

        /**
         *
         *  /\
         *  |
         *  |
         *  |
         * IDE PRINTELJETEK KI LEGYSZI A TESZTEKET AMIKET MEGIRTATOK EGY SZAMMAL AZ ELEJEN ÉS RAKJATOK BE A SWITCHBE PLS
         */


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (Integer.parseInt(input)){
            case 1:
                tests.WorkerPushesBoxToTarget();
                break;
            case 2:
                tests.WorkerPushesBoxToColumn();
                break;
            case 3:
                tests.SelectWorkerInAction();
                break;
            case 4:
                tests.trapOpensWithABoxOnIt();
                break;
            case 5:
                tests.MLL();
                break;
            case 6:
                tests.MLMMF();
                break;
            case 7:
                tests.WorkerOnColumn();
                break;
            case 8:
                tests.WorkerOnHole();
                break;
            case 9:
                tests.WorkerOnWall();
                break;
            case 10:
                tests.WorkerPushesBox();
                break;
            case 11:
                tests.WorkerStandsOnTrap();
                break;
            case 12:
                tests.WorkerStepsOnActiveTrap();
                break;
            case 13:
                tests.WorkerStepsOnInactiveTrap();
                break;
            case 14:
                tests.WorkerStepsOnTarget();
                break;
        }
    }
}
