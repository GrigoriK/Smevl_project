package sprinExamples.beans.inputBean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputProjectLeadBean {
    private String employeeId;

    private String projectId;
}
