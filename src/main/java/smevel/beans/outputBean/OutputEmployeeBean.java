package smevel.beans.outputBean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputEmployeeBean {
    private String employeeId;
    private String name;

    private String female;

    private Short workExperience;

    private String positionId;

    private String projectId;
}
