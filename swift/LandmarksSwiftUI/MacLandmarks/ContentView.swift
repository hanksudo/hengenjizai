//
//  ContentView.swift
//  MacLandmarks
//
//  Created by Hank Wang on 2023/06/11.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            LandmarkList()
                .frame(minWidth: 700, minHeight: 300)
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(ModelData())
    }
}
