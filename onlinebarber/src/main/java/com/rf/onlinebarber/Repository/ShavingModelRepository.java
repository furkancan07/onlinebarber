package com.rf.onlinebarber.Repository;

import com.rf.onlinebarber.Entity.ShavingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShavingModelRepository extends JpaRepository<ShavingModel,Long> {
}
