package org.opentripplanner.graph_builder.module.interlining;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.opentripplanner.transit.model._data.TransitModelForTest.stopTime;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.opentripplanner.graph_builder.DataImportIssueStore;
import org.opentripplanner.model.StopPattern;
import org.opentripplanner.model.TripPattern;
import org.opentripplanner.model.plan.PlanTestConstants;
import org.opentripplanner.model.transfer.TransferService;
import org.opentripplanner.routing.trippattern.Deduplicator;
import org.opentripplanner.routing.trippattern.TripTimes;
import org.opentripplanner.transit.model._data.TransitModelForTest;
import org.opentripplanner.transit.model.framework.FeedScopedId;

class InterlineProcessorTest implements PlanTestConstants {

  @Test
  void run() {
    var transferService = new TransferService();
    var processor = new InterlineProcessor(transferService, 100, DataImportIssueStore.noop());

    var patterns = List.of(
      tripPattern("trip-1", "block-1"),
      tripPattern("trip-2", "block-1"),
      tripPattern("trip-2", "block-3")
    );

    var createdTransfers = processor.run(patterns);
    assertEquals(1, createdTransfers.size());

    assertEquals(transferService.listAll(), createdTransfers);

    createdTransfers.forEach(t -> assertTrue(t.getTransferConstraint().isStaySeated()));
  }

  private static TripPattern tripPattern(String tripId, String blockId) {
    var trip = TransitModelForTest
      .trip(tripId)
      .withGtfsBlockId(blockId)
      .withServiceId(new FeedScopedId("1", "1"))
      .build();

    var stopTimes = List.of(stopTime(trip, 0), stopTime(trip, 1), stopTime(trip, 2));
    var stopPattern = new StopPattern(stopTimes);

    var tp = new TripPattern(TransitModelForTest.id(tripId), trip.getRoute(), stopPattern);
    var tripTimes = new TripTimes(trip, stopTimes, new Deduplicator());
    tp.add(tripTimes);
    return tp;
  }
}
