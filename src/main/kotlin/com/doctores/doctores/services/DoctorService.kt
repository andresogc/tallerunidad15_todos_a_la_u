package com.doctores.doctores.services

import com.doctores.doctores.domains.request.CreateDoctorRequest
import com.doctores.doctores.domains.responses.CreateDoctorResponse
import com.doctores.doctores.repositories.DoctorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import com.doctores.doctores.domains.entity.Doctor
import org.springframework.http.ResponseEntity

@Service
class DoctorService {
    @Autowired
    private lateinit var doctorRepository: DoctorRepository
    fun createDoctor(request: CreateDoctorRequest): CreateDoctorResponse{
        val doctor =  doctorRepository.save(
            Doctor(
                nombre = request.nombre,
                apellido = request.apellido,
                especialidad = request.especialidad,
                correo = request.correo,
                consultorio = request.consultorio,
            )
        )
        return CreateDoctorResponse(
            idDoctor = 1,
            nombre = request.nombre,
            apellido = request.apellido,
            especialidad = request.especialidad,
            correo = request.correo,
            consultorio = request.consultorio,
            createAt = Instant.now(),
            updatedAt = Instant.now()
        )
    }

    fun getAllDoctors(): List<CreateDoctorResponse>{
        val response : List<CreateDoctorResponse> = listOf(
            CreateDoctorResponse(
                idDoctor = 1,
                nombre = "test2",
                apellido = "test",
                especialidad = "test",
                correo = "test",
                consultorio = 123,
                createAt = Instant.now(),
                updatedAt = Instant.now()
            )
        )
        return response
    }

    fun getDoctorById(id: Long): CreateDoctorResponse {
        val doctor: Doctor? =  doctorRepository.getByDoctorId(id)
        if (doctor!=null){
            return CreateDoctorResponse(
                idDoctor = doctor.idDoctor,
                nombre = doctor.nombre,
                apellido = doctor.apellido,
                especialidad = doctor.especialidad,
                correo = doctor.correo,
                consultorio = doctor.consultorio,
                createAt = Instant.now(),
                updatedAt = Instant.now()
            )
        }else{
            return CreateDoctorResponse(
                idDoctor = 1,
                nombre = "test",
                apellido = "test",
                especialidad = "test",
                correo = "test",
                consultorio = 123,
                createAt = Instant.now(),
                updatedAt = Instant.now()
            )
        }



    }

    fun updateDoctor(id: Long, request: CreateDoctorRequest): CreateDoctorResponse {
        val existingDoctor: Doctor? = doctorRepository.getByDoctorId(id)
        if (existingDoctor == null) {
            return CreateDoctorResponse(
                    idDoctor = 1,
                    nombre = "test",
                    apellido = "test",
                    especialidad = "test",
                    correo = "test",
                    consultorio = 123,
                    createAt = Instant.now(),
                    updatedAt = Instant.now(),

            )
        }
        existingDoctor.nombre = request.nombre
        existingDoctor.apellido = request.apellido
        existingDoctor.especialidad = request.especialidad
        existingDoctor.correo = request.correo
        existingDoctor.consultorio = request.consultorio

        val updatedDoctor: Doctor = doctorRepository.save(existingDoctor)

        return CreateDoctorResponse(
                idDoctor = updatedDoctor.idDoctor,
                nombre = updatedDoctor.nombre,
                apellido = updatedDoctor.apellido,
                especialidad = updatedDoctor.especialidad,
                correo = updatedDoctor.correo,
                consultorio = updatedDoctor.consultorio,
                createAt = Instant.now(),
                updatedAt = Instant.now()
        )
    }

    fun deleteDoctorById(id: Long) :ResponseEntity<String> {

        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id)
            return ResponseEntity.ok("Registro eliminado correctamente")
        } else {
            return ResponseEntity.notFound().build()
        }
    }


}