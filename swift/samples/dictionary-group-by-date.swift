struct Event {
    let name: String
    let date: String
}

let events = [
    Event(name: "allen", date: "2020-03-23"),
    Event(name: "clerk", date: "2020-03-31"),
    Event(name: "hank", date: "2020-03-23"),
    Event(name: "oishi", date: "2020-03-31"),
    Event(name: "ooo", date: "2020-03-01"),
]

let groupByDate = Dictionary(grouping: events, by: { $0.date })
    .sorted { $0.0 > $1.0 }

groupByDate.forEach { date, events in
    print(date, events)
}

// -- Extension

struct EventsByDate {
    let date: String
    let events: [Event]
}

extension Array where Element == Event {
    func groupByDate() -> [EventsByDate] {
        return Dictionary(grouping: self, by: { $0.date })
            .sorted { $0.0 > $1.0 }
            .compactMap { EventsByDate(date: $0, events: $1) }
    }
}

print(events.groupByDate())
