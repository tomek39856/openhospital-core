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
package org.isf.medicalstockward.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.isf.medicals.model.Medical;
import org.isf.medicalstock.model.Lot;
import org.isf.medicalstockward.model.MovementWard;
import org.isf.patient.model.Patient;
import org.isf.utils.exception.OHException;
import org.isf.ward.model.Ward;

public class TestMovementWard 
{	 
	private GregorianCalendar now = new GregorianCalendar();
	private GregorianCalendar date = new GregorianCalendar(now.get(Calendar.YEAR), 2, 2);
	private boolean isPatient = false;
	private int age = 10;
	private float weight = 78;
	private String description = "TestDescriptionm";
	private Double quantity = 46.;
	private String units = "TestUni";
    
			
	public MovementWard setup(
			Ward ward,
			Patient patient,
			Medical medical,
			Ward wardTo,
			Ward wardFrom,
			Lot lot,
			boolean usingSet) throws OHException 
	{
		MovementWard movementWard;
				
		if (usingSet)
		{
			movementWard = new MovementWard();
			_setParameters(movementWard, ward, patient, medical, wardFrom, wardTo, lot);
		}
		else
		{
			// Create MovementWard with all parameters 
			movementWard = new MovementWard(ward, date, isPatient, patient, age, weight, description, medical, quantity, units, wardTo, wardFrom, lot);
		}
				    	
		return movementWard;
	}
	
	public void _setParameters(
			MovementWard movementWard,
			Ward ward,
			Patient patient,
			Medical medical,
			Ward wardTo,
			Ward wardFrom,
			Lot lot) 
	{	
		movementWard.setAge(age);
		movementWard.setDate(date);
		movementWard.setDescription(description);
		movementWard.setMedical(medical);
		movementWard.setPatient(isPatient);
		movementWard.setPatient(patient);
		movementWard.setQuantity(quantity);
		movementWard.setUnits(units);
		movementWard.setWard(ward);
		movementWard.setWeight(weight);
		movementWard.setWardFrom(wardFrom);
		movementWard.setWardTo(wardTo);
		movementWard.setlot(lot);
		
		return;
	}
	
	public void check(
			MovementWard movementWard) 
	{		
    	assertEquals(age, movementWard.getAge());
    	assertEquals(date, movementWard.getDate());
    	assertEquals(description, movementWard.getDescription());
    	assertEquals(isPatient, movementWard.isPatient());
    	assertEquals(quantity, movementWard.getQuantity());
    	assertEquals(units, movementWard.getUnits());
    	assertEquals(weight, movementWard.getWeight(), 0.1);
		
		return;
	}
}
