package com.example.employee.repo.impl;


import com.example.employee.pojo.Department;
import com.example.employee.repo.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    private final EntityManager entityManager;

    @Autowired
    public DepartmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Department department) {
        entityManager.persist(department);
    }

    @Override
    public List<Department> findByNameAndMinId(String name, int minId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> cq = cb.createQuery(Department.class);
        Root<Department> root = cq.from(Department.class);
        cq.select(root).where(cb.and(new Predicate[] {
                cb.equal(root.get("name"), name), cb.greaterThanOrEqualTo(root.get("id"), minId)}));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void update(Department department) {
        entityManager.merge(department);
    }

    @Override
    public void delete(int id) {
        // use em.remove(em.find()) results in two queries
        entityManager.createQuery("delete from Department d where d.id = :id").setParameter("id", id).executeUpdate();
    }

}
