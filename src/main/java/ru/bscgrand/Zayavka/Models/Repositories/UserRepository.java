package ru.bscgrand.Zayavka.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bscgrand.Zayavka.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
