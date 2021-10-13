package smevel.converters.impl;

import org.springframework.stereotype.Service;
import smevel.beans.*;
import smevel.converters.BeanToEntityConverter;
import smevel.entity.*;
import smevel.services.DateFormatter;

@Service
public class BeanToEntityConverterImpl implements BeanToEntityConverter {
    @Override
    public Employee convertEmployeeBeanToEmployee(EmployeeBean employeeBean) {
        if (employeeBean != null) {
            Employee employee = new Employee();
            employee.setEmployeeId(employeeBean.getEmployeeId());
            employee.setName(employeeBean.getName());
            employee.setFemale(employeeBean.getFemale());
            employee.setWorkExperience(employeeBean.getWorkExperience());
            employee.setPosition(convertPositionBeanToEntity(
                    employeeBean.getPositionBean()));
            employee.setProject(convertProjectBeanToEntity(
                    employeeBean.getProjectBean()));
            return employee;
        }
        return null;
    }

    @Override
    public Project convertProjectBeanToEntity(ProjectBean projectBean) {
        if (projectBean != null) {
            Project project = new Project();
            project.setProjectId(projectBean.getProjectId());
            project.setProjectName(projectBean.getProjectName());
            project.setProjectCode(projectBean.getProjectCode());
            return project;
        }
        return null;

    }

    @Override
    public Position convertPositionBeanToEntity(PositionBean positionBean) {
        if (positionBean != null) {
            Position position = new Position();
            position.setPositionId(positionBean.getPositionId());
            position.setPositionName(positionBean.getPositionName());
            return position;
        }
        return null;
    }

    @Override
    public ProjectLead convertProjectLeadBeanToEntity(ProjectLeadBean projectLeadBean) {
        if (projectLeadBean != null) {
            ProjectLead projectLead = new ProjectLead();
            projectLead.setProjectLeadId(projectLeadBean.getProjectLeadId());
            projectLead.setProject(convertProjectBeanToEntity(projectLeadBean.getProjectBean()));
            projectLead.setEmployee(convertEmployeeBeanToEmployee(projectLeadBean.getEmployeeBean()));

            return projectLead;
        }
        return null;
    }

    @Override
    public VacationLeave convertVacationLeaveBeanToVacationLeave(VacationLeaveBean vacationLeaveBean) {
        if (vacationLeaveBean != null) {
            VacationLeave vacationLeave = new VacationLeave();
            vacationLeave.setVacationId(vacationLeaveBean.getVacationId());
            vacationLeave.setVacationStartDate(DateFormatter
                    .getDateByFormattedStringBy(vacationLeaveBean.getVacationStartDate()));
            vacationLeave.setVacationEndDate(DateFormatter
                    .getDateByFormattedStringBy(vacationLeaveBean.getVacationEndDate()));
            vacationLeave.setEmployee(convertEmployeeBeanToEmployee(vacationLeaveBean.getEmployeeBean()));
            return vacationLeave;
        } else {
            return null;
        }
    }


}
