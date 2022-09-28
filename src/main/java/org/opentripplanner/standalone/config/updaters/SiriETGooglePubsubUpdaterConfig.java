package org.opentripplanner.standalone.config.updaters;

import static org.opentripplanner.standalone.config.framework.json.OtpVersion.NA;

import org.opentripplanner.ext.siri.updater.SiriETGooglePubsubUpdaterParameters;
import org.opentripplanner.standalone.config.framework.json.NodeAdapter;

public class SiriETGooglePubsubUpdaterConfig {

  public static SiriETGooglePubsubUpdaterParameters create(String configRef, NodeAdapter c) {
    return new SiriETGooglePubsubUpdaterParameters(
      configRef,
      c.of("feedId").withDoc(NA, /*TODO DOC*/"TODO").withExample(/*TODO DOC*/"TODO").asString(null),
      c.of("type").withDoc(NA, /*TODO DOC*/"TODO").withExample(/*TODO DOC*/"TODO").asString(),
      c
        .of("projectName")
        .withDoc(NA, /*TODO DOC*/"TODO")
        .withExample(/*TODO DOC*/"TODO")
        .asString(),
      c.of("topicName").withDoc(NA, /*TODO DOC*/"TODO").withExample(/*TODO DOC*/"TODO").asString(),
      c
        .of("dataInitializationUrl")
        .withDoc(NA, /*TODO DOC*/"TODO")
        .withExample(/*TODO DOC*/"TODO")
        .asString(null),
      c.of("reconnectPeriodSec").withDoc(NA, /*TODO DOC*/"TODO").asInt(30),
      c.of("purgeExpiredData").withDoc(NA, /*TODO DOC*/"TODO").asBoolean(false)
    );
  }
}
