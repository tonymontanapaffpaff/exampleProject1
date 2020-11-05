package by.gsu.epamlab.consts;

public class DBConstants {
    public static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/results?serverTimezone=Europe/Minsk";
    public static final String USER = "epamlab";
    public static final String PASSWORD = "111";
    public static final String LOGIN_SELECT_QUERY = "SELECT `idLogin` FROM `logins` WHERE `name` = ?";
    public static final String LOGIN_INSERT_QUERY = "INSERT INTO `logins` (`idLogin`, `name`) VALUES (NULL, ?)";
    public static final String TEST_SELECT_QUERY = "SELECT `idTest` FROM `tests` WHERE `name` = ?";
    public static final String TEST_INSERT_QUERY = "INSERT INTO `tests` (`idTest`, `name`) VALUES (NULL, ?)";
    public static final String RESULT_INSERT_QUERY = "INSERT INTO `results` (`testId`, `loginId`, `dat`, `mark`) VALUES (?, ?, ?, ?)";
    public static final String MEAN_COST_SELECT_QUERY = "SELECT login.name as login, AVG(`mark`) as mark FROM `logins` login, `results`" +
            " WHERE results.loginId = login.idLogin GROUP BY `loginId` ORDER BY mark DESC";
    public static final String LIST_SELECT_QUERY = "SELECT test.name as test, login.name as login, `dat`, `mark` FROM tests test, logins login, `results`" +
            " WHERE results.testId = test.idTest AND results.loginId = login.idLogin AND `dat` >= DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY)" +
            " ORDER BY `dat` ASC";
    public static final int SELECT_INDEX = 1;
    public static final int TEST_INDEX = 1;
    public static final int LOGIN_INDEX = 2;
    public static final int DATE_INDEX = 3;
    public static final int MARK_INDEX = 4;
    public static final int MEAN_VALUE_LOGIN = 1;
    public static final int MEAN_VALUE_MARK = 2;
    public static final String LOGIN_FIELD = "login";
    public static final String TEST_FIELD = "test";
    public static final String DATE_FIELD = "dat";
    public static final String MARK_FIELD = "mark";
}
