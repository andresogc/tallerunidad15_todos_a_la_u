package com.doctores.doctores.repositories

import com.doctores.doctores.domains.entity.Appointment
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


interface AppointmentRepository : JpaRepository<Appointment, Long> {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("select * from citas where id_cita = :id", nativeQuery = true)
    fun getByAppointmentId(id: Long): Appointment?
}