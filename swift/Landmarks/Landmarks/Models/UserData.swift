//
//  UserData.swift
//  Landmarks
//
//  Created by Hank Wang on 2020/3/3.
//

import SwiftUI
import Combine

final class UserData: ObservableObject {
    @Published var showFavoritesOnly = false
    @Published var landmarks = landmarkData
    @Published var profile = Profile.default
}
