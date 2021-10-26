package smevel.converters.impl;

import org.springframework.stereotype.Component;
import smevel.beans.outputBean.*;
import smevel.converters.EntityToOutputBeanConverter;
import smevel.entity.*;
import smevel.services.DateFormatter;

import java.util.Optional;
import java.util.UUID;

@Component
public class EntityToOutputBeanConverterImpl implements EntityToOutputBeanConverter {
    @Override
    public OutputEmployeeBean convertEmployeeToOutputEmployeeBean(Employee employee) {
        return Optional.ofNullable(employee)
                .map(empl -> OutputEmployeeBean.builder()
                        .employeeId(empl.getEmployeeId().toString())
                        .name(empl.getName())
                        .female(empl.getFemale())
                        .positionId(Optional.ofNullable(empl.getPosition())
                                .map(Position::getPositionId)
                                .map(UUID::toString)
                                .orElse(null))
                        .projectId(Optional.ofNullable(empl.getProject())
                                .map(Project::getProjectId)
                                .map(UUID::toString)
                                .orElse(null))
                        .workExperience(empl.getWorkExperience())
                        .build())
                .orElse(null);
    }

    @Override
    public OutputProjectBean convertProjectToOutputEProjectBean(Project project) {
        return Optional.ofNullable(project)
                .map(pr -> OutputProjectBean.builder()
                        .projectId(pr.getProjectId().toString())
                        .projectName(pr.getProjectName())
                        .projectCode(pr.getProjectCode())
                        .projectLeadId(Optional.ofNullable(pr.getProjectLead())
                                .map(ProjectLead::getProjectLeadId)
                                .map(UUID::toString)
                                .orElse(null))
                        .build())
                .orElse(null);
    }

    @Override
    public OutputPositionBean convertPositionToOutputEPositionBean(Position position) {
        return Optional.ofNullable(position)
                .map(pos -> OutputPositionBean.builder()
                        .positionId(pos.getPositionId().toString())
                        .positionName(pos.getPositionName())
                        .build())
                .orElse(null);
    }

    @Override
    public OutputProjectLeadBean convertProjectLeadToOutputEProjectLeadBean(ProjectLead projectLead) {
        return Optional.ofNullable(projectLead)
                .map(projectL -> OutputProjectLeadBean.builder()
                        .projectLeadId(projectL.getProjectLeadId().toString())
                        .projectId(Optional.ofNullable(projectL.getProject())
                                .map(Project::getProjectId)
                                .map(UUID::toString)
                                .orElse(null))
                        .employeeId(Optional.ofNullable(projectL.getEmployee())
                                .map(Employee::getEmployeeId)
                                .map(UUID::toString)
                                .orElse(null))
                        .build())
                .orElse(null);
    }

    @Override
    public OutputVacationLeaveBean convertVacationLeaveToOutputEVacationLeaveBean(VacationLeave vacationLeave) {
        return Optional.ofNullable(vacationLeave)
                .map(vacation -> OutputVacationLeaveBean.builder()
                        .vacationId(vacation.getVacationId().toString())
                        .vacationStartDate(DateFormatter
                                .getFormattedStringByDate(vacation.getVacationStartDate()))
                        .vacationEndDate(DateFormatter
                                .getFormattedStringByDate(vacation.getVacationEndDate()))
                        .employeeId(Optional.ofNullable(vacation.getEmployee())
                                .map(Employee::getEmployeeId)
                                .map(UUID::toString)
                                .orElse(null))
                        .build())
                .orElse(null);
    }
}
