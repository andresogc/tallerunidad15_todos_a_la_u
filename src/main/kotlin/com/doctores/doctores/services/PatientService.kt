package com.doctores.doctores.services

import com.doctores.doctores.domains.request.CreatePatientRequest
import com.doctores.doctores.domains.responses.CreatePatientResponse
import com.doctores.doctores.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import com.doctores.doctores.domains.entity.Patient
import org.springframework.http.ResponseEntity

@Service
class PatientService {
    @Autowired
    private lateinit var patientRepository: PatientRepository
    fun createPatient(request: CreatePatientRequest): CreatePatientResponse{
        val patient =  patientRepository.save(
                Patient(
                        nombre = request.nombre,
                        apellido = request.apellido,
                        identificacion = request.identificacion,
                        telefono = request.telefono
                )
        )
        return CreatePatientResponse(
                idPatient = patient.idPatient,
                nombre = request.nombre,
                apellido = request.apellido,
                identificacion = request.identificacion,
                telefono = request.telefono,
                createAt = Instant.now(),
                updatedAt = Instant.now()
        )
    }

    fun getAllPatients(): List<CreatePatientResponse>{
        val response : List<CreatePatientResponse> = listOf(
                CreatePatientResponse(
                        idPatient = 1,
                        nombre = "test",
                        apellido = "test",
                        identificacion = "test",
                        telefono = 45555,
                        createAt = Instant.now(),
                        updatedAt = Instant.now()
                )
        )
        return response
    }

    fun getPatientById(id: Long): CreatePatientResponse {
        val doctor: Patient? =  patientRepository.getByPatientId(id)
        if (doctor!=null){
            return CreatePatientResponse(
                    idPatient = doctor.idPatient,
                    nombre = doctor.nombre,
                    apellido = doctor.apellido,
                    identificacion = doctor.identificacion,
                    telefono = doctor.telefono,
                    createAt = Instant.now(),
                    updatedAt = Instant.now()
            )
        }else{
            return CreatePatientResponse(
                    idPatient = 1,
                    nombre = "test",
                    apellido = "test",
                    identificacion = "test",
                    telefono = 45877,
                    createAt = Instant.now(),
                    updatedAt = Instant.now()
            )
        }



    }

    fun updatePatient(id: Long, request: CreatePatientRequest): CreatePatientResponse {
        val existingPatient: Patient? = patientRepository.getByPatientId(id)
        if (existingPatient == null) {
            return CreatePatientResponse(
                    idPatient = 1,
                    nombre = "test",
                    apellido = "test",
                    identificacion = "test",
                    telefono = 7841111,
                    createAt = Instant.now(),
                    updatedAt = Instant.now(),

                    )
        }
        existingPatient.nombre = request.nombre
        existingPatient.apellido = request.apellido
        existingPatient.identificacion = request.identificacion
        existingPatient.telefono = request.telefono

        val updatedPatient: Patient = patientRepository.save(existingPatient)

        return CreatePatientResponse(
                idPatient = updatedPatient.idPatient,
                nombre = updatedPatient.nombre,
                apellido = updatedPatient.apellido,
                identificacion = updatedPatient.identificacion,
                telefono = updatedPatient.telefono,
                createAt = Instant.now(),
                updatedAt = Instant.now()
        )
    }

    fun deletePatientById(id: Long) :ResponseEntity<String> {

        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id)
            return ResponseEntity.ok("Registro eliminado correctamente")
        } else {
            return ResponseEntity.notFound().build()
        }
    }


}