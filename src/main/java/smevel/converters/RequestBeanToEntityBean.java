package smevel.converters;

import smevel.beans.*;
import smevel.beans.inputBean.*;

public interface RequestBeanToEntityBean {

    EmployeeBean convertRequestBeanToEntityBean(InputEmployeeBean inputEmployeeBean);

    ProjectBean convertRequestBeanToEntityBean(InputProjectBean inputProjectBean);

    PositionBean convertRequestBeanToEntityBean(InputPositionBean inputPositionBean);

    ProjectLeadBean convertRequestBeanToEntityBean(InputProjectLeadBean inputProjectLeadBean);

    VacationLeaveBean convertRequestBeanToEntityBean(InputVacationLeaveBean inputVacationLeaveBean);
}
