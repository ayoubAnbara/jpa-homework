package com.ayoubanbara.jpahomework.repositories;

import com.ayoubanbara.jpahomework.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepo extends JpaRepository<Patient,Long> {

    List<Patient> findAllByMalade(boolean malade);
    Page<Patient> findAllByMalade(boolean malade, Pageable pageable);

    List<Patient> findAllByMaladeAndScoreLessThan(boolean malade,int score);
    List<Patient> findAllByMaladeIsTrueAndScoreLessThan(int score);
    List<Patient> findAllByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1,Date d2,String nom);

    @Query("SELECT p from Patient p where p.dateNaissance between :x and :d2 or p.nom like :nom")
    List<Patient> chercherPatients(@Param("x") Date d1, Date d2, String nom);


}
