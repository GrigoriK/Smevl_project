package smevel.beans;

import lombok.Builder;
import lombok.Data;
import smevel.beans.baseBeansStructure.BaseBean;

import java.util.UUID;

@Data
@Builder
public class ProjectLeadBean implements BaseBean {
    private UUID projectLeadId;

    private EmployeeBean employeeBean;

    private ProjectBean projectBean;
}
