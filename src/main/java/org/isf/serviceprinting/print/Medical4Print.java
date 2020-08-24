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
package org.isf.serviceprinting.print;

import org.isf.medicals.model.Medical;

public class Medical4Print {

	private String medicalDescription;
	private String medicalType;
	private double minQty;
	private double curQty;
	private String expiring;
	
	public Medical4Print(Medical medical){
		medicalDescription=medical.getDescription();
		medicalType=medical.getType().getDescription();
		minQty=medical.getMinqty();
		curQty=medical.getInitialqty()+medical.getInqty()-medical.getOutqty();
		if(curQty<minQty)expiring="Yes";
		else expiring="No";
	}

	public double getCurQty() {
		return curQty;
	}

	public void setCurQty(double curQty) {
		this.curQty = curQty;
	}
	public String getExpiring() {
		return expiring;
	}

	public void setExpiring(String expiring) {
		this.expiring = expiring;
	}

	public String getMedicalDescription() {
		return medicalDescription;
	}

	public void setMedicalDescription(String medicalDescription) {
		this.medicalDescription = medicalDescription;
	}

	public String getMedicalType() {
		return medicalType;
	}

	public void setMedicalType(String medicalType) {
		this.medicalType = medicalType;
	}

	public double getMinQty() {
		return minQty;
	}

	public void setMinQty(double minQty) {
		this.minQty = minQty;
	}
	
}
