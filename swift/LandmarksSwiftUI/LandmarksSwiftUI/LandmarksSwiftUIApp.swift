//
//  LandmarksSwiftUIApp.swift
//  LandmarksSwiftUI
//
//  Created by Hank Wang on 2023/04/27.
//

import SwiftUI

@main
struct LandmarksSwiftUIApp: App {
    @StateObject private var modelData = ModelData()
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(modelData)
        }
        
        #if os(watchOS)
        WKNotificationScene(controller: NotificationController.self, category: "LandmarkNear")
        #endif
    }
}
