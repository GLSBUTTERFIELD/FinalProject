package com.skilldistillery.roundtwo.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.roundtwo.entities.Gathering;

public interface GatheringRepository extends JpaRepository<Gathering, Integer>{
	Gathering findByHostUsernameAndId (String username, int gatheringId);
	List<Gathering>findByEnabledTrue();
	List<Gathering> findByHostUsername(String username);
	List<Gathering> findByParticipantsUserUsernameAndStartDateBefore(String username,LocalDate curentDate);
	List<Gathering> findByParticipantsUserUsernameAndStartDateAfter(String username, LocalDate currentDate);
}
