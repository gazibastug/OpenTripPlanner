package org.opentripplanner.api.mapping;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import org.opentripplanner.api.model.ApiAlert;
import org.opentripplanner.routing.alertpatch.TransitAlert;

public class AlertMapper {

  private final Locale locale;

  public AlertMapper(Locale locale) {
    this.locale = locale;
  }

  public List<ApiAlert> mapToApi(Collection<TransitAlert> alerts) {
    // Using {@code null} and not an empty set will minimize the JSON removing the
    // {@code alerts} from the result.
    if (alerts == null || alerts.isEmpty()) {
      return null;
    }

    return alerts.stream().map(this::mapToApi).collect(Collectors.toList());
  }

  ApiAlert mapToApi(TransitAlert domain) {
    ApiAlert api = new ApiAlert();
    if (domain.headerText() != null) {
      api.alertHeaderText = domain.headerText().toString(locale);
    }

    if (domain.descriptionText() != null) {
      api.alertDescriptionText = domain.descriptionText().toString(locale);
    }

    if (domain.url() != null) {
      api.alertUrl = domain.url().toString(locale);
    }

    api.effectiveStartDate = ofNullableInstant(domain.getEffectiveStartDate());
    api.effectiveEndDate = ofNullableInstant(domain.getEffectiveEndDate());

    return api;
  }

  private static Date ofNullableInstant(Instant instant) {
    return Optional.ofNullable(instant).map(Date::from).orElse(null);
  }
}
