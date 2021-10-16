package smevel.converters;

import smevel.beans.outputBean.*;
import smevel.entity.*;

public interface EntityToOutputBeanConverter {

    OutputEmployeeBean convertEmployeeToOutputEmployeeBean(Employee employee);

    OutputProjectBean convertProjectToOutputEProjectBean(Project project);

    OutputPositionBean convertPositionToOutputEPositionBean(Position position);

    OutputProjectLeadBean convertProjectLeadToOutputEProjectLeadBean(ProjectLead projectLead);

    OutputVacationLeaveBean convertVacationLeaveToOutputEVacationLeaveBean(VacationLeave vacationLeave);
}
