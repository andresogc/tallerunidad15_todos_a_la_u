package com.doctores.doctores.domains.request

import org.jetbrains.annotations.NotNull

class CreatePatientRequest (
    @field:NotNull("Nombre is required")
    val nombre: String,
    val apellido: String,
    val identificacion: String,
    val telefono: Long,
)
