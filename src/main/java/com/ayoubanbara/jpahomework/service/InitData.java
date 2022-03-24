package com.ayoubanbara.jpahomework.service;

import com.ayoubanbara.jpahomework.entities.Patient;
import com.ayoubanbara.jpahomework.repositories.PatientRepo;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class InitData implements CommandLineRunner {
    private final PatientRepo patientRepo;
    private final Faker faker = new Faker();
    private final Random random = new Random();


    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 100; i++) {
            patientRepo.save(new Patient(null, faker.name().fullName(),
                    faker.date().birthday()
                    , random.nextBoolean(), random.nextInt()));
        }

        System.err.println("-------------------");
        Page<Patient> patientPage = patientRepo.findAll(PageRequest.of(0, 5));
        System.out.println("total elements :"+patientPage.getTotalElements());
        System.out.println("total pages: "+ patientPage.getTotalPages());

      //  patientPage.getContent().forEach(System.out::println);

        System.err.println("-------------------");
        Patient patient = patientRepo.findById(1L).orElseThrow(() -> new RuntimeException("patient not found"));
        System.out.println(patient);

        patient.setScore(4555);
        patientRepo.save(patient);

        patientRepo.deleteById(1L);
        System.err.println("---------patientsByMalade----------");
        List<Patient> patientsByMalade=patientRepo.findAllByMalade(true);
      //  patientsByMalade.forEach(System.out::println);
        System.err.println("---------patientsByMalade pageable----------");
        Page<Patient> patientsByMaladePageable=patientRepo.findAllByMalade(true,
                PageRequest.of(0,4));
       // patientsByMaladePageable.forEach(System.out::println);
        System.err.println("--------------------");
        Date date=new Date();
        patientRepo.save(new Patient(null, "amine",
                date
                , random.nextBoolean(), random.nextInt()));

        patientRepo.chercherPatients(date,date,"%amine%")
                .forEach(System.out::println);
    }
}
