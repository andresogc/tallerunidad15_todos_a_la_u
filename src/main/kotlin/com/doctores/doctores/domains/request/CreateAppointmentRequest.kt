package com.doctores.doctores.domains.request

import org.jetbrains.annotations.NotNull

data class CreateAppointmentRequest(
    val identificacion_paciente: Long,
    val especialidad: String,
    val id_doctor: Long,
    val horario: String

)