package org.launchcode.cheesemvc3.models.data;

import org.launchcode.cheesemvc3.models.cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CheeseDao extends CrudRepository<cheese, Integer> {

}
