package smevel.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import smevel.beans.ProjectLeadBean;
import smevel.beans.inputBean.InputProjectLeadBean;
import smevel.converters.EntityToBeanConverter;
import smevel.converters.RequestBeanToEntityBeanImpl;
import smevel.converters.impl.BeanToEntityConverterImpl;
import smevel.entity.Employee;
import smevel.entity.Project;
import smevel.entity.ProjectLead;
import smevel.repo.EmployeesRepo;
import smevel.repo.ProjectLeadRepo;
import smevel.repo.ProjectsRepo;
import smevel.services.abst.BaseEntityService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static smevel.constants.StringConstants.*;

@Service
@Slf4j
@AllArgsConstructor
public class ProjectLeadService extends BaseEntityService<ProjectLead, ProjectLeadBean,
        InputProjectLeadBean, ProjectLeadRepo> {


    private final ProjectLeadRepo projectLeadRepo;
    private final BeanToEntityConverterImpl beanToEntityConverter;
    private final EntityToBeanConverter entityToBeanConverter;
    private final EmployeesRepo employeesRepo;
    private final ProjectsRepo projectsRepo;
    private final RequestBeanToEntityBeanImpl requestBeanToEntityBean;

    @Transactional
    public ResponseEntity<ProjectLeadBean> madeEmployeeLeadOfProject(String employeeId, String projectId) {
        return updateEntityWithResponse(() -> madeEmployeeLeadOfProjectWithResult(employeeId, projectId),
                getUpdateEntityMessage(employeeId));
    }

    @Transactional
    public ResponseEntity<Collection<ProjectLeadBean>> getProjectLeadByProjectId(String projectId) {
        return getCollectionOfBean(() -> getProjectLeadBeanByProjectId(projectId),
                getMessageByFieldNameAndValue(PROJECT_ID, projectId));
    }

    @Override
    protected ProjectLead getEntityByEntityBean(ProjectLeadBean bean, InputProjectLeadBean inputBean) {

        Collection<ProjectLead> existingProjects = projectLeadRepo.findByProjectIdOrEmployeeId(UUID.fromString(inputBean.getProjectId()),
                UUID.fromString(inputBean.getEmployeeId()));
        if (!CollectionUtils.isEmpty(existingProjects)) {
            throw new IllegalArgumentException("Lead for this project is already exist or employee is" +
                    " already project lead for another project");
        }
        return super.getEntityByEntityBean(bean, inputBean);
    }

    @Override
    protected ProjectLeadBean convertEntityToBean(ProjectLead entity) {
        return entityToBeanConverter.convertProjectLeadToProjectLeadBean(entity);
    }

    @Override
    protected ProjectLead convertBeanToEntity(ProjectLeadBean bean) {
        return beanToEntityConverter.convertProjectLeadBeanToEntity(bean);
    }

    @Override
    protected ProjectLeadBean convertRequestBeanToEntityBean(InputProjectLeadBean inputBean) {
        return requestBeanToEntityBean.convertRequestBeanToEntityBean(inputBean);
    }

    @Override
    protected void prepareEntityBeforeSave(ProjectLead entity, InputProjectLeadBean inputBean) {
        Optional.ofNullable(inputBean.getProjectId())
                .map(UUID::fromString)
                .ifPresent(projectId -> {
                    Optional<Project> optionalProject = projectsRepo.findById(projectId);
                    optionalProject.ifPresent(entity::setProject);
                });
        Optional.ofNullable(inputBean.getEmployeeId())
                .map(UUID::fromString)
                .ifPresent(employeeId -> {
                    Optional<Employee> optionalEmployee = employeesRepo.findById(employeeId);
                    optionalEmployee.ifPresent(entity::setEmployee);
                });

    }

    @Override
    protected String getEntityName() {
        return PROJECT_LEAD;
    }

    @Override
    protected ProjectLeadRepo getJpaRepository() {
        return projectLeadRepo;
    }

    private ProjectLeadBean madeEmployeeLeadOfProjectWithResult(String employeeId, String projectId) {
        Optional<Employee> optionalEmployee = employeesRepo.findById(UUID.fromString(employeeId));
        Optional<Project> optionalProject;
        Optional<ProjectLead> projectLeadById = projectLeadRepo.findById(UUID.fromString(projectId));
        ProjectLead projectLead = projectLeadById.orElseGet(ProjectLead::new);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (employee.getProject() != null) {
                UUID employeeProjectId = employee.getProject().getProjectId();
                if (employeeProjectId.equals(UUID.fromString(projectId))) {
                    log.info("Employee will be project lead of  project {}", employeeProjectId.toString());
                    optionalProject = projectsRepo.findById(UUID.fromString(projectId));
                } else {
                    log.info("Employee was assigned to project {}", employeeProjectId.toString());
                    log.info("Employee will be assigned to project {}", projectId);
                    optionalProject = projectsRepo.findById(UUID.fromString(projectId));
                    optionalProject.ifPresent(employee::setProject);
                    employeesRepo.save(employee);
                }
                optionalProject.ifPresent(projectLead::setProject);
                projectLead.setEmployee(employee);
                ProjectLead savedProjectLeadEntity = projectLeadRepo.save(projectLead);
                return convertEntityToBean(savedProjectLeadEntity);
            } else {
                log.info("Employee isn't assigned to any project");
                return null;
            }
        } else {
            log.info(getMessageByFieldNameAndValue(EMPLOYEE_ID, employeeId));
            return null;
        }
    }

    private Collection<ProjectLeadBean> getProjectLeadBeanByProjectId(String projectId) {
        Collection<ProjectLead> projectLeads = projectLeadRepo
                .findByProjectId(UUID.fromString(projectId));
        return projectLeads.stream().map(this::convertEntityToBean).collect(Collectors.toList());
    }

}
