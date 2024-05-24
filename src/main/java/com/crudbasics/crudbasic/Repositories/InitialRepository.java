package com.crudbasics.crudbasic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface InitialRepository<T, ID> extends JpaRepository<T, ID> {

}
