package ru.hh.httpemulator.server.scenario;

import java.util.Collection;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import ru.hh.httpemulator.client.entity.AttributeType;
import ru.hh.httpemulator.client.entity.HttpEntry;

@Named(value = TimeoutScenario.SCENARIO_NAME)
public class TimeoutScenario implements Scenario {
  static final String SCENARIO_NAME = "timeoutScenario";

  private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutScenario.class);

  private static final int DEFAULT_TIMEOUT = 5000;

  private static final String TIMEOUT_KEY = "timeout";

  @Override
  public Collection<HttpEntry> execute(HttpServletRequest request, Response.ResponseBuilder response, Collection<HttpEntry> otherEntries) {

    int timeout = DEFAULT_TIMEOUT;
    if (!CollectionUtils.isEmpty(otherEntries)) {
      for (HttpEntry httpEntry : otherEntries) {
        if (httpEntry.getType().equals(AttributeType.PARAMETER) && TIMEOUT_KEY.equals(httpEntry.getKey())) {
          timeout = Integer.parseInt(httpEntry.getValue());
        }
      }
    }

    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      LOGGER.warn("", e);
    }

    return otherEntries;
  }
}
