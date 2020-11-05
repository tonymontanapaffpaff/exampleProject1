import by.gsu.epamlab.consts.Constants;
import by.gsu.epamlab.factories.HalfResultFactory;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.utility.RunnerLogic;

public class RunnerHalf {
    public static void main(String[] args) {
        ResultFactory factory = new HalfResultFactory();
        RunnerLogic.execute(factory, Constants.INPUT_HALFCSV);
    }
}
