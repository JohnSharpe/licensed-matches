package online.jsharpe.lm.domain.repositories;

import online.jsharpe.lm.domain.models.License;

import java.util.List;
import java.util.UUID;

public interface LicenseRepository {

    List<License> getLicensesForCustomer(UUID customerId);

}
