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


}
