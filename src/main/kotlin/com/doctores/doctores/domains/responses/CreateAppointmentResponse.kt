package com.doctores.doctores.domains.responses

import java.time.Instant
data class CreateAppointmentResponse(
    val idCita: Long,
    val identificacion_paciente: Long,
    val especialidad: String,
    val id_doctor: Long,
    val horario: String,

)
