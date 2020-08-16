package online.jsharpe.lm.application;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import online.jsharpe.lm.domain.LicensedMatchService;
import online.jsharpe.lm.application.repositories.LocalLicensedMatchRepository;
import online.jsharpe.lm.application.repositories.LocalLicenseRepository;
import online.jsharpe.lm.application.repositories.LocalMatchRepository;
import online.jsharpe.lm.domain.summary.SummaryGenerator;
import online.jsharpe.lm.domain.summary.clock.SystemClock;

public class LMApplication extends Application<LMConfiguration> {

    public static void main(String[] args) throws Exception {
        new LMApplication().run(args);
    }

    @Override
    public String getName() {
        return "licensed-matches";
    }

    @Override
    public void run(LMConfiguration lmConfiguration, Environment environment) {

        final LicensedMatchService licensedMatchService = new LicensedMatchService(
                new SummaryGenerator(new SystemClock()),
                new LocalLicensedMatchRepository(
                        new LocalLicenseRepository(),
                        new LocalMatchRepository()
                )
        );

        environment.jersey().register(licensedMatchService);

    }
}
