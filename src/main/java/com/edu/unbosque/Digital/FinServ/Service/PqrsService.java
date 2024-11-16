package com.edu.unbosque.Digital.FinServ.Service;

import com.edu.unbosque.Digital.FinServ.Model.PqrsModel;
import com.edu.unbosque.Digital.FinServ.Repository.PqrsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service class for managing PQRS.
 */
@Service
public class PqrsService {
    @Autowired
    private PqrsRepository pqrsRepository;

    /**
     * Retrieves all PQRS.
     *
     * @return a list of all PQRS
     */
    public PqrsModel createPqrs(PqrsModel pqrs) {
        try {
            pqrs.setCreationDate(new Date());
            return pqrsRepository.save(pqrs);
        } catch (Exception e) {
            e.printStackTrace(); // Ver registro de excepción detallado
            throw new RuntimeException("Error al guardar la PQRS: " + e.getMessage(), e);
        }
    }


    public List<PqrsModel> getPqrsByCustomerId(int customerId) {
        return pqrsRepository.findByCustomerId(customerId);
    }


    public PqrsModel updatePqrs(int id, PqrsModel pqrsDetails) {
        PqrsModel existingPqrs = pqrsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PQRS not found with id " + id));

        existingPqrs.setDescription(pqrsDetails.getDescription());

        return pqrsRepository.save(existingPqrs);
    }


    public void deletePqrs(int id) {
        if (pqrsRepository.existsById(id)) {
            pqrsRepository.deleteById(id);
        } else {
            throw new RuntimeException("PQRS not found with id " + id);
        }
    }
}
