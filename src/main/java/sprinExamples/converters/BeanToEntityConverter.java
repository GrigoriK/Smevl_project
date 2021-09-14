package sprinExamples.converters;

import sprinExamples.beans.EmployeeBean;
import sprinExamples.beans.PositionBean;
import sprinExamples.beans.ProjectBean;
import sprinExamples.beans.ProjectLeadBean;
import sprinExamples.entity.Employee;
import sprinExamples.entity.Position;
import sprinExamples.entity.Project;
import sprinExamples.entity.ProjectLead;

public interface BeanToEntityConverter {

    Employee convertEmployeeBeanToEmployee(EmployeeBean employeeBean);

    Project convertProjectBeanToEntity(ProjectBean projectBean);

    Position convertPositionBeanToEntity(PositionBean positionBean);

    ProjectLead convertProjectLeadBeanToEntity(ProjectLeadBean projectLeadBean);
}
