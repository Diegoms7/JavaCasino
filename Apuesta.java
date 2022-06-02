
import java.util.ArrayList;

/**
 * @author HMartin
 */
public class Apuesta {

    private Integer numsBetsPleno;
    private String groupsNum;
    private Double cuantityBetNum;
    private Double cuantityBetGroup;
    private double ganancias;

    public Apuesta(Integer numsBetsPleno, String groupsNum, Double cuantityBetNum, Double cuantityBetGroup) {
        this.numsBetsPleno = numsBetsPleno;
        this.groupsNum = groupsNum;
        this.cuantityBetNum = cuantityBetNum;
        this.cuantityBetGroup = cuantityBetGroup;
        this.ganancias = 0;
    }

    public boolean checkBet() {
        boolean check = false;

        if (this.numsBetsPleno == null && this.cuantityBetNum == null)
        {
            System.out.println("Numeros apostados: 0");
            System.out.println("Valor de la apuesta: 0");
            check = true;
        }

        return check;

    }

    public boolean compareNums(int gameNum) {
        boolean x = false;

        if (this.numsBetsPleno == gameNum)
        {
            System.out.println("Has acertado el pleno");
            x = true;

        }

        return x;
    }

    public ArrayList<Integer> compareList() {
        ArrayList<Integer> listWins = new ArrayList<>();
        boolean check = true;

        if (checkBet() == true)
        {
            System.out.println("No ha apostado a ningun grupo");
            check = false;
        }

        while (check)
        {

            if (this.groupsNum.equalsIgnoreCase("negro"))
            {
                listWins.add(888);
            }
            if (this.groupsNum.equalsIgnoreCase("rojo"))
            {
                listWins.add(000);
            }
            if (this.groupsNum.equalsIgnoreCase("vecinos"))
            {
                listWins.add(0);
            }
            if (this.groupsNum.equalsIgnoreCase("series"))
            {
                listWins.add(2);
            }
            if (this.groupsNum.equalsIgnoreCase("huerfanos"))
            {
                listWins.add(1);
            }

        }

        return listWins;
    }

    public double gains(int gameNum) {
        double finalGain = 0;
        if (compareNums(gameNum)==false)
        {
            System.out.println("No has acertado el pleno");
        }
        if (compareNums(gameNum)==true)
        {
            finalGain = cuantityBetNum * 36;
        }

        for (int i = 0; i < compareList().size(); i++)
        {

            if (compareList().get(i) == (0))
            {
                finalGain = +this.cuantityBetGroup * 2.4;

            }
            if (compareList().get(i) == (2))
            {
                finalGain = +this.cuantityBetGroup * 2.6;

            }
            if (compareList().get(i) == (1))
            {
                finalGain = +this.cuantityBetGroup * 2.8;

            }
            if (compareList().get(i) == (000))
            {
                finalGain = +this.cuantityBetGroup * 2;

            }
            if (compareList().get(i) == (888))
            {
                finalGain = +this.cuantityBetGroup * 2;

            }

        }

        return finalGain;

    }

    public String getGroupsNum() {
        return groupsNum;
    }

    public void setGroupsNum(String groupsNum) {
        this.groupsNum = groupsNum;
    }

    public Double getCuantityBetGroup() {
        return cuantityBetGroup;
    }

    public void setCuantityBetGroup(Double cuantityBetGroup) {
        this.cuantityBetGroup = cuantityBetGroup;
    }
    
    

    public Integer getNumsBetsPleno() {
        return numsBetsPleno;
    }

    public void setNumsBetsPleno(Integer numsBetsPleno) {
        this.numsBetsPleno = numsBetsPleno;
    }

    public Double getCuantityBetNum() {
        return cuantityBetNum;
    }

    public void setCuantityBetNum(Double cuantityBetNum) {
        this.cuantityBetNum = cuantityBetNum;
    }

    public double getGanancias() {
        return ganancias;
    }

    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }

    @Override
    public String toString() {
        return "Apuesta{" + "numsBetsPleno=" + numsBetsPleno + ", groupsNum=" + groupsNum + ", cuantityBetNum=" + cuantityBetNum + ", cuantityBetGroup=" + cuantityBetGroup + ", ganancias=" + ganancias + '}';
    }

}
