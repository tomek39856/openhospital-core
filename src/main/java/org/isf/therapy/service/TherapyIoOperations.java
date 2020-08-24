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
package org.isf.therapy.service;

import java.util.ArrayList;

import org.isf.therapy.model.TherapyRow;
import org.isf.utils.db.TranslateOHServiceException;
import org.isf.utils.exception.OHServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=OHServiceException.class)
@TranslateOHServiceException
public class TherapyIoOperations {

	@Autowired
	private TherapyIoOperationRepository repository;
	
	/**
	 * insert a new {@link TherapyRow} (therapy) in the DB
	 * 
	 * @param thRow - the {@link TherapyRow} (therapy)
	 * @return the therapyID
	 * @throws OHServiceException 
	 */
	public TherapyRow newTherapy(TherapyRow thRow) throws OHServiceException {
		return repository.save(thRow);
	}

	/**
	 * return the list of {@link TherapyRow}s (therapies) for specified Patient ID
	 * or
	 * return all {@link TherapyRow}s (therapies) if <code>0</code> is passed
	 * 
	 * @param patID - the Patient ID
	 * @return the list of {@link TherapyRow}s (therapies)
	 * @throws OHServiceException 
	 */
	public ArrayList<TherapyRow> getTherapyRows(int patID) throws OHServiceException {
		return patID != 0 ?
			new ArrayList<TherapyRow>(repository.findByPatIDCodeOrderByPatIDCodeAscTherapyIDAsc(patID)) :
			new ArrayList<TherapyRow>(repository.findAllByOrderByPatIDCodeAscTherapyIDAsc());
	}
	
	/**
	 * delete all {@link TherapyRow}s (therapies) for specified Patient ID
	 * 
	 * @param patID - the Patient ID
	 * @return <code>true</code> if the therapies have been deleted, <code>false</code> otherwise
	 * @throws OHServiceException 
	 */
	public boolean deleteAllTherapies(int patID) throws OHServiceException {
		repository.deleteByPatIDCode(patID);
		return true;
	}

	/**
	 * checks if the code is already in use
	 *
	 * @param code - the therapy code
	 * @return <code>true</code> if the code is already in use, <code>false</code> otherwise
	 * @throws OHServiceException 
	 */
	public boolean isCodePresent(Integer code) throws OHServiceException	{
		return repository.exists(code);
	}
}
