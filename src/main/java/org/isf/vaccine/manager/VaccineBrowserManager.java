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
package org.isf.vaccine.manager;

import java.util.ArrayList;
import java.util.List;

import org.isf.generaldata.MessageBundle;
import org.isf.utils.exception.OHDataIntegrityViolationException;
import org.isf.utils.exception.OHServiceException;
import org.isf.utils.exception.OHDataValidationException;
import org.isf.utils.exception.model.OHExceptionMessage;
import org.isf.utils.exception.model.OHSeverityLevel;
import org.isf.vaccine.model.Vaccine;
import org.isf.vaccine.service.VaccineIoOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class that provides gui separation from database operations and gives some
 * useful logic manipulations of the dynamic data (memory)
 *
 * @author Eva
 *
 *
 * modification history
 *  20/10/2011 - Cla - insert vaccinetype managment
 *
 */
@Component
public class VaccineBrowserManager {

    private final Logger logger = LoggerFactory.getLogger(VaccineBrowserManager.class);
    @Autowired
	private VaccineIoOperations ioOperations;
	
	/**
	 * Verify if the object is valid for CRUD and return a list of errors, if any
	 * @param vaccine
	 * @param insert <code>true</code> or updated <code>false</code>
	 * @throws OHServiceException 
	 */
	protected void validateVaccine(Vaccine vaccine, boolean insert) throws OHServiceException {
		String key = vaccine.getCode();
		String description = vaccine.getDescription();
        List<OHExceptionMessage> errors = new ArrayList<OHExceptionMessage>();
        if(key.isEmpty() ){
	        errors.add(new OHExceptionMessage("codeEmptyError", 
	        		MessageBundle.getMessage("angal.vaccine.pleaseinsertacode"), 
	        		OHSeverityLevel.ERROR));
        }
        if(key.length()>10){
	        errors.add(new OHExceptionMessage("codeTooLongError", 
	        		MessageBundle.getMessage("angal.vaccine.codemaxchars"), 
	        		OHSeverityLevel.ERROR));
        }
        if(description.isEmpty() ){
            errors.add(new OHExceptionMessage("descriptionEmptyError", 
            		MessageBundle.getMessage("angal.vaccine.pleaseinsertadescription"), 
            		OHSeverityLevel.ERROR));
        }
        if (insert) {
        	if (codeControl(vaccine.getCode())){
    			throw new OHDataIntegrityViolationException(new OHExceptionMessage(null, 
    					MessageBundle.getMessage("angal.common.codealreadyinuse"), 
    					OHSeverityLevel.ERROR));
    		}
        }
        if(!errors.isEmpty()){
	        throw new OHDataValidationException(errors);
	    }
    }

	/**
	 * returns the list of {@link Vaccine}s in the DB
	 *
	 * @return the list of {@link Vaccine}s
	 */
	public ArrayList<Vaccine> getVaccine() throws OHServiceException {
		return getVaccine(null);
	}

	/**
	 * returns the list of {@link Vaccine}s based on vaccine type code
	 *
	 * @param vaccineTypeCode - the type code.
	 * @return the list of {@link Vaccine}s
	 */
	public ArrayList<Vaccine> getVaccine(String vaccineTypeCode) throws OHServiceException {
		return ioOperations.getVaccine(vaccineTypeCode);
	}
	
	/**
	 * inserts a new {@link Vaccine} in the DB
	 *
	 * @param vaccine - the item to insert
	 * @return <code>true</code> if the item has been inserted, <code>false</code> otherwise
	 */
	public boolean newVaccine(Vaccine vaccine) throws OHServiceException {
		validateVaccine(vaccine, true);
		return ioOperations.newVaccine(vaccine);
	}

	/**
	 * updates the specified {@link Vaccine} object.
	 * @param vaccine - the {@link Vaccine} object to update.
	 * @return <code>true</code> if has been updated, <code>false</code> otherwise.
	 */
	public boolean updateVaccine(Vaccine vaccine) throws OHServiceException {
		validateVaccine(vaccine, false);
        return ioOperations.updateVaccine(vaccine);
    }
	
	/**
	 * deletes a {@link Vaccine} in the DB
	 *
	 * @param vaccine - the item to delete
	 * @return <code>true</code> if the item has been deleted, <code>false</code> otherwise
	 */
	public boolean deleteVaccine(Vaccine vaccine) throws OHServiceException {
		return ioOperations.deleteVaccine(vaccine);
	}
	
	/**
	 * checks if the code is already in use
	 *
	 * @param code - the vaccine code
	 * @return <code>true</code> if the code is already in use, <code>false</code> otherwise
	 */
	public boolean codeControl(String code) throws OHServiceException {
		return ioOperations.isCodePresent(code);
	}
}
