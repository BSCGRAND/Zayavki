package ru.bscgrand.Zayavka.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bscgrand.Zayavka.Models.Brigada;

@Repository
public interface BrigadaRepository extends JpaRepository<Brigada,Long> {
}
