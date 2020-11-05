import by.gsu.epamlab.consts.Constants;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.utility.RunnerLogic;

public class RunnerInt {
    public static void main(String[] args) {
        ResultFactory factory = new ResultFactory();
        RunnerLogic.execute(factory, Constants.INPUT_CSV);
    }
}
