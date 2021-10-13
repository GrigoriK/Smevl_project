package smevel.converters;

import smevel.beans.*;
import smevel.entity.*;

public interface EntityToBeanConverter {

    EmployeeBean convertEmployeeToEmployeeBean(Employee employee);

    ProjectBean convertProjectToProjectBean(Project project);

    PositionBean convertPositionToPositionBean(Position position);

    ProjectLeadBean convertProjectLeadToProjectLeadBean(ProjectLead projectLead);
    VacationLeaveBean convertVacationLeaveToVacationLeaveBean(VacationLeave vacationLeave);
}
