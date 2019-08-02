package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntries = new HashMap<>();

    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;
        TimeEntry entry = new TimeEntry(
                id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours()
        );
        timeEntries.put(id, entry);
        return entry;
    }

    @Override
    public TimeEntry find(Long timeEntryId) {
        return timeEntries.get(timeEntryId);

    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(Long timeEntryId, TimeEntry timeEntry) {
        if (timeEntries.get(timeEntryId) != null) {
            TimeEntry entry = new TimeEntry(
                    timeEntryId, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours()
            );
            timeEntries.replace(timeEntryId, entry);
            return entry;
        } else {
            return null;
        }

    }

    @Override
    public void delete(Long timeEntryId) {
        timeEntries.remove(timeEntryId);
    }
}
