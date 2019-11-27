package ru.bscgrand.Zayavka.Models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrigadaRepository extends JpaRepository<Brigada,Long> {
}
