public class Controller {

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

}
