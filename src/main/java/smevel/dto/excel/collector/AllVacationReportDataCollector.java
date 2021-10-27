package smevel.dto.excel.collector;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smevel.beans.EmployeeBean;
import smevel.beans.PositionBean;
import smevel.beans.ProjectBean;
import smevel.beans.VacationLeaveBean;
import smevel.dto.excel.data.AllVacationReportData;
import smevel.dto.excel.data.BaseVacationLeaveData;
import smevel.services.impl.VacationLeaveService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllVacationReportDataCollector {

    private final VacationLeaveService vacationLeaveService;

    @Transactional
    public Collection<AllVacationReportData> collectData() {
        Collection<VacationLeaveBean> vlBeans = vacationLeaveService.getAllEntityBeans();
        return vlBeans.stream()
                .map(this::getAllVacationReportDataByVacationLeaveBean)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private AllVacationReportData getAllVacationReportDataByVacationLeaveBean(VacationLeaveBean vacationLeaveBean) {
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
                AllVacationReportData allVacationReportData = new AllVacationReportData();
                allVacationReportData.setNameAndSurname(nameSurname);
                allVacationReportData.setVlStartDate(vacationStartDate);
                allVacationReportData.setVlEndDate(vacationEndDate);
                allVacationReportData.setVlDuration(getVlDuration(vacationStartDate, vacationEndDate));
                allVacationReportData.setPositionName(positionBean.getPositionName());
                allVacationReportData.setProjectName(projectBean.getProjectName());
                return allVacationReportData;
            }
        }
    }


    public int getVlDuration(Date vlStartDate, Date vlEndDate) {
        long diff = vlEndDate.getTime() - vlStartDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
