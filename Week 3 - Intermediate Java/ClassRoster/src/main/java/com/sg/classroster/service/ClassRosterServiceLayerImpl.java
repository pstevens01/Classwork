/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author Paul
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    ClassRosterDao dao;
    private ClassRosterAuditDao auditDao;
    
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createStudent(Student student) throws 
            ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        // Check if Student Id already exists
        if (dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException(
                "ERROR: Could not create student. Student Id " + student.getStudentId() +
                    " already exists.");
        }
        // Validate all fields on the student obj.
        validateStudentData(student);
        // Valid student object, now persist the student obj.
        dao.addStudent(student.getStudentId(), student);
        // Write to audit log with successful creating of student
        auditDao.writeAuditEntry(
            "Student " + student.getStudentId() + " CREATED.");
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
        // Write to audit log with successful removal of student
        auditDao.writeAuditEntry(
            "Student " + studentId + " REMOVED.");
        return removedStudent;
    }
    
    private void validateStudentData(Student student) throws 
            ClassRosterDataValidationException {
        // Checks if all fields in the student obj is neither null or of 
        // length 0 (strings with just whitespaces).
        if (student.getFirstName() == null
                || student.getFirstName().trim().length() == 0
                || student.getLastName() == null
                || student.getLastName().trim().length() == 0
                || student.getCohort() == null
                || student.getCohort().trim().length() == 0) {
            
            throw new ClassRosterDataValidationException(
                "ERROR: All fields [First name, Last name, Cohort] are required."); 
        }
    }
    
}
