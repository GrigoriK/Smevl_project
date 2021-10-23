package smevel.dto.excel.data;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AllVacationReportData {
    private String nameAndSurname;
    private Date vlStartDate;
    private Date vlEndDate;
    //days
    private int vlDuration;
    private String positionName;
}
