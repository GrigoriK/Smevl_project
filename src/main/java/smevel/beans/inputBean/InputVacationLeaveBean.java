package smevel.beans.inputBean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputVacationLeaveBean {


    private String vacationStartDate;

    private String vacationEndDate;

    private String employeeId;
}
