//
//  TrainCard.swift
//  Showcase
//
//  Created by Hank Wang on 2023/06/16.
//

import SwiftUI

enum TrainSymbol: String {
    case front = "train.side.front.car"
    case middle = "train.side.middle.car"
    case rear = "train.side.rear.car"
}

struct TrainCard: View {
    let position: TrainSymbol
    
    init(_ position: TrainSymbol) {
        self.position = position
    }
    
    var body: some View {
        Image(systemName: position.rawValue)
    }
}

struct TrainCard_Previews: PreviewProvider {
    static var previews: some View {
        TrainCard(.front)
    }
}
