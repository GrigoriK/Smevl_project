package smevel.dto.excel.collector;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smevel.beans.EmployeeBean;
import smevel.beans.PositionBean;
import smevel.beans.VacationLeaveBean;
import smevel.dto.excel.data.VacationIntersectionReportData;
import smevel.services.impl.VacationLeaveService;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacationIntersectionReportDataCollector {
    private final VacationLeaveService vacationLeaveService;

    public Collection<VacationIntersectionReportData> collectData() {
        Collection<VacationLeaveBean> vlBeans = vacationLeaveService.getAllEntityBeans();
        return vlBeans.stream()
                .map(this::getVacationIntersectionReportDataByVacationLeaveBean)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private VacationIntersectionReportData getVacationIntersectionReportDataByVacationLeaveBean(VacationLeaveBean vacationLeaveBean) {
        EmployeeBean employeeBean = vacationLeaveBean.getEmployeeBean();
        if (employeeBean == null) {
            return null;
        } else {
            String nameSurname = employeeBean.getName() + " " + employeeBean.getFemale();
            PositionBean positionBean = employeeBean.getPositionBean();
            if (positionBean == null) {
                return null;
            } else {
                String positionName = positionBean.getPositionName();
                Date vacationStartDate = vacationLeaveBean.getVacationStartDate();
                Date vacationEndDate = vacationLeaveBean.getVacationEndDate();
                return VacationIntersectionReportData.builder()
                        .nameAndSurname(nameSurname)
                        .vlStartDate(vacationStartDate)
                        .vlEndDate(vacationEndDate)
                        .vlDuration(getVlDuration(vacationStartDate, vacationEndDate))
                        .positionName(positionName)
                        .build();
            }
        }
    }


    public int getVlDuration(Date vlStartDate, Date vlEndDate) {
        long diff = vlStartDate.getTime() - vlEndDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
