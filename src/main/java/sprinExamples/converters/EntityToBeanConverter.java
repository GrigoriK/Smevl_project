package sprinExamples.converters;

import sprinExamples.beans.EmployeeBean;
import sprinExamples.beans.PositionBean;
import sprinExamples.beans.ProjectBean;
import sprinExamples.beans.ProjectLeadBean;
import sprinExamples.entity.Employee;
import sprinExamples.entity.Position;
import sprinExamples.entity.Project;
import sprinExamples.entity.ProjectLead;

public interface EntityToBeanConverter {

    EmployeeBean convertEmployeeToEmployeeBean(Employee employee);

    ProjectBean convertProjectToProjectBean(Project project);

    PositionBean convertPositionToPositionBean(Position position);

    ProjectLeadBean convertProjectLeadToProjectLeadBean(ProjectLead projectLead);
}
