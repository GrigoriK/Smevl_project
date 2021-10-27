package smevel.dto.excel.data;

import lombok.Data;

import java.util.Date;

@Data
public class BaseVacationLeaveData {
    private String nameAndSurname;
    private Date vlStartDate;
    private Date vlEndDate;
    //days
    private int vlDuration;
    private String projectName;
    private String positionName;

}
