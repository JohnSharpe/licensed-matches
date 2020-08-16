package online.jsharpe.lm.domain.repositories.licenses;

import online.jsharpe.lm.domain.models.License;

import java.util.List;
import java.util.UUID;

public interface LicenseRepository {

    List<License> getLicensesForCustomer(UUID customerId);

}
