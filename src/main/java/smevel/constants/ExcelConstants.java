package smevel.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelConstants {

    public static final String EXCEL_FILE_NAME_DATE_FORMAT = "dd_MM_yyyy";

    public static final String ALL_VACATIONS_REPORT_FILE_NAME = "all_vacations_%s";
    public static final String ALL_VACATIONS_REPORT_SHEET_NAME = "All vacations";

    public static final String VACATIONS_BY_DATE_REPORT_FILE_NAME = "vacations_by_date_range_%s";
    public static final String VACATIONS_BY_DATE_REPORT_SHEET_NAME = "Vacations by date range";


    public final static List<String> ALL_LIST_OF_VACATIONS_DATA_TITLES = Arrays.asList("ФИО", "Начало отпуска", "Конец отпуска",
            "Длительность отпуска (днях)", "Должность","Проект");
    public final static ArrayList<String> DATE_RANGES_TITLES = new ArrayList<>(Arrays.asList("Начало периода", "Конец периода"));

}
