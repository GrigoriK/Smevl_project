package smevel.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smevel.beans.PositionBean;
import smevel.beans.inputBean.InputPositionBean;
import smevel.converters.EntityToBeanConverter;
import smevel.converters.RequestBeanToEntityBeanImpl;
import smevel.converters.impl.BeanToEntityConverterImpl;
import smevel.entity.Position;
import smevel.repo.PositionRepo;
import smevel.services.abst.BaseEntityService;

import javax.transaction.Transactional;
import java.util.Collection;

import static smevel.constants.StringConstants.POSITION;
import static smevel.constants.StringConstants.POSITION_NAME;

@Service
@AllArgsConstructor
public class PositionService extends BaseEntityService<Position, PositionBean,
        InputPositionBean, PositionRepo> {

    private final PositionRepo positionRepo;
    private final BeanToEntityConverterImpl beanToEntityConverter;
    private final EntityToBeanConverter entityToBeanConverter;
    private final RequestBeanToEntityBeanImpl requestBeanToEntityBean;


    @Transactional
    public ResponseEntity<Collection<PositionBean>> getPositionByName(String positionName) {
        return getCollectionOfBean(() ->
                        getEntitiesWithSupplier(() -> positionRepo.findByPositionName(positionName)),
                getMessageByFieldNameAndValue(POSITION_NAME, positionName));
    }

    @Override
    protected PositionBean convertEntityToBean(Position entity) {
        return entityToBeanConverter.convertPositionToPositionBean(entity);
    }

    @Override
    protected Position convertBeanToEntity(PositionBean bean) {
        return beanToEntityConverter.convertPositionBeanToEntity(bean);
    }

    @Override
    protected PositionBean convertRequestBeanToEntityBean(InputPositionBean inputBean) {
        return requestBeanToEntityBean.convertRequestBeanToEntityBean(inputBean);

    }

    @Override
    protected void prepareEntityBeforeSave(Position entity, InputPositionBean inputBean) {

    }

    @Override
    protected String getEntityName() {
        return POSITION;
    }

    @Override
    protected PositionRepo getJpaRepository() {
        return positionRepo;
    }

    @Override
    protected void checkEntityBeforeSave(Position entity) {

    }
}
