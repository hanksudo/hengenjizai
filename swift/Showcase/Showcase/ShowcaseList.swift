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
                    Text("Public / Private View")
                }
                NavigationLink {
                    TimerView()
                } label: {
                    Text("Timer")
                }
                
                Text("Case 3")
                    
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
