package com.doctores.doctores.domains.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "citas")
open class Appointment (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id_cita")
        open var idCita: Long=0,

        @Column(name="horario")
        open var horario: String,

        @Column(name="especialidad")
        open var especialidad: String,

        @Column(name="id_doctor")
        open var iddoctor: Long,

        @Column(name="identificacion_paciente")
        open var identificacionPaciente: Long,

        @Column(name="created_at")
        open var createdAt: Instant = Instant.now(),

        @Column(name="updated_at")
        open var updatedAt: Instant = Instant.now(),

        )