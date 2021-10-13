package smevel.beans.excel;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeeVacationLeaveBean {
    private String NameAndSurname;
    private Date vlStartDate;
    private Date vlEndDate;
    private int vlDuration;
    private String positionName;

}
