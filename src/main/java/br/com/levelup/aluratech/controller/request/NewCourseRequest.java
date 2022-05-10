package br.com.levelup.aluratech.controller.request;

import br.com.levelup.aluratech.model.Course;
import br.com.levelup.aluratech.model.Instructor;
import br.com.levelup.aluratech.model.SubCategory;
import br.com.levelup.aluratech.shared.ExistsId;
import br.com.levelup.aluratech.shared.UniqueValue;
import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCourseRequest {

    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;
    @NotBlank(message = "O código não pode ser vazio!")
    @Pattern(regexp = "^[a-z0-9-]*$", message = "O código deve conter apenas letras minúsculas, números e hífen!")
    @UniqueValue(domainClass = Course.class, fieldName = "code", message = "O código do curso já existe!")
    private String code;
    @NotNull(message = "A estimativa (em horas) é obrigatória!")
    @Min(1) @Max(20)
    private Integer estimatedTimeInHours;
    private String targetAudience;
    private boolean visibility;
    private String resume;
    private String developedSkills;
    @NotNull(message = "O instrutor deve ser obrigatório!")
    @ExistsId(domainClass = Instructor.class, fieldName = "id")
    private Long idInstructor;
    @NotNull(message = "A subcategoria deve ser obrigatória!")
    @ExistsId(domainClass = SubCategory.class, fieldName = "id")
    private Long idSubCategory;

    public Course toEntity(Instructor instructor, SubCategory subCategory) {
        return new Course(name, code, estimatedTimeInHours, targetAudience, visibility, resume, developedSkills, instructor, subCategory);
    }
}
