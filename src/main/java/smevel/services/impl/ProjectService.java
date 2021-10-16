package smevel.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smevel.beans.ProjectBean;
import smevel.beans.inputBean.InputProjectBean;
import smevel.beans.outputBean.OutputProjectBean;
import smevel.converters.EntityToBeanConverter;
import smevel.converters.EntityToOutputBeanConverter;
import smevel.converters.RequestBeanToEntityBeanImpl;
import smevel.converters.impl.BeanToEntityConverterImpl;
import smevel.entity.Project;
import smevel.repo.ProjectsRepo;
import smevel.services.abst.BaseEntityService;

import javax.transaction.Transactional;
import java.util.Collection;

import static smevel.constants.StringConstants.*;

@Service
@Slf4j
@AllArgsConstructor
public class ProjectService extends BaseEntityService<Project, ProjectBean,
        InputProjectBean, OutputProjectBean, ProjectsRepo> {

    private final ProjectsRepo projectsRepo;
    private final BeanToEntityConverterImpl beanToEntityConverter;
    private final EntityToBeanConverter entityToBeanConverter;
    private final RequestBeanToEntityBeanImpl requestBeanToEntityBean;
    private final EntityToOutputBeanConverter entityToOutputBeanConverter;


    @Transactional
    public ResponseEntity<Collection<OutputProjectBean>> getProjectByName(String projectName) {
        return getCollectionOfBean(() ->
                        getEntitiesWithSupplier(() -> projectsRepo.findByProjectName(projectName)),
                getMessageByFieldNameAndValue(PROJECT_NAME, projectName));
    }

    @Transactional
    public ResponseEntity<Collection<OutputProjectBean>> getProjectByCode(String projectCode) {
        return getCollectionOfBean(() ->
                        getEntitiesWithSupplier(() -> projectsRepo.findByProjectCode(projectCode)),
                getMessageByFieldNameAndValue(PROJECT_CODE, projectCode));
    }

    @Override
    protected ProjectBean convertEntityToBean(Project entity) {
        return entityToBeanConverter.convertProjectToProjectBean(entity);
    }

    @Override
    protected Project convertBeanToEntity(ProjectBean bean) {
        return beanToEntityConverter.convertProjectBeanToEntity(bean);
    }

    @Override
    protected ProjectBean convertRequestBeanToEntityBean(InputProjectBean inputBean) {
        return requestBeanToEntityBean.convertRequestBeanToEntityBean(inputBean);
    }

    @Override
    protected void prepareEntityBeforeSave(Project entity, InputProjectBean inputBean) {

    }

    @Override
    protected String getEntityName() {
        return PROJECT;
    }

    @Override
    protected ProjectsRepo getJpaRepository() {
        return projectsRepo;
    }

    @Override
    protected void checkEntityBeforeSave(Project entity) {

    }

    @Override
    protected OutputProjectBean convertEntityToOutPutBean(Project entity) {
        return entityToOutputBeanConverter.convertProjectToOutputEProjectBean(entity);
    }
}
