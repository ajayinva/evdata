package com.ajay.evdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface EvDataRepository extends JpaRepository<EvData, String>{
	
	@Modifying
    @Transactional
    @Query("UPDATE EvData e SET e.baseMsrp = :baseMsrp WHERE e.makeId = :makeId AND e.modelId = :modelId")
    int updateMsrp(
    	@Param("makeId") Long makeId
    ,	@Param("modelId") Long modelId
    , 	@Param("baseMsrp") Integer baseMsrp
    );

}
