package smevel.services.abst;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static smevel.constants.StringConstants.*;

@Slf4j
public abstract class BaseEntityService<E, B, I, R extends JpaRepository<E, UUID>> {


    @Transactional
    public ResponseEntity<B> addNewEntityWithResponse(I inputBean) {
        return addNewEntityWithResponse(
                () -> addNewEntity(inputBean), getCanNotCreateMessage());
    }

    @Transactional
    public ResponseEntity<Collection<B>> getEntityByIdWithResponse(String id) {
        return getCollectionOfBean(() ->
                        getEntitiesWithSupplier(() ->
                                getOptionalEntityById(id)),
                getMessageByFieldNameAndValue(ID, id));
    }

    @Transactional
    public ResponseEntity<Collection<B>> getAllEntitiesWithResponse() {
        return getCollectionOfBean(this::getAllEntityBeans,
                getCanNotFindMessage());
    }

    protected ResponseEntity<Collection<B>> getCollectionOfBean(Supplier<Collection<B>> getBeanSupplier,
                                                                String logMessage) {
        try {
            return new ResponseEntity<>(
                    getBeanSupplier.get(),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.info(logMessage);
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    protected ResponseEntity<B> addNewEntityWithResponse(Supplier<B> addEntitySupplier, String errorMessage) {
        try {
            return new ResponseEntity<>(addEntitySupplier.get(), HttpStatus.CREATED);
        } catch (Exception e) {
            log.info(errorMessage);
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    protected ResponseEntity<B> updateEntityWithResponse(Supplier<B> addEntitySupplier, String errorMessage) {
        try {
            return new ResponseEntity<>(addEntitySupplier.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.info(errorMessage);
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    protected Collection<E> getOptionalEntityById(String id) {
        Optional<E> entity = getEntityById(id);
        if (entity.isPresent()) {
            return Collections.singleton(entity.get());
        } else {
            return new ArrayList<>();
        }
    }

    protected Collection<B> getAllEntityBeans() {
        Collection<E> entities = getAllEntities();
        return entities.stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    protected B addNewEntity(I inputBean) {
        B bean = convertRequestBeanToEntityBean(inputBean);
        E entity = getEntityByEntityBean(bean, inputBean);
        prepareEntityBeforeSave(entity, inputBean);
        E saveEntity = saveEntity(entity);
        return convertEntityToBean(saveEntity);
    }

    protected Collection<B> getEntitiesWithSupplier(Supplier<Collection<E>> entitySupplier) {
        Collection<E> entities = entitySupplier.get();
        return entities.stream()
                .map(this::convertEntityToBean)
                .collect(Collectors.toList());
    }

    protected String getMessageByFieldNameAndValue(String fieldName, String fieldValue) {
        return String.format(CAN_NOT_FIND_ENTITY_BY_FIELD, getEntityName(), fieldName, fieldValue);
    }

    protected String getUpdateEntityMessage(String employeeId) {
        return String.format(CAN_NOT_UPDATE, getEntityName(), employeeId);
    }


    protected E getEntityByEntityBean(B bean, I inputBean) {
        return convertBeanToEntity(bean);
    }

    private Optional<E> getEntityById(String id) {
        return getJpaRepository().findById(UUID.fromString(id));
    }

    private Collection<E> getAllEntities() {
        return getJpaRepository().findAll();
    }

    private String getCanNotCreateMessage() {
        return String.format(CAN_NOT_CREATE, getEntityName());
    }

    private String getCanNotFindMessage() {
        return String.format(CAN_NOT_FIND, entitySingleToPluralNames.get(getEntityName()));
    }


    protected abstract B convertEntityToBean(E entity);

    protected abstract E convertBeanToEntity(B bean);

    protected abstract B convertRequestBeanToEntityBean(I inputBean);

    protected abstract void prepareEntityBeforeSave(E entity, I inputBean);

    private E saveEntity(E entity) {
        return getJpaRepository().save(entity);
    }

    protected abstract String getEntityName();

    protected abstract R getJpaRepository();
}
