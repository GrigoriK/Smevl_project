package smevel.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smevel.beans.VacationLeaveBean;
import smevel.beans.inputBean.InputVacationLeaveBean;
import smevel.converters.EntityToBeanConverter;
import smevel.converters.RequestBeanToEntityBeanImpl;
import smevel.converters.impl.BeanToEntityConverterImpl;
import smevel.entity.Employee;
import smevel.entity.VacationLeave;
import smevel.repo.EmployeesRepo;
import smevel.repo.VacationLeaveRepo;
import smevel.services.abst.BaseEntityService;

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
}
