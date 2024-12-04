package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.controllers.RoomController;
import org.kainos.ea.controllers.TestController;
import org.kainos.ea.daos.RoomDao;
import org.kainos.ea.daos.TestDao;
import org.kainos.ea.services.RoomService;
import org.kainos.ea.services.TestService;

public class PigeonApplication extends Application<PigeonConfiguration> {
    public static void main(final String[] args) throws Exception {
        new PigeonApplication().run(args);
    }

    @Override
    public String getName() {
        return "Pigeon";
    }

    @Override
    public void initialize(final Bootstrap<PigeonConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    final PigeonConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(final PigeonConfiguration configuration,
                    final Environment environment) {
        environment.jersey()
                .register(new TestController(new TestService(new TestDao())));
        environment.jersey()
                .register(new RoomController(new RoomService(new RoomDao())));
    }

}
