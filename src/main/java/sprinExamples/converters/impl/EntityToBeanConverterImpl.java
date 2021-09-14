package sprinExamples.converters.impl;

import org.springframework.stereotype.Service;
import sprinExamples.beans.EmployeeBean;
import sprinExamples.beans.PositionBean;
import sprinExamples.beans.ProjectBean;
import sprinExamples.beans.ProjectLeadBean;
import sprinExamples.converters.EntityToBeanConverter;
import sprinExamples.entity.Employee;
import sprinExamples.entity.Position;
import sprinExamples.entity.Project;
import sprinExamples.entity.ProjectLead;

@Service
public class EntityToBeanConverterImpl implements EntityToBeanConverter {
    @Override
    public EmployeeBean convertEmployeeToEmployeeBean(Employee employee) {
        return EmployeeBean.builder()
                .employeeId(employee.getEmployeeId())
                .name(employee.getName())
                .female(employee.getFemale())
                .positionBean(convertPositionToPositionBean(employee.getPosition()))
                .projectBean(convertProjectToProjectBean(employee.getProject()))
                .workExperience(employee.getWorkExperience())
                .build();
    }

    @Override
    public ProjectBean convertProjectToProjectBean(Project project) {

        return ProjectBean.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .projectCode(project.getProjectCode())
                .build();
    }

    @Override
    public PositionBean convertPositionToPositionBean(Position position) {

        return PositionBean.builder()
                .positionId(position.getPositionId())
                .positionName(position.getPositionName())
                .build();
    }

    @Override
    public ProjectLeadBean convertProjectLeadToProjectLeadBean(ProjectLead projectLead) {
        if (projectLead != null) {
            return ProjectLeadBean.builder()
                    .projectLeadId(projectLead.getProjectLeadId())
                    .projectBean(convertProjectToProjectBean(projectLead.getProject()))
                    .employeeBean(convertEmployeeToEmployeeBean(projectLead.getEmployee()))
                    .build();
        }
        return null;
    }
}
