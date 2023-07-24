package com.doctores.doctores.services

import com.doctores.doctores.domains.entity.Appointment
import com.doctores.doctores.domains.entity.Doctor
import com.doctores.doctores.domains.request.CreateAppointmentRequest
import com.doctores.doctores.domains.responses.CreateAppointmentResponse
import com.doctores.doctores.domains.responses.CreateDoctorResponse
import com.doctores.doctores.repositories.AppointmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AppointmentService {
    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository
    fun createAppointment(request: CreateAppointmentRequest): CreateAppointmentResponse{
        println("Valor de idDoctor recibido en la solicitud: ${request.id_doctor}")
        val appointment =  appointmentRepository.save(
                Appointment(
                        horario = request.horario,
                        especialidad = request.especialidad,
                        iddoctor = request.id_doctor,
                        identificacionPaciente = request.identificacion_paciente,
                )
        )
        return CreateAppointmentResponse(
                idCita = appointment.idCita,
                especialidad = appointment.especialidad,
                id_doctor = appointment.iddoctor,
                identificacion_paciente = appointment.identificacionPaciente,
                horario = appointment.horario,
        )
    }

    fun getAllAppointments(): List<CreateAppointmentResponse>{
        var response : List<CreateAppointmentResponse> = listOf(
            CreateAppointmentResponse(
                idCita = 1,
                identificacion_paciente = 1,
                especialidad = "Test",
                id_doctor = 1,
                horario = "16"
            )
        )
        return response
    }

    fun getAppointmentById(id: Long): CreateAppointmentResponse{
        return CreateAppointmentResponse(
                idCita = 1,
                identificacion_paciente = 1,
                especialidad = "Test",
                id_doctor = 1,
                horario = "16"
        )
    }

    fun updateAppointment(id: Long): CreateAppointmentResponse{
        return CreateAppointmentResponse(
                idCita = 1,
                identificacion_paciente = 1,
                especialidad = "Test",
                id_doctor = 1,
                horario = "16"
        )
    }

    fun deleteAppointmentById(id: Long) : ResponseEntity<String> {

        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id)
            return ResponseEntity.ok("Registro eliminado correctamente")
        } else {
            return ResponseEntity.notFound().build()
        }
    }
}