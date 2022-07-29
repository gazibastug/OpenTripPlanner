package org.opentripplanner.ext.fares.model;

import java.util.ArrayList;
import java.util.List;
import org.opentripplanner.model.FareLegRule;

public record FareRulesData(
  List<FareAttribute> fareAttributes,
  List<FareRule> fareRules,
  List<FareLegRule> fareLegRules
) {
  public FareRulesData() {
    this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(0));
  }
}
