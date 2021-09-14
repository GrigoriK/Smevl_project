package sprinExamples.beans;

import lombok.Builder;
import lombok.Data;
import sprinExamples.beans.baseBeansStructure.BaseBean;

import java.util.UUID;

@Data
@Builder
public class EmployeeBean implements BaseBean {
    private UUID employeeId;

    private String name;

    private String female;

    private Short workExperience;

    private PositionBean positionBean;

    private ProjectBean projectBean;
}
