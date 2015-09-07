package br.gov.servicos.utils;

import lombok.experimental.FieldDefaults;
import net.logstash.logback.marker.LogstashMarker;
import org.eclipse.jgit.lib.BatchingProgressMonitor;
import org.slf4j.Logger;

import static lombok.AccessLevel.PRIVATE;
import static net.logstash.logback.marker.Markers.append;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class LogstashProgressMonitor extends BatchingProgressMonitor {

    Logger log;

    public LogstashProgressMonitor(Logger log) {
        this.log = log;
    }

    @Override
    protected void onUpdate(String taskName, int workCurr) {
    }

    @Override
    protected void onEndTask(String taskName, int workCurr) {
        LogstashMarker marker = append("task", taskName).and(append("work", workCurr)).and(append("done", true));
        log.info(marker, taskName);
    }

    @Override
    protected void onUpdate(String taskName, int workCurr, int workTotal, int percentDone) {
    }

    @Override
    protected void onEndTask(String taskName, int workCurr, int workTotal, int percentDone) {
        LogstashMarker marker = append("task", taskName)
                .and(append("work", workCurr))
                .and(append("total", workTotal))
                .and(append("percent", percentDone))
                .and(append("done", true));

        log.info(marker, taskName);
    }
}
