//
//  AlternativeContentView.swift
//  Showcase
//
//  Created by Hank Wang on 2023/06/14.
//

import SwiftUI

struct AlternativeContentView: View {
    var body: some View {
        NavigationSplitView {
            List {
                NavigationLink {
                    PublicPrivateView()
                } label: {
                    Label("Public/Private View", systemImage: "lock")
                }
                NavigationLink {
                    TimerView()
                } label: {
                    Label("Timer", systemImage: "timer")
                }
                NavigationLink {
                    LibraryView()
                } label: {
                    Label("Libary", systemImage: "books.vertical")
                }
                NavigationLink {
                    SettingsView()
                } label: {
                    Label("Settings", systemImage: "gear")
                }
            }
            .navigationTitle("Showcases")
        } detail: {
            
        }
    }
}

struct AlternativeContentView_Previews: PreviewProvider {
    static var previews: some View {
        AlternativeContentView()
    }
}
