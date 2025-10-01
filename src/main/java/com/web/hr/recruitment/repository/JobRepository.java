package com.web.hr.recruitment.repository;

import com.web.hr.recruitment.entity.Job;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
  // Có thể thêm custom query nếu cần
  @Query(value = "SELECT *\n"
      + "FROM jobs\n"
      + "ORDER BY create_date DESC\n"
      + "LIMIT 10;\n", nativeQuery = true)
  List<Job> findTop10ByOrderBycreateDateDesc();   // 10 job mới nhất

  @Query(value = "SELECT *\n"
      + "FROM jobs\n"
      + "ORDER BY views DESC\n"
      + "LIMIT 10;\n", nativeQuery = true)
  List<Job> findTop10ByOrderByViewsDesc();    // 10 job nhiều view nhất

  // Top 5 job sắp hết hạn (phải select *)
  @Query(value = "SELECT * FROM jobs "
      + "WHERE deadline IS NOT NULL "
      + "AND deadline >= CURDATE() "
      + "ORDER BY deadline ASC "
      + "LIMIT 5", nativeQuery = true)
  List<Job> findTop5ByOrderByDeadlineAsc();
}
