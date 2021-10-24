package smevel.beans;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class VacationLeaveBean {

    private UUID vacationId;

    private Date vacationStartDate;

    private Date vacationEndDate;

    private EmployeeBean employeeBean;
}
