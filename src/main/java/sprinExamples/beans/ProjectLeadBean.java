package sprinExamples.beans;

import lombok.Builder;
import lombok.Data;
import sprinExamples.beans.baseBeansStructure.BaseBean;

import java.util.UUID;

@Data
@Builder
public class ProjectLeadBean implements BaseBean {
    private UUID projectLeadId;

    private EmployeeBean employeeBean;

    private ProjectBean projectBean;
}
