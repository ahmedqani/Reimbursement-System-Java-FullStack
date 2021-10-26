package com.ahmed.ERS.dao;

import com.ahmed.ERS.model.Reimbursement;
import com.ahmed.ERS.model.User;

import java.util.List;

public interface ReimbursementDao {

    /**
     * Used to retrieve a list of all Animals
     * @return
     */
    List<Reimbursement> getAllReimbursements();

    Reimbursement getReimbursement(int requestid);

    List<Reimbursement> getReimbursementByUserid(int author_id);

    /**
     * Used to persist the user to the datastore
     * @param reimbursementToSave
     */

    Reimbursement saveReimbursement(Reimbursement reimbursementToSave) throws Exception;

    /**
     * Used to persist the user to the datastore
     * @param
     */

    void updateReimbursementStatus(Reimbursement reimbursementStatusToUpdate) throws Exception;


}
