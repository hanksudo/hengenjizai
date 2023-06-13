//
//  LibraryView.swift
//  Showcase
//
//  Created by Hank Wang on 2023/06/14.
//

import SwiftUI

struct LibraryView: View {
    var body: some View {
        VStack {
            HStack {
                Image(systemName: "folder.badge.plus")
                Image(systemName: "heart.circle.fill")
                Image(systemName: "alarm")
            }
            .symbolRenderingMode(.multicolor)
            .font(.largeTitle)
            
            Divider()
            Label("Favorite Books", systemImage: "books.vertical")
                .labelStyle(.titleAndIcon)
                .font(.largeTitle)
        }
    }
}

struct LibraryView_Previews: PreviewProvider {
    static var previews: some View {
        LibraryView()
    }
}
