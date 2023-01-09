package solvd.laba.ermakovich.ha.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import solvd.laba.ermakovich.ha.domain.doctor.Specialization;
import solvd.laba.ermakovich.ha.domain.hospital.Department;
import solvd.laba.ermakovich.ha.web.dto.group.onCreate;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDto extends UserInfoDto {
    @NotNull(message = "department can`t be null", groups = onCreate.class)
    private Department department;
    @NotNull(message = "specialization can`t be null", groups = onCreate.class)
    private Specialization specialization;

}
