//
//  ShowcaseList.swift
//  Showcase
//
//  Created by Hank Wang on 2023/04/28.
//

import SwiftUI

struct ShowcaseList: View {
    var body: some View {
        NavigationView {
            List {
                NavigationLink {
                    PublicPrivateView()
                } label: {
                    Label("Public / Private View", systemImage: "lock")
                }
                NavigationLink {
                    LibraryView()
                } label: {
                    Label("Libary", systemImage: "books.vertical")
                }
                NavigationLink {
                    TimerView()
                } label: {
                    Label("Timer", systemImage: "timer")
                }
            }
            .navigationTitle("Showcases")
        }
    }
}

struct ShowcaseList_Previews: PreviewProvider {
    static var previews: some View {
        ShowcaseList()
    }
}
