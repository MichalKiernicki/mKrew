package pl.mkrew.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mkrew.app.domain.Appointment;
import pl.mkrew.app.domain.RCKiK;
import pl.mkrew.app.repository.AppointmentRepository;
import pl.mkrew.app.repository.RCKiKRepository;
import pl.mkrew.app.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AppointmentServiceTest {

    @Mock
    RCKiKRepository rcKiKRepository;
    @Mock
    AppointmentRepository appointmentRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void shouldCreateScheduleAppointmentForOneDay() {
        // given
        Long rckikId = 1L;
        RCKiK rckik = RCKiK.builder()
                .id(rckikId)
                .build();
        LocalDate startDate = LocalDate.of(2021, 12, 5);
        LocalDate endDate = LocalDate.of(2021, 12, 5);
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(12,0);
        int visitDuration = 60;
        List<Appointment> expectedAppointments = List.of(
                Appointment.builder()
                        .date(LocalDate.of(2021, 12, 5))
                        .time(LocalTime.of(9, 0))
                        .rckik(rckik)
                        .available(true)
                        .build(),
                Appointment.builder()
                        .date(LocalDate.of(2021, 12, 5))
                        .time(LocalTime.of(10, 0))
                        .rckik(rckik)
                        .available(true)
                        .build(),
                Appointment.builder()
                        .date(LocalDate.of(2021, 12, 5))
                        .time(LocalTime.of(11, 0))
                        .rckik(rckik)
                        .available(true)
                        .build()
        );

        when(rcKiKRepository.findById(rckikId))
                .thenReturn(Optional.of(rckik));

        // when
        appointmentService.createScheduleAppointment(rckikId, startDate, endDate, startTime, endTime, visitDuration);

        // then
        verify(appointmentRepository, times(1)).saveAll(expectedAppointments);
    }
}