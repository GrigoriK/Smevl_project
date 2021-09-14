package sprinExamples.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sprinExamples.beans.ProjectBean;
import sprinExamples.beans.inputBean.InputProjectBean;
import sprinExamples.converters.EntityToBeanConverter;
import sprinExamples.converters.impl.BeanToEntityConverterImpl;
import sprinExamples.entity.Project;
import sprinExamples.repo.ProjectsRepo;

import javax.transaction.Transactional;
import java.util.Collection;

import static sprinExamples.constants.StringConstants.*;

@Service
@Slf4j
@AllArgsConstructor
public class ProjectService extends BaseEntityService<Project, ProjectBean,
        InputProjectBean, ProjectsRepo> {

    private final ProjectsRepo projectsRepo;
    private final BeanToEntityConverterImpl beanToEntityConverter;
    private final EntityToBeanConverter entityToBeanConverter;


    @Transactional
    public ResponseEntity<Collection<ProjectBean>> getProjectByName(String projectName) {
        return getCollectionOfBean(() ->
                        getEntitiesWithSupplier(() -> projectsRepo.findByProjectName(projectName)),
                getMessageByFieldNameAndValue(PROJECT_NAME, projectName));
    }

    @Transactional
    public ResponseEntity<Collection<ProjectBean>> getProjectByCode(String projectCode) {
        return getCollectionOfBean(() ->
                        getEntitiesWithSupplier(() -> projectsRepo.findByProjectCode(projectCode)),
                getMessageByFieldNameAndValue(PROJECT_CODE, projectCode));
    }

    @Override
    ProjectBean convertEntityToBean(Project entity) {
        return entityToBeanConverter.convertProjectToProjectBean(entity);
    }

    @Override
    Project convertBeanToEntity(ProjectBean bean) {
        return beanToEntityConverter.convertProjectBeanToEntity(bean);
    }

    @Override
    ProjectBean convertRequestBeanToEntityBean(InputProjectBean inputBean) {
        return ProjectBean
                .builder()
                .projectName(inputBean.getProjectName())
                .projectCode(inputBean.getProjectCode())
                .build();
    }

    @Override
    void prepareEntityBeforeSave(Project entity, InputProjectBean inputBean) {

    }

    @Override
    String getEntityName() {
        return PROJECT;
    }

    @Override
    ProjectsRepo getJpaRepository() {
        return projectsRepo;
    }
}
