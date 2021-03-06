package smevel.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smevel.beans.EmployeeBean;
import smevel.beans.inputBean.InputEmployeeBean;
import smevel.beans.outputBean.OutputEmployeeBean;
import smevel.converters.EntityToBeanConverter;
import smevel.converters.EntityToOutputBeanConverter;
import smevel.converters.RequestBeanToEntityBeanImpl;
import smevel.converters.impl.BeanToEntityConverterImpl;
import smevel.entity.Employee;
import smevel.entity.Position;
import smevel.entity.Project;
import smevel.repo.EmployeesRepo;
import smevel.repo.PositionRepo;
import smevel.repo.ProjectsRepo;
import smevel.services.abst.BaseEntityService;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

import static smevel.constants.StringConstants.*;

@AllArgsConstructor
@Service
@Slf4j
public class EmployeeService extends BaseEntityService<Employee, EmployeeBean,
        InputEmployeeBean, OutputEmployeeBean, EmployeesRepo> {
    private final EmployeesRepo employeesRepo;
    private final ProjectsRepo projectsRepo;
    private final PositionRepo positionRepo;

    private final BeanToEntityConverterImpl beanToEntityConverter;
    private final EntityToBeanConverter entityToBeanConverter;
    private final RequestBeanToEntityBeanImpl requestBeanToEntityBean;
    private final EntityToOutputBeanConverter entityToOutputBeanConverter;

    @Transactional
    public ResponseEntity<OutputEmployeeBean> assignEmployeeToProject(String employeeId, String projectId) {

        return updateEntityWithResponse(() -> assignEmployeeToProjectWithResult(employeeId, projectId),
                getUpdateEntityMessage(employeeId));
    }

    @Transactional
    public ResponseEntity<OutputEmployeeBean> assignPositionToEmployee(String employeeId, String positionId) {

        return updateEntityWithResponse(() -> assignPositionToEmployeeWithResult(employeeId, positionId),
                getUpdateEntityMessage(employeeId));
    }

    @Override
    protected EmployeeBean convertEntityToBean(Employee entity) {
        return entityToBeanConverter.convertEmployeeToEmployeeBean(entity);
    }

    @Override
    protected Employee convertBeanToEntity(EmployeeBean bean) {
        return beanToEntityConverter.convertEmployeeBeanToEmployee(bean);
    }

    @Override
    protected EmployeeBean convertRequestBeanToEntityBean(InputEmployeeBean inputBean) {
        return requestBeanToEntityBean.convertRequestBeanToEntityBean(inputBean);
    }

    @Override
    protected void prepareEntityBeforeSave(Employee entity, InputEmployeeBean inputBean) {
        Optional.ofNullable(inputBean.getProjectId())
                .map(UUID::fromString)
                .ifPresent(projectId -> {
                    Optional<Project> optionalProject = projectsRepo.findById(projectId);
                    optionalProject.ifPresent(entity::setProject);
                });

        Optional.ofNullable(inputBean.getPositionId())
                .map(UUID::fromString)
                .ifPresent(positionId -> {
                    Optional<Position> optionalPosition = positionRepo.findById(positionId);
                    optionalPosition.ifPresent(entity::setPosition);
                });
    }

    @Override
    protected String getEntityName() {
        return EMPLOYEE;
    }

    @Override
    protected EmployeesRepo getJpaRepository() {
        return employeesRepo;
    }

    @Override
    protected void checkEntityBeforeSave(Employee entity) {

    }

    @Override
    protected OutputEmployeeBean convertEntityToOutPutBean(Employee entity) {
        return entityToOutputBeanConverter.convertEmployeeToOutputEmployeeBean(entity);
    }

    private OutputEmployeeBean assignEmployeeToProjectWithResult(String employeeId, String projectId) {
        Optional<Employee> optionalEmployee = employeesRepo.findById(UUID.fromString(employeeId));
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (projectId != null) {
                Optional<Project> optionalProject = projectsRepo.findById(UUID.fromString(projectId));
                if (optionalProject.isPresent()) {
                    employee.setProject(optionalProject.get());
                    employeesRepo.save(employee);
                } else {
                    log.info(String.format(CAN_NOT_FIND_ENTITY_BY_FIELD, PROJECT, PROJECT_ID, projectId));
                }
            }
            return convertEntityToOutPutBean(employee);
        }
        log.info(String.format(CAN_NOT_FIND_ENTITY_BY_FIELD, EMPLOYEE, EMPLOYEE_ID, employeeId));
        return null;
    }

    private OutputEmployeeBean assignPositionToEmployeeWithResult(String employeeId, String positionId) {
        Optional<Employee> optionalEmployee = employeesRepo.findById(UUID.fromString(employeeId));
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (positionId != null) {
                Optional<Position> optionalPosition = positionRepo.findById(UUID.fromString(positionId));
                if (optionalPosition.isPresent()) {
                    employee.setPosition(optionalPosition.get());
                    employeesRepo.save(employee);
                } else {
                    log.info(String.format(CAN_NOT_FIND_ENTITY_BY_FIELD, POSITION, POSITION_ID, positionId));
                }
            }
            return convertEntityToOutPutBean(employee);
        }
        log.info(String.format(CAN_NOT_FIND_ENTITY_BY_FIELD, EMPLOYEE, EMPLOYEE_ID, employeeId));
        return null;
    }

}
