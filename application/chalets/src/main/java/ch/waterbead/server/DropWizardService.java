package ch.waterbead.server;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class DropWizardService extends Service<DropWizardConfiguration> {
    public static void main(String[] args) throws Exception {
        new DropWizardService().run(args);
    }
 
    @Override
    public void initialize(Bootstrap<DropWizardConfiguration> bootstrap) {
        bootstrap.setName("lesCrosets");
    }
 
    @Override
    public void run(DropWizardConfiguration configuration, Environment environment) throws Exception {
    	environment.addResource(IndexResource.class);
    	environment.addFilter(new DropWizardFilter(), "/*");
    }
}
