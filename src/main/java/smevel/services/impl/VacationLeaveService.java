package smevel.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smevel.beans.VacationLeaveBean;
import smevel.beans.inputBean.InputVacationLeaveBean;
import smevel.constants.StringConstants;
import smevel.converters.EntityToBeanConverter;
import smevel.converters.RequestBeanToEntityBeanImpl;
import smevel.converters.impl.BeanToEntityConverterImpl;
import smevel.entity.Employee;
import smevel.entity.VacationLeave;
import smevel.repo.EmployeesRepo;
import smevel.repo.VacationLeaveRepo;
import smevel.services.DateFormatter;
import smevel.services.abst.BaseEntityService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static smevel.constants.StringConstants.VACATION_LEAVE;

@Service
@RequiredArgsConstructor
public class VacationLeaveService extends BaseEntityService<VacationLeave,
        VacationLeaveBean, InputVacationLeaveBean, VacationLeaveRepo> {

    private final VacationLeaveRepo vacationLeaveRepo;
    private final EmployeesRepo employeesRepo;

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

    private String getMessageByDateRange(String startDateString, String endDateString) {
        return String.format(StringConstants.CAN_NOT_FIND_ENTITIES_BY_DATE_RANGE,
                startDateString,
                endDateString);
    }
}
