package com.cantvas2.cantvas2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cantvas2.cantvas2.models.Course;

import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    @Query(value = "select * from Course where teacher_id =:teacherId", nativeQuery = true)
    Optional<Course> findByTeacherId(@Param("teacherId") Long id);
}
