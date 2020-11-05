import by.gsu.epamlab.consts.Constants;
import by.gsu.epamlab.factories.DecimalResultFactory;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.utility.RunnerLogic;

public class RunnerDecimal {
    public static void main(String[] args) {
        ResultFactory factory = new DecimalResultFactory();
        RunnerLogic.execute(factory, Constants.INPUT_XML);
    }
}
