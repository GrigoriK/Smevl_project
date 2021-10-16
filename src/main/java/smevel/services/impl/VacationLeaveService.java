package smevel.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import smevel.beans.VacationLeaveBean;
import smevel.beans.inputBean.InputVacationLeaveBean;
import smevel.constants.StringConstants;
import smevel.converters.EntityToBeanConverter;
import smevel.converters.RequestBeanToEntityBeanImpl;
import smevel.converters.impl.BeanToEntityConverterImpl;
import smevel.entity.Employee;
import smevel.entity.Project;
import smevel.entity.VacationLeave;
import smevel.repo.EmployeesRepo;
import smevel.repo.ProjectsRepo;
import smevel.repo.VacationLeaveRepo;
import smevel.services.DateFormatter;
import smevel.services.abst.BaseEntityService;

import javax.transaction.Transactional;
import java.util.*;

import static smevel.constants.StringConstants.VACATION_LEAVE;

@Service
@Slf4j
@RequiredArgsConstructor
public class VacationLeaveService extends BaseEntityService<VacationLeave,
        VacationLeaveBean, InputVacationLeaveBean, VacationLeaveRepo> {

    private final VacationLeaveRepo vacationLeaveRepo;
    private final EmployeesRepo employeesRepo;
    private final ProjectsRepo projectsRepo;

    private final BeanToEntityConverterImpl beanToEntityConverter;
    private final EntityToBeanConverter entityToBeanConverter;
    private final RequestBeanToEntityBeanImpl requestBeanToEntityBean;

    @Transactional
    public ResponseEntity<Collection<VacationLeaveBean>> getVacationsByRange(String startDateString,
                                                                             String endDateString) {
        return getCollectionOfBean(() ->
                        getEntitiesWithSupplier(() -> getVacationsByDateRanges(startDateString, endDateString)),
                getMessageByDateRange(startDateString, endDateString));
    }


    @Transactional
    public ResponseEntity<Collection<VacationLeaveBean>> getVacationsListByEmployeeId(String employeeId) {
        return getCollectionOfBean(() ->
                        getEntitiesWithSupplier(() -> getVacationsByEmployeeId(employeeId)),
                getMessageByFieldWithId(StringConstants.EMPLOYEE_ID, employeeId));
    }


    @Transactional
    public ResponseEntity<Collection<VacationLeaveBean>> getVacationsListByProjectId(String projectId) {
        return getCollectionOfBean(() ->
                        getEntitiesWithSupplier(() -> getVacationsByProjectId(projectId)),
                getMessageByFieldWithId(StringConstants.PROJECT_ID, projectId));
    }

    @Override
    protected VacationLeaveBean convertEntityToBean(VacationLeave entity) {

        return entityToBeanConverter.convertVacationLeaveToVacationLeaveBean(entity);
    }

    @Override
    protected VacationLeave convertBeanToEntity(VacationLeaveBean bean) {

        return beanToEntityConverter.convertVacationLeaveBeanToVacationLeave(bean);
    }

    @Override
    protected VacationLeaveBean convertRequestBeanToEntityBean(InputVacationLeaveBean inputBean) {

        return requestBeanToEntityBean.convertRequestBeanToEntityBean(inputBean);
    }

    @Override
    protected void prepareEntityBeforeSave(VacationLeave entity, InputVacationLeaveBean inputBean) {
        Optional.ofNullable(inputBean.getEmployeeId())
                .map(UUID::fromString)
                .ifPresent(employeeId -> {
                    Optional<Employee> optionalProject = employeesRepo.findById(employeeId);
                    optionalProject.ifPresent(entity::setEmployee);
                });
    }

    @Override
    protected String getEntityName() {
        return VACATION_LEAVE;
    }

    @Override
    protected VacationLeaveRepo getJpaRepository() {
        return vacationLeaveRepo;
    }

    @Override
    protected void checkEntityBeforeSave(VacationLeave entity) {
        Date vacationStartDate = entity.getVacationStartDate();
        Date vacationEndDate = entity.getVacationEndDate();
        if (vacationStartDate.compareTo(vacationEndDate) >= 0) {
            throw new IllegalArgumentException("Vacation start after or equals vacation end date");
        }
    }

    private Collection<VacationLeave> getVacationsByDateRanges(String startDateString, String endDateString) {
        Date startDate = DateFormatter.getDateByFormattedStringBy(startDateString);
        Date endDate = DateFormatter.getDateByFormattedStringBy(endDateString);
        return vacationLeaveRepo.findByDateRange(startDate, endDate);

    }

    private Collection<VacationLeave> getVacationsByEmployeeId(String employeeId) {
        Optional<Employee> employee = employeesRepo.findById(UUID.fromString(employeeId));

        if (employee.isPresent()) {
            return vacationLeaveRepo.findByEmployee(employee.get());

        } else {
            log.info("Couldn't find employee by id: {}", employeeId);
            return Collections.emptyList();
        }

    }


    private Collection<VacationLeave> getVacationsByProjectId(String projectId) {
        Optional<Project> projectOptional = projectsRepo.findById(UUID.fromString(projectId));

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            //todo research why project.getEmployees() doesn't work
            Collection<Employee> employees = employeesRepo.findByProject(project);
            if (!CollectionUtils.isEmpty(employees)) {
                return vacationLeaveRepo.findByEmployeeIn(employees);
            }

        } else {
            log.info("Couldn't find project by id: {}", projectId);
        }
        return Collections.emptyList();
    }

    private String getMessageByDateRange(String startDateString, String endDateString) {
        return String.format(StringConstants.CAN_NOT_FIND_ENTITIES_BY_DATE_RANGE,
                startDateString,
                endDateString);
    }

    private String getMessageByFieldWithId(String fieldName, String id) {
        return String.format(StringConstants.CAN_NOT_FIND_ENTITIES_BY_FIELD, fieldName, id);
    }

}
