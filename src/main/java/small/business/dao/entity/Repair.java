package small.business.dao.entity;

import java.io.Serializable;

public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String dateOfAdmissionToRepair;
    private String dateOfBeginRepair;
    private String dateOfEndRepair;
    private String dateOfTakenFromRepair;
    private CounterParties counterParty;
    private double price;
    private String info;
}