package smevel.converters.impl;

import org.springframework.stereotype.Service;
import smevel.beans.*;
import smevel.converters.EntityToBeanConverter;
import smevel.entity.*;

import java.util.Optional;

@Service
public class EntityToBeanConverterImpl implements EntityToBeanConverter {
    @Override
    public EmployeeBean convertEmployeeToEmployeeBean(Employee employee) {
        return Optional.ofNullable(employee)
                .map(empl -> EmployeeBean.builder()
                        .employeeId(empl.getEmployeeId())
                        .name(empl.getName())
                        .female(empl.getFemale())
                        .positionBean(convertPositionToPositionBean(empl.getPosition()))
                        .projectBean(convertProjectToProjectBean(empl.getProject()))
                        .workExperience(empl.getWorkExperience())
                        .build())
                .orElse(null);
    }

    @Override
    public ProjectBean convertProjectToProjectBean(Project project) {
        return Optional.ofNullable(project)
                .map(pr -> ProjectBean.builder()
                        .projectId(pr.getProjectId())
                        .projectName(pr.getProjectName())
                        .projectCode(pr.getProjectCode())
                        .build())
                .orElse(null);
    }

    @Override
    public PositionBean convertPositionToPositionBean(Position position) {
        return Optional.ofNullable(position)
                .map(pos -> PositionBean.builder()
                        .positionId(pos.getPositionId())
                        .positionName(pos.getPositionName())
                        .build())
                .orElse(null);
    }

    @Override
    public ProjectLeadBean convertProjectLeadToProjectLeadBean(ProjectLead projectLead) {
        return Optional.ofNullable(projectLead)
                .map(projectL -> ProjectLeadBean.builder()
                        .projectLeadId(projectL.getProjectLeadId())
                        .projectBean(convertProjectToProjectBean(projectL.getProject()))
                        .employeeBean(convertEmployeeToEmployeeBean(projectL.getEmployee()))
                        .build())
                .orElse(null);

    }

    @Override
    public VacationLeaveBean convertVacationLeaveToVacationLeaveBean(VacationLeave vacationLeave) {
        return Optional.ofNullable(vacationLeave)
                .map(vacation -> VacationLeaveBean.builder()
                        .vacationId(vacationLeave.getVacationId())
                        .vacationStartDate(vacationLeave.getVacationStartDate())
                        .vacationEndDate(vacationLeave.getVacationEndDate())
                        .employeeBean(convertEmployeeToEmployeeBean(vacationLeave.getEmployee()))
                        .build())
                .orElse(null);
    }
}
