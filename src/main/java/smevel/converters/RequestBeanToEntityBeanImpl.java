package smevel.converters;

import org.springframework.stereotype.Service;
import smevel.beans.*;
import smevel.beans.inputBean.*;

import java.util.Optional;

@Service
public class RequestBeanToEntityBeanImpl implements RequestBeanToEntityBean {
    @Override
    public EmployeeBean convertRequestBeanToEntityBean(InputEmployeeBean inputEmployeeBean) {
        return Optional.ofNullable(inputEmployeeBean)
                .map(inputBean -> EmployeeBean
                        .builder()
                        .name(inputBean.getName())
                        .female(inputBean.getFemale())
                        .workExperience(inputBean.getWorkExperience())
                        .build())
                .orElse(null);
    }

    @Override
    public ProjectBean convertRequestBeanToEntityBean(InputProjectBean inputProjectBean) {
        return Optional.ofNullable(inputProjectBean)
                .map(inputBean -> ProjectBean
                        .builder()
                        .projectName(inputProjectBean.getProjectName())
                        .projectCode(inputProjectBean.getProjectCode())
                        .build())
                .orElse(null);
    }

    @Override
    public PositionBean convertRequestBeanToEntityBean(InputPositionBean inputPositionBean) {
        return Optional.ofNullable(inputPositionBean)
                .map(inputBean -> PositionBean
                        .builder()
                        .positionName(inputBean.getPositionName())
                        .build())
                .orElse(null);
    }

    @Override
    public ProjectLeadBean convertRequestBeanToEntityBean(InputProjectLeadBean inputProjectLeadBean) {
        return ProjectLeadBean.builder().build();
    }

    @Override
    public VacationLeaveBean convertRequestBeanToEntityBean(InputVacationLeaveBean inputVacationLeaveBean) {
        return Optional.ofNullable(inputVacationLeaveBean)
                .map(inputBean -> VacationLeaveBean
                        .builder()
                        .vacationStartDate(inputBean.getVacationStartDate())
                        .vacationEndDate(inputBean.getVacationEndDate())
                        .build())
                .orElse(null);
    }
}
