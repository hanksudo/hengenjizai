//
//  PublicPrivateView.swift
//  Showcase
//
//  Created by Hank Wang on 2023/04/29.
//

import SwiftUI


struct PublicPrivateView: View {
    @State private var isAuthenticated = false
    @State private var alertMessage = ""
    @State private var showAlert = false
    @State private var goPublicView = false
    
    
    var body: some View {
        VStack {
            NavigationLink(destination: PublicView()) {
                Text("Go to public view")
            }
            NavigationLink(destination: PrivateView()) {
                Text("Go to private view")
            }
        }
        .navigationTitle("Public / Private View")
    }
}

struct PublicPrivateView_Previews: PreviewProvider {
    static var previews: some View {
        PublicPrivateView()
    }
}
