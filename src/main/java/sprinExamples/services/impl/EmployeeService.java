package sprinExamples.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprinExamples.beans.EmployeeBean;
import sprinExamples.beans.inputBean.InputEmployeeBean;
import sprinExamples.converters.EntityToBeanConverter;
import sprinExamples.converters.impl.BeanToEntityConverterImpl;
import sprinExamples.entity.Employee;
import sprinExamples.entity.Position;
import sprinExamples.entity.Project;
import sprinExamples.repo.EmployeesRepo;
import sprinExamples.repo.PositionRepo;
import sprinExamples.repo.ProjectsRepo;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

import static sprinExamples.constants.StringConstants.*;

@AllArgsConstructor
@Service
@Slf4j
public class EmployeeService extends BaseEntityService<Employee, EmployeeBean,
        InputEmployeeBean, EmployeesRepo> {
    private final EmployeesRepo employeesRepo;
    private final ProjectsRepo projectsRepo;
    private final PositionRepo positionRepo;
    private final BeanToEntityConverterImpl beanToEntityConverter;
    private final EntityToBeanConverter entityToBeanConverter;

    @Transactional
    public ResponseEntity<EmployeeBean> assignEmployeeToProject(String employeeId, String projectId) {

        return updateEntityWithResponse(() -> assignEmployeeToProjectWithResult(employeeId, projectId),
                getUpdateEntityMessage(employeeId));
    }

    @Transactional
    public ResponseEntity<EmployeeBean> assignPositionToEmployee(String employeeId, String positionId) {

        return updateEntityWithResponse(() -> assignPositionToEmployeeWithResult(employeeId, positionId),
                getUpdateEntityMessage(employeeId));
    }

    @Override
    EmployeeBean convertEntityToBean(Employee entity) {
        return entityToBeanConverter.convertEmployeeToEmployeeBean(entity);
    }

    @Override
    Employee convertBeanToEntity(EmployeeBean bean) {
        return beanToEntityConverter.convertEmployeeBeanToEmployee(bean);
    }

    @Override
    EmployeeBean convertRequestBeanToEntityBean(InputEmployeeBean inputBean) {
        return EmployeeBean
                .builder()
                .name(inputBean.getName())
                .female(inputBean.getFemale())
                .workExperience(inputBean.getWorkExperience())
                .build();
    }

    @Override
    void prepareEntityBeforeSave(Employee entity, InputEmployeeBean inputBean) {
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
    String getEntityName() {
        return EMPLOYEE;
    }

    @Override
    EmployeesRepo getJpaRepository() {
        return employeesRepo;
    }

    private EmployeeBean assignEmployeeToProjectWithResult(String employeeId, String projectId) {
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
            return entityToBeanConverter.convertEmployeeToEmployeeBean(employee);
        }
        log.info(String.format(CAN_NOT_FIND_ENTITY_BY_FIELD, EMPLOYEE, EMPLOYEE_ID, employeeId));
        return null;
    }

    private EmployeeBean assignPositionToEmployeeWithResult(String employeeId, String positionId) {
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
            return entityToBeanConverter.convertEmployeeToEmployeeBean(employee);
        }
        log.info(String.format(CAN_NOT_FIND_ENTITY_BY_FIELD, EMPLOYEE, EMPLOYEE_ID, employeeId));
        return null;
    }

}
