package sprinExamples.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprinExamples.beans.PositionBean;
import sprinExamples.beans.inputBean.InputPositionBean;
import sprinExamples.converters.EntityToBeanConverter;
import sprinExamples.converters.impl.BeanToEntityConverterImpl;
import sprinExamples.entity.Position;
import sprinExamples.repo.PositionRepo;

import javax.transaction.Transactional;
import java.util.Collection;

import static sprinExamples.constants.StringConstants.*;

@Service
@AllArgsConstructor
public class PositionService extends BaseEntityService<Position, PositionBean,
        InputPositionBean, PositionRepo> {

    private final PositionRepo positionRepo;
    private final BeanToEntityConverterImpl beanToEntityConverter;
    private final EntityToBeanConverter entityToBeanConverter;



    @Transactional
    public ResponseEntity<Collection<PositionBean>> getPositionByName(String positionName) {
        return getCollectionOfBean(() ->
                        getEntitiesWithSupplier(() -> positionRepo.findByPositionName(positionName)),
                getMessageByFieldNameAndValue(POSITION_NAME, positionName));
    }

    @Override
    PositionBean convertEntityToBean(Position entity) {
        return entityToBeanConverter.convertPositionToPositionBean(entity);
    }

    @Override
    Position convertBeanToEntity(PositionBean bean) {
        return beanToEntityConverter.convertPositionBeanToEntity(bean);
    }

    @Override
    PositionBean convertRequestBeanToEntityBean(InputPositionBean inputBean) {
        return PositionBean
                .builder()
                .positionName(inputBean.getPositionName())
                .build();

    }

    @Override
    void prepareEntityBeforeSave(Position entity, InputPositionBean inputBean) {

    }

    @Override
    String getEntityName() {
        return POSITION;
    }

    @Override
    PositionRepo getJpaRepository() {
        return positionRepo;
    }
}
