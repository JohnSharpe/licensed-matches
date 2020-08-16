package online.jsharpe.lm.domain.repositories.licenses;

import online.jsharpe.lm.domain.models.License;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class LocalLicenseRepository implements LicenseRepository {

    private final List<License> licenses = new ArrayList<>();

    /**
     * Get all licences from a local store that are held by a given customer.
     * Copies are returned to prevent leaking the original pointers.
     *
     * @param customerId the customer identifier
     * @return Each license held by the given customer
     */
    @Override
    public List<License> getLicensesForCustomer(UUID customerId) {
        return licenses.stream()
                .filter(license -> Objects.equals(customerId, license.getCustomerId()))
                .map(License::new)
                .collect(Collectors.toList());
    }
}
