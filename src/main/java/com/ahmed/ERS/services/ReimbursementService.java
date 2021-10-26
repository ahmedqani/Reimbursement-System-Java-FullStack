package com.ahmed.ERS.services;

import com.ahmed.ERS.dao.DAOUtilities;
import com.ahmed.ERS.dao.ReimbursementDao;
import com.ahmed.ERS.dao.UserDao;
import com.ahmed.ERS.exceptions.InvalidCredentialsException;
import com.ahmed.ERS.logging.Logging;
import com.ahmed.ERS.model.Reimbursement;
import com.ahmed.ERS.model.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ReimbursementService {
    //Call DAO method
    ReimbursementDao dao = DAOUtilities.getReimbursementDao();

    public List<Reimbursement> getAllReimbursements() {
        return dao.getAllReimbursements();
    }

    public Reimbursement getReimbursement(int requestid) {
        Reimbursement reimbursement = dao.getReimbursement(requestid);
        return reimbursement;
    }

    public List<Reimbursement> getReimbursementByUserid(int author_id) {
        return dao.getReimbursementByUserid(author_id);
    }



    public Reimbursement updateReimbursementStatus(Reimbursement reimbursementStatusToUpdate) throws Exception {
        dao.updateReimbursementStatus(reimbursementStatusToUpdate);
        Logging.logger.warn("Reimbursement: " + reimbursementStatusToUpdate.getId() + " 's Status has been changed to " + reimbursementStatusToUpdate.getReimbursementStatus());
        return reimbursementStatusToUpdate;
    }


    public Reimbursement saveReimbursement(Reimbursement reimbursementToSave) {
        try {
            dao.saveReimbursement(reimbursementToSave);
            Logging.logger.info("Reimbursement: " + reimbursementToSave.getReimbursementType() + " was Created");
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            System.out.println("ID is already in use");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem creating the Reimbursement at this time");
        }
        return reimbursementToSave;
    }


}
