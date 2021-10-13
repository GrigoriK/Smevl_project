package smevel.beans.inputBean;

import lombok.Data;

import java.util.Date;

@Data
public class InputVacationLeaveBean {


    private Date vacationStartDate;

    private Date vacationEndDate;

    private String employeeId;
}
