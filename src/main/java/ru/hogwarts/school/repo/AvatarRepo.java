package ru.hogwarts.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.school.model.Avatar;

import java.util.List;
import java.util.Optional;

public interface AvatarRepo extends PagingAndSortingRepository<Avatar,Long> {

    Optional<Avatar> findByStudentId(Long studentId);


}
