package smevel.beans.outputBean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputVacationLeaveBean {

    private String vacationId;
    private String vacationStartDate;

    private String vacationEndDate;

    private String employeeId;
}
