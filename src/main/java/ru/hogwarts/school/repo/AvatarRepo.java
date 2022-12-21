package ru.hogwarts.school.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.school.model.Avatar;

import java.util.Optional;

public interface AvatarRepo extends PagingAndSortingRepository<Avatar,Long> {

    Optional<Avatar> findByStudentId(Long studentId);

}
