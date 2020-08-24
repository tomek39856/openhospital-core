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
package org.isf.sms.service;

import java.util.Date;
import java.util.List;

import org.isf.sms.model.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface SmsIoOperationRepository extends JpaRepository<Sms, Integer> {
    List<Sms> findBySmsDateSchedBetweenOrderBySmsDateSchedAsc(Date start, Date stop);
    List<Sms> findBySmsDateSchedBetweenAndSmsDateSentIsNullOrderBySmsDateSchedAsc(Date start, Date stop);
    List<Sms> findBySmsDateSentIsNullOrderBySmsDateSchedAsc();
    @Modifying
    void deleteByModuleAndModuleIDAndSmsDateSentIsNull(String mod, String id);
}
