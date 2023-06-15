//
//  EventTile.swift
//  Showcase
//
//  Created by Hank Wang on 2023/06/15.
//

import SwiftUI

struct Event {
    let title: String
    let date: Date
    let location: String
    let symbol: String
}

struct EventTile: View {
    let event: Event
    let stripeHeight = 15.0
    var body: some View {
        HStack(alignment: .firstTextBaseline) {
            Image(systemName: event.symbol)
                .font(.title)
            VStack(alignment: .leading) {
                Text(event.title)
                    .font(.title)
                Text(
                    event.date,
                    format: Date.FormatStyle()
                        .day(.defaultDigits)
                        .month(.wide)
                )
                Text(event.location)
            }
        }
        .padding()
        .padding(.top, stripeHeight)
        .background {
            ZStack(alignment: .top) {
                Rectangle()
                    .opacity(0.3)
                Rectangle()
                    .frame(maxHeight: stripeHeight)
            }
            .foregroundColor(.teal)
        }
        .clipShape(RoundedRectangle(cornerRadius: stripeHeight, style: .continuous))
    }
}

struct EventTile_Previews: PreviewProvider {
    static let event = Event(title: "Buy Daisies", date: .now, location: "Flower Shop", symbol: "gift")
    
    static var previews: some View {
        EventTile(event: event)
    }
}
