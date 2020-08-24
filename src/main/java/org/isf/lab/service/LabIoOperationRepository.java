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
package org.isf.lab.service;

import java.util.GregorianCalendar;
import java.util.List;

import org.isf.lab.model.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabIoOperationRepository extends JpaRepository<Laboratory, Integer> {

    List<Laboratory> findByExamDateBetweenOrderByExamDateDesc(
            GregorianCalendar dateFrom,
            GregorianCalendar dateTo);

    List<Laboratory> findByExamDateBetweenAndExam_DescriptionOrderByExamDateDesc(
            GregorianCalendar dateFrom,
            GregorianCalendar dateTo,
            String exam);

    List<Laboratory> findByPatient_CodeOrderByRegistrationDate(Integer patient);

    List<Laboratory> findByExamDateBetweenOrderByExam_Examtype_DescriptionDesc(
            GregorianCalendar dateFrom,
            GregorianCalendar dateTo);

    List<Laboratory> findByExamDateBetweenAndExam_DescriptionContainingOrderByExam_Examtype_DescriptionDesc(
            GregorianCalendar dateFrom,
            GregorianCalendar dateTo,
            String exam);

}
