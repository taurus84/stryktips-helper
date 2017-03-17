public final class Calculator {

    static double probability;
    static double odds;

    public static int convertOddsToProbability(double odds) {
        probability = 1 / odds;
        return  (int) (probability * 100);
    }

    public static double convertProbabilityToOdds(int probability) {
        odds = (probability * 1.0 / 100.0);
        return (double)(1 / odds);
    }

    public static int[][] getProcentOddsDifference(int[][] procents, double[][] odds) {
        int[][] returnArray = new int[procents.length][procents[0].length];
        for(int i = 0; i < procents.length; i++) {
            for(int j = 0; j < procents[0].length; j++) {
                returnArray[i][j] = procents[i][j] - convertOddsToProbability(odds[i][j]);
            }
        }
        return returnArray;
    }


}
