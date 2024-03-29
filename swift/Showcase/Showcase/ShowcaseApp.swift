//
//  ShowcaseApp.swift
//  Showcase
//
//  Created by Hank Wang on 2023/04/28.
//

import SwiftUI

@main
struct ShowcaseApp: App {
    var body: some Scene {
        #if os(iOS)
        MyScence()
        #elseif os(macOS)
        MyAlternativeScene()
        #endif
    }
}
