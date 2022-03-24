package com.example.pieceart.works;

import com.example.pieceart.entity.Works;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorksRepository extends JpaRepository<Works, Long> {
}
