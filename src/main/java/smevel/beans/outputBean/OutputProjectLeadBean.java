package smevel.beans.outputBean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputProjectLeadBean {
    private String projectLeadId;
    private String employeeId;

    private String projectId;
}
