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
package org.isf.admission.service;

import java.util.GregorianCalendar;
import java.util.List;

import org.isf.admission.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionIoOperationRepository extends JpaRepository<Admission, Integer>, AdmissionIoOperationRepositoryCustom {

	@Query(value = "select a FROM Admission a WHERE a.admitted = 1 AND a.ward.code = :ward")
    List<Admission> findAllWhereWard(@Param("ward") String ward);

	@Query(value = "select a FROM Admission a WHERE a.patient.code = :patient and a.deleted='N' and a.admitted = 1")
    Admission findOneWherePatientIn(@Param("patient") Integer patient);

	@Query(value = "select a FROM Admission a WHERE a.patient.code =:patient and a.deleted='N' order by a.admDate asc")
    List<Admission> findAllWherePatientByOrderByDate(@Param("patient") Integer patient);

	@Query(value = "select a FROM Admission a " +
			"WHERE a.ward.code =:ward AND a.admDate >= :dateFrom AND a.admDate <= :dateTo AND a.deleted ='N' " +
			"ORDER BY a.yProg desc ")
    List<Admission> findAllWhereWardAndDates(
            @Param("ward") String ward, @Param("dateFrom") GregorianCalendar dateFrom,
            @Param("dateTo") GregorianCalendar dateTo);

	@Query(value = "select a FROM Admission a WHERE a.admitted =1 and a.ward.code = :ward and a.deleted = 'N'")
	List<Admission> findAllWhereWardIn(@Param("ward") String ward);
}
