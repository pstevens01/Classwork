/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul
 */
@Component
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {

    public Order orderOne, orderTwo;
    
    private final HashMap<Integer, Order> orderMap = new HashMap<>();
    private final HashMap<String, Product> productMap = new HashMap<>();
    private final HashMap<String, Tax> taxMap = new HashMap<>();
    
    @Autowired
    public FlooringMasteryDaoStubImpl() {
        productMap.put("Wood", new Product("Wood", BigDecimal.ONE, BigDecimal.ONE));
        productMap.put("Other Product", new Product("Other Product", BigDecimal.ONE, BigDecimal.ONE));
        taxMap.put("CA", new Tax("CA", "California", BigDecimal.TEN));
        taxMap.put("TX", new Tax("TX", "Texas", BigDecimal.ONE));
        orderOne = new Order("Paul Stevens", "TX", "Wood", LocalDate.parse("09-11-2021", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 
                BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        orderTwo = new Order("Penelope Stevens", "CA", "Other Product", LocalDate.parse("09-12-2021", DateTimeFormatter.ofPattern("MM-dd-yyyy")), 
                BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        orderMap.put(1, orderOne);
        orderMap.put(2, orderTwo);
    }
    
    @Override
    public int addOrder(Order order) {
        orderMap.put(order.getOrderNumber(), order);
        return 1;
    }

    @Override
    public Order createOrder(String date, String customerName, String state, String productType, BigDecimal area) {
        return new Order(customerName, state, productType, LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy")), area, 
                taxMap.get(state).getStateTaxRate(), productMap.get(productType).getCostPerSqFt(), productMap.get(productType).getLaborCostPerSqFt());}

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        return getAllOrders().stream()
                .filter((order) -> order.getOrderDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrderByNumberAndDate(String orderNum, String date) {
        // Order number and date must match or return null
        if(orderMap.containsKey(Integer.parseInt(orderNum)) && 
                orderMap.get(Integer.parseInt(orderNum)).getOrderDate().equals(LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy")))) {
            return orderMap.get(Integer.parseInt(orderNum));
        } else {
            return null;
        }}

    @Override
    public Order getOrderByNameDate(String date, String customerName) {
        // Create a list of orders that matches the date and customerName params
        List<Order> orderList = getAllOrders().stream().filter((order) -> order.getOrderDate().equals(LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"))) 
                && order.getCustomerName().equals(customerName)).collect(Collectors.toList());
        Order order = null;
        if(!orderList.isEmpty()){
            order = orderList.get(0);
        }
        return order;}

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orderMap.values());
    }

    @Override
    public void editOrder(Order order) throws FlooringMasteryPersistenceException {
        orderMap.replace(order.getOrderNumber(), order);    
    }

    @Override
    public void removeOrder(Order order) {
        orderMap.remove(order.getOrderNumber(), order);
    }

    @Override
    public void updateOrder(Order order) throws FlooringMasteryPersistenceException {
        orderMap.replace(order.getOrderNumber(), order);}

    @Override
    public void recalculateOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrderData() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadOrderData() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportOrdersForBackup() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadProductData() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadTaxData() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasTaxCode(String abbv) {
        return taxMap.containsKey(abbv);
    }

    @Override
    public boolean hasProductType(String product) {
        return productMap.containsKey(product);
    }
    
}
