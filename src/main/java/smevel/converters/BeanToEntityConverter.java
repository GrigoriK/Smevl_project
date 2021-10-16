package smevel.converters;

import smevel.beans.*;
import smevel.entity.*;

public interface BeanToEntityConverter {

    Employee convertEmployeeBeanToEmployee(EmployeeBean employeeBean);

    Project convertProjectBeanToEntity(ProjectBean projectBean);

    Position convertPositionBeanToEntity(PositionBean positionBean);

    ProjectLead convertProjectLeadBeanToEntity(ProjectLeadBean projectLeadBean);

    VacationLeave convertVacationLeaveBeanToVacationLeave(VacationLeaveBean vacationLeaveBean);
}
