/*-
 * #%L
 * OpenHospital
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2020 Informatici Senza Frontiere
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
package org.isf.patvac.service;

import org.isf.patvac.model.PatientVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.GregorianCalendar;
import java.util.List;

@Repository
public interface PatVacIoOperationRepository extends JpaRepository<PatientVaccine, Integer>, PatVacIoOperationRepositoryCustom {

	@Query("select max(pv.progr) from PatientVaccine pv")
    Integer findMaxCode();
    
	@Query("select max(pv.progr) from PatientVaccine pv where pv.vaccineDate >= :yearStart and pv.vaccineDate < :yearEnd")
	Integer findMaxCodeWhereVaccineDate(@Param("yearStart") GregorianCalendar yearStart, @Param("yearEnd") GregorianCalendar yearEnd);

    List<PatientVaccine> findByPatient_code(int patientId);
}
