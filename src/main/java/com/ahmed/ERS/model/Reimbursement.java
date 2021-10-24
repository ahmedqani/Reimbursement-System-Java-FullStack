package com.ahmed.ERS.model;

import java.util.Date;

public class Reimbursement {
    private long id;
    private long amount;
    private Date submitted;
    private Date resolved;
    private String description;
    private long author_id;
    private long resolver_id;
    private ReimbursementType reimbursementType;
    private ReimbursementStatus reimbursementStatus;



}
