package com.blockchain.data.anonymization.Repository;

import com.blockchain.data.anonymization.model.CovidUser;
import com.blockchain.data.anonymization.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface CovidUserRepository  extends JpaRepository<CovidUser, Long > {
    @Query("SELECT count(*) FROM CovidUser u where u.streetArea = ?1")
    Integer findAllByStreetArea(String area);

    @Query("SELECT u FROM CovidUser u where u.streetArea LIKE %?1%")
    List<CovidUser> findAllByStreetAreas(String area);

   /* @Query("SELECT * FROM CovidUser u where u.covidStartDate Between ?1 AND ?2")
    List<CovidUser> findAllByCovidStartDate(Date startDate, Date endDate);*/
}
