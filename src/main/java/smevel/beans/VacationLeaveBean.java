package smevel.beans;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class VacationLeaveBean {

    private UUID vacationId;

    private String vacationStartDate;

    private String vacationEndDate;

    private EmployeeBean employeeBean;
}
