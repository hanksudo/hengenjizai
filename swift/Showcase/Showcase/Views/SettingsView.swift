//
//  SettingsView.swift
//  Showcase
//
//  Created by Hank Wang on 2023/06/14.
//

import SwiftUI

struct SettingsView: View {
    var body: some View {
        #if os(macOS)
        SettingsInTabView()
        #else
        SettingsInNavigationStack()
        #endif
    }
    
    private enum Settings: String, CaseIterable {
        case account = "Account"
        case sync = "Sync"
        case general = "General"
        case appIcon = "App icon"
        
        var image: String {
            switch self {
            case .account:
                return "person.crop.circle"
            case .sync:
                return "cloud"
            case .general:
                return "gear"
            case .appIcon:
                return "app"
            }
        }
    }
    
    private func SettingsInNavigationStack() -> some View {
        NavigationStack {
            List {
                NavigationLink {
                    SettingsDetailView(title: Settings.account.rawValue)
                } label: {
                    Label(Settings.account.rawValue, systemImage: Settings.account.image)
                }
                NavigationLink {
                    SettingsDetailView(title: Settings.sync.rawValue)
                } label: {
                    Label(Settings.sync.rawValue, systemImage: Settings.sync.image)
                }
                
                Section {
                    NavigationLink {
                        SettingsDetailView(title: Settings.general.rawValue)
                    } label: {
                        Label(Settings.general.rawValue, systemImage: Settings.general.image)
                    }
                    NavigationLink {
                        SettingsDetailView(title: Settings.appIcon.rawValue)
                    } label: {
                        Label(Settings.appIcon.rawValue, systemImage: Settings.appIcon.image)
                    }
                }
            }
            .navigationTitle("Settings")
        }
    }
    
    private func SettingsInTabView() -> some View {
        TabView {
            ForEach(Settings.allCases, id: \.self) { item in
                SettingsDetailView(title: item.rawValue)
                    .tabItem {
                        Label(item.rawValue, systemImage: item.image)
                    }
                    .tag(item)
            }
        }
        .frame(width: 375, height: 150)
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView()
    }
}
