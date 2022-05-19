package Casino;

public class Reel implements numbersInterface {

    private int num1;
    private int num2;
    private int num3;

    public Reel(int num1, int num2, int num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public Reel() {
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getNum3() {
        return num3;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }

    @Override
    public String toString() {
        return "Reel{" + "num1:" + num1 + ", num2:" + num2 + ", num3" + num3 + '}';
    }

    

    public int getRandomNumber() {
        int min = 0;
        int max = 140;

        return (int) ((Math.random() * (max - min)) + min);
    }

    public int numberSelector(int i) {

        if (i < 5) {
            i = 1;
        } else if (i >= 5 && i <= 14) {
            i = 2;
        } else if (i >= 15 && i <= 29) {
            i = 3;
        } else if (i >= 30 && i <= 49) {
            i = 4;
        } else if (i >= 50 && i <= 74) {
            i = 5;
        } else if (i >= 75 && i <= 104) {
            i = 6;
        } else if (i >= 105 && i <= 139) {
            i = 7;
        }
        return i;

    }

    public static double rewardCalculator(Partida partida, Reel reel1, Reel reel2, Reel reel3) {

        double reward = 0;

        if (reel1.getNum1() == reel1.getNum2() && reel1.getNum1() == reel1.getNum3()) {

            if (reel1.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 24) / reel1.getNum1();
            }
            reward = reward + (2 * partida.getBet() * 3) / (reel1.getNum1() / 2.0);
            System.out.println("Linea 1 ganadora");

        }

        if (reel2.getNum1() == reel2.getNum2() && reel2.getNum1() == reel2.getNum3()) {

            if (reel2.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 24) / reel2.getNum1();
            }
            reward = reward + (2 * partida.getBet() * 3) / (reel2.getNum1() / 2.0);
            System.out.println("Linea 2 ganadora");

        }

        if (reel3.getNum1() == reel3.getNum2() && reel3.getNum1() == reel3.getNum3()) {

            if (reel3.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 24) / reel3.getNum1();
            }
            reward = reward + (2 * partida.getBet() * 3) / (reel3.getNum1() / 2.0);
            System.out.println("Linea 3 ganadora");

        }

        if (reel1.getNum1() == reel2.getNum2() && reel1.getNum1() == reel3.getNum3()) {

            if (reel1.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 18) / reel1.getNum1();
            }
            reward = reward + (2 * partida.getBet() * 2) / (reel1.getNum1() / 2.0);
            System.out.println("Diagonal 1 ganadora");

        }

        if (reel3.getNum1() == reel2.getNum2() && reel3.getNum1() == reel1.getNum3()) {

            if (reel3.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 18) / reel3.getNum1();
            }
            reward = reward + (2 * partida.getBet() * 2) / (reel3.getNum1() / 2.0);
            System.out.println("Diagonal 2 ganadora");

        }

        if (reel1.getNum1() == reel1.getNum2() && reel1.getNum1() == reel2.getNum3()) {

            if (reel1.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 12) / reel1.getNum1();
            }
            reward = reward + (2 * partida.getBet()) * 1.5 / (reel1.getNum1() / 2.0);
            System.out.println("Combinada 1 ganadora");

        }

        if (reel3.getNum1() == reel3.getNum2() && reel3.getNum1() == reel2.getNum3()) {

            if (reel3.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 12) / reel3.getNum1();
            }
            reward = reward + (2 * partida.getBet() * 1.5) / (reel3.getNum1() / 2.0);
            System.out.println("Combinada 2 ganadora");

        }

        if (reel2.getNum1() == reel3.getNum2() && reel2.getNum1() == reel3.getNum3()) {

            if (reel2.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 12) / reel2.getNum1();
            }
            reward = reward + (2 * partida.getBet()) * 1.5 / (reel2.getNum1() / 2.0);
            System.out.println("Combinada 3 ganadora");
        }

        if (reel2.getNum1() == reel1.getNum2() && reel2.getNum1() == reel1.getNum3()) {

            if (reel2.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 12) / reel2.getNum1();
            }
            reward = reward + (2 * partida.getBet() * 1.5) / (reel2.getNum1() / 2.0);
            System.out.println("Combinada 4 ganadora");
        }

        if (reel2.getNum1() == reel1.getNum2() && reel2.getNum1() == reel2.getNum3()) {

            if (reel2.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 12) / reel2.getNum1();
            }
            reward = reward + (2 * partida.getBet() * 1.5) / (reel2.getNum1() / 2.0);
            System.out.println("Combinada 4 ganadora");
        }

        if (reel2.getNum1() == reel3.getNum2() && reel2.getNum1() == reel2.getNum3()) {

            if (reel2.getNum1() == 1) {
                reward = reward + (2 * partida.getBet() * 12) / reel2.getNum1();
            }
            reward = reward + (2 * partida.getBet() * 1.5) / (reel2.getNum1() / 2.0);
            System.out.println("Combinada 4 ganadora");
        }

        return reward;

    }

}
