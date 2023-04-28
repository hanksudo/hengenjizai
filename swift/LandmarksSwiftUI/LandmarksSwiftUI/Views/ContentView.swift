//
//  ContentView.swift
//  LandmarksSwiftUI
//
//  Created by Hank Wang on 2023/04/27.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        LandmarkList()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(ModelData())
    }
}
