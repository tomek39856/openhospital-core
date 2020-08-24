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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isf.operation.manager;

import java.util.ArrayList;
import java.util.List;

import org.isf.admission.model.Admission;
import org.isf.opd.model.Opd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.isf.operation.model.OperationRow;
import org.isf.operation.service.OperationRowIoOperations;
import org.isf.utils.exception.OHServiceException;
/**
 *
 * @author xavier
 */
@Component
public class OperationRowBrowserManager {
    private final Logger logger = LoggerFactory.getLogger(OperationRowBrowserManager.class);
    @Autowired
    private OperationRowIoOperations ioOperations;
    
    public List<OperationRow> getOperationRowByAdmission(Admission adm) throws OHServiceException{
	return ioOperations.getOperationRowByAdmission(adm);
    }
    
    public ArrayList<OperationRow> getOperationRowByOpd(Opd opd) throws OHServiceException {
        return ioOperations.getOperationRowByOpd(opd);
    }
    
    public boolean deleteOperationRow(OperationRow operationRow) throws OHServiceException {
        return ioOperations.deleteOperationRow(operationRow);
    }

    public boolean updateOperationRow(OperationRow opRow) throws OHServiceException {
        try {
            ioOperations.updateOperationRow(opRow);
            return true;
        } catch (OHServiceException ex) {
            throw ex;
        }
    }

    public boolean newOperationRow(OperationRow opRow) throws OHServiceException {
        try {
            ioOperations.newOperationRow(opRow);
            return true;
        } catch (OHServiceException ex) {
        	throw ex;
        }
    }
}
