//
//  PrivateView.swift
//  Showcase
//
//  Created by Hank Wang on 2023/04/29.
//

import SwiftUI
import LocalAuthentication

struct PrivateView: View {
    @State private var authenticated = false
    @Environment(\.presentationMode) var presentationMode
    
    var context = LAContext()
    
    var body: some View {
        VStack {
            if authenticated {
                Text("Private View")
                    .font(.largeTitle)
            }
        }
//        .navigationTitle("Private View")
        .onAppear {
            var error: NSError?
            if context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) {
                // Face ID is available, authenticate user
                context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: "Authenticate with Face ID") { success, authenticationError in
                    DispatchQueue.main.async {
                        if success {
                            authenticated = true
                        } else {
                            presentationMode.wrappedValue.dismiss()
                        }
                    }
                }
            } else {
                // Face ID is not available, handle error
                print(error?.localizedDescription ?? "Face ID not available")
            }
        }
    }
}

struct PrivateView_Previews: PreviewProvider {
    static var previews: some View {
        PrivateView()
    }
}
