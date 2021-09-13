/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachinefinal.dao.VendingMachineAuditDao;
import com.sg.vendingmachinefinal.dao.VendingMachinePersistenceException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul
 */
@Component
public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao {
    
   @Override
   public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
       
   }
}
