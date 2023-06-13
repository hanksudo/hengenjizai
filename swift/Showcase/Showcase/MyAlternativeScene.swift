//
//  MyAlternativeScene.swift
//  Showcase
//
//  Created by Hank Wang on 2023/06/14.
//

import SwiftUI

struct MyAlternativeScene: Scene {
    var body: some Scene {
        WindowGroup {
            AlternativeContentView()
        }
        
        #if os(macOS)
        Settings {
            SettingsView()
        }
        #endif
    }
}
