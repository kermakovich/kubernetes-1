package solvd.laba.ermakovich.ha.service;

import solvd.laba.ermakovich.ha.domain.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {

    String entityName = "appointment";

    List<LocalTime> getTimeSlotsByDoctorIdAndDate(long id, LocalDate date);

    Appointment save(Appointment appointment);

    List<Appointment> getAllFutureByPatientId(long patientId);

    void delete(long appointmentId);

    List<Appointment> getAllByPatientIdAndDoctorId(long patientId, long doctorId);

    List<Appointment> getAllFutureByDoctorId(long doctorId);
}
