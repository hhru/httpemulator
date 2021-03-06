package ru.hh.httpemulator.server;

import org.glassfish.jersey.server.ResourceConfig;
import ru.hh.httpemulator.server.config.EmulatorProdConfig;
import ru.hh.httpemulator.server.utils.AmbiguousRulesExceptionMapper;
import ru.hh.httpemulator.server.utils.RuleNotFoundExceptionMapper;
import ru.hh.httpemulator.server.utils.ScenarioNotFoundExceptionMapper;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.starter.exceptions.AnyExceptionMapper;

public class Launcher {

  public static void main(String[] args) {
    buildApplication().run(EmulatorProdConfig.class);
  }

  static NabApplication buildApplication() {
    return NabApplication.builder()
        .configureJersey()
        .executeOnConfig(Launcher::registerExceptionMappers)
        .bindToRoot()
        .build();
  }

  private static void registerExceptionMappers(ResourceConfig config) {
    config.register(AnyExceptionMapper.class);
    config.register(AmbiguousRulesExceptionMapper.class);
    config.register(RuleNotFoundExceptionMapper.class);
    config.register(ScenarioNotFoundExceptionMapper.class);
  }
}
