/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul
 */
@Component
public class FlooringMasteryAuditDaoStubImpl implements FlooringMasteryAuditDao {
    
    @Override
    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException {
        
    }
}
