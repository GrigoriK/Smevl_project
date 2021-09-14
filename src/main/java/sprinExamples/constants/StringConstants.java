package sprinExamples.constants;

import java.util.HashMap;
import java.util.Map;

public class StringConstants {
    public static final String ID = "id";
    public static final String CAN_NOT_FIND_ENTITY_BY_FIELD = "Can't find  %s by %s: %s";
    public static final String CAN_NOT_FIND = "Can't find  %s";
    public static final String CAN_NOT_CREATE = "Can't create  %s";
    public static final String CAN_NOT_UPDATE = "Can't update  %s entity with id: %s";

    public static final String PROJECT = "Project";
    public static final String PROJECTS = "Projects";
    public static final String PROJECT_ID = "projectId";
    public static final String PROJECT_NAME = "projectName";
    public static final String PROJECT_CODE = "projectCode";

    public static final String POSITION = "Position";
    public static final String POSITIONS = "Positions";
    public static final String POSITION_ID = "positionId";
    public static final String POSITION_NAME = "positionName";

    public static final String EMPLOYEE = "Employee";
    public static final String EMPLOYEES = "Employees";
    public static final String EMPLOYEE_ID = "employeeId";

    public static final String PROJECT_LEAD = "Project Lead";
    public static final String PROJECT_LEADS = "Project Leads";
    public static final String PROJECT_LEAD_ID = "projectLeadId";

    public static final Map<String, String> entitySingleToPluralNames = new HashMap<String, String>() {{
        put(EMPLOYEE, EMPLOYEES);
        put(POSITION, POSITIONS);
        put(PROJECT, PROJECTS);
    }};
}
