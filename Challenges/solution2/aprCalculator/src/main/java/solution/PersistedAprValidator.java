package solution;

import java.util.Optional;

public class PersistedAprValidator extends AprValidator {

    private AprValidator aprValidator;
    private DBUtils dbUtils;


    public PersistedAprValidator(AprValidator aprValidator, DBUtils dbUtils) throws ClassNotFoundException {
        this.aprValidator = aprValidator;
        this.dbUtils = dbUtils;
    }

    @Override
    public Optional<Double> resolve(Loan loan) {
        Optional<Double> result = aprValidator.resolve(loan);
        if (result.isPresent()) {
            dbUtils.save(loan.getId(), result.get());
        }
        return result;
    }

}