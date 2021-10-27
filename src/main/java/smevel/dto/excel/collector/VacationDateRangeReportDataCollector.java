package smevel.dto.excel.collector;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smevel.beans.EmployeeBean;
import smevel.beans.PositionBean;
import smevel.beans.ProjectBean;
import smevel.beans.VacationLeaveBean;
import smevel.dto.excel.data.VacationDateRangeReportData;
import smevel.dto.excel.filter.DateRangeVacationFilter;
import smevel.services.impl.VacationLeaveService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacationDateRangeReportDataCollector {

    private final VacationLeaveService vacationLeaveService;

    @Transactional
    public Collection<VacationDateRangeReportData> collectData(DateRangeVacationFilter filter) {
        Collection<VacationLeaveBean> vlBeans = vacationLeaveService
                .getVacationsBeansByDateRanges(filter.getVacationStartDate(), filter.getVacationEndDate());
        return vlBeans.stream()
                .map(this::getVacationDateRangeReportDataByVacationLeaveBean)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private VacationDateRangeReportData getVacationDateRangeReportDataByVacationLeaveBean(VacationLeaveBean vacationLeaveBean) {
        EmployeeBean employeeBean = vacationLeaveBean.getEmployeeBean();
        if (employeeBean == null) {
            return null;
        } else {
            String nameSurname = employeeBean.getName() + " " + employeeBean.getFemale();
            PositionBean positionBean = employeeBean.getPositionBean();
            ProjectBean projectBean = employeeBean.getProjectBean();
            if (positionBean == null || projectBean == null) {
                return null;
            } else {
                Date vacationStartDate = vacationLeaveBean.getVacationStartDate();
                Date vacationEndDate = vacationLeaveBean.getVacationEndDate();
                VacationDateRangeReportData vacationDateRangeReportData = new VacationDateRangeReportData();
                vacationDateRangeReportData.setNameAndSurname(nameSurname);
                vacationDateRangeReportData.setVlStartDate(vacationStartDate);
                vacationDateRangeReportData.setVlEndDate(vacationEndDate);
                vacationDateRangeReportData.setVlDuration(getVlDuration(vacationStartDate, vacationEndDate));
                vacationDateRangeReportData.setPositionName(positionBean.getPositionName());
                vacationDateRangeReportData.setProjectName(projectBean.getProjectName());
                return vacationDateRangeReportData;
            }
        }
    }


    public int getVlDuration(Date vlStartDate, Date vlEndDate) {
        long diff = vlEndDate.getTime() - vlStartDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

}
