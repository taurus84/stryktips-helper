public class Controller {

    private SvenskaSpelData ssd;
    private final int  MAXOFFSET = 15;
    int probability;
    double odds;

    StryktipsPanel viewer;
    public Controller(StryktipsPanel viewer) {
        this.viewer = viewer;
    }

    public int convertOddsToProbability(double odds) {
        probability = Calculator.convertOddsToProbability(odds);
        viewer.taResult.setText(probability + "");
        return  probability;
    }

    public double convertProbabilityToOdds(int probability) {
        odds = Calculator.convertProbabilityToOdds(probability);
        //change view
        String oddsFormated = String.format("%.2f", odds);
        viewer.taResult.setText(oddsFormated + "");
        return odds;
    }

    public void getData() {
        ssd = new SvenskaSpelData();
        String header = ssd.getWeekHeader();
        String turnOver = ssd.getTurnOver() + "";
        String[] games = ssd.getMatches();
        String[][] procents = ssd.getProcents();
        String[][] odds = ssd.getOdds();
        int[][] diffs = Calculator.getProcentOddsDifference(convertStringArrToIntArr(procents), convertStringArrToDoubleArr(odds));
        viewer.updateAllPanels(header, turnOver, games, procents, odds, diffs);
        viewer.updateDiffPanel(getHuePower(diffs));
    }

    private int[][] convertStringArrToIntArr(String[][] array) {
        int[][] returnArray = new int[array.length][array[0].length];
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                returnArray[i][j] = Integer.parseInt(array[i][j]);
            }
        }
        return returnArray;
    }

    private double[][] convertStringArrToDoubleArr(String[][] array) {
        double[][] returnArray = new double[array.length][array[0].length];
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                returnArray[i][j] = Double.parseDouble(array[i][j]);
            }
        }
        return returnArray;
    }

    private double[][] getHuePower(int[][] diffs) {
        int tempDiff;
        double huePower;
        double[][] returnArray = new double[diffs.length][diffs[0].length];
        for(int i = 0; i < diffs.length; i++) {
            for (int j = 0; j < diffs[0].length; j++) {
                tempDiff = diffs[i][j];
                if(tempDiff > MAXOFFSET) {
                    huePower = 0;
                } else if(tempDiff < - MAXOFFSET) {
                    huePower = 1;
                } else {
                    huePower = ((double)((tempDiff + MAXOFFSET))/((double)  (MAXOFFSET*2)));
                } returnArray[i][j] = 1 - huePower;
            }
        }
        return returnArray;
    }
}
