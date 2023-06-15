//
//  MyScence.swift
//  Showcase
//
//  Created by Hank Wang on 2023/06/14.
//

import SwiftUI

struct MyScence: Scene {
    var body: some Scene {
        WindowGroup {
            TabView {
                ContentView()
                    .tabItem {
                        Label("Home", systemImage: "house")
                    }
                
                LibraryView()
                    .tabItem {
                        Label("Libary", systemImage: "books.vertical")
                    }
                
                SettingsView()
                    .tabItem {
                        Label("Settings", systemImage: "gear")
                    }
            }
        }
    }
}
