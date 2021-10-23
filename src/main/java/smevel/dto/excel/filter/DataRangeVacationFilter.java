package smevel.dto.excel.filter;

import lombok.Data;

import java.util.Date;

@Data
public class DataRangeVacationFilter {
    private Date vacationStartDate;
    private Date vacationEndDate;
}
