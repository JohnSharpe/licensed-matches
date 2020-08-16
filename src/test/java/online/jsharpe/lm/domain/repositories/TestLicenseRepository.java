package online.jsharpe.lm.domain.repositories;

import online.jsharpe.lm.domain.models.License;
import online.jsharpe.lm.domain.repositories.LicenseRepository;

import java.util.List;
import java.util.UUID;

public class TestLicenseRepository implements LicenseRepository {

    private final List<License> licenses;
    private UUID lastCalledWith;

    public TestLicenseRepository(List<License> licenses) {
        this.licenses = licenses;
    }

    @Override
    public List<License> getLicensesForCustomer(UUID customerId) {
        this.lastCalledWith = customerId;
        return licenses;
    }

    public UUID getLastCalledWith() {
        return lastCalledWith;
    }
}
