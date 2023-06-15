//
//  LibraryView.swift
//  Showcase
//
//  Created by Hank Wang on 2023/06/14.
//

import SwiftUI

struct LibraryView: View {
    enum Flavor: String, CaseIterable, Identifiable {
        case chocolate, vanilla, strawberry, carrot
        var id: Self { self }
    }

    @AppStorage("flavor")
    private var selectedFlavor: Flavor = .chocolate
    
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
            
            Group {
                Label("Favorite Books", systemImage: "books.vertical")
                    .labelStyle(.titleAndIcon)
                    .font(.largeTitle)
                
                Divider()
                
                HStack {
                    Picker("Flavor", selection: $selectedFlavor) {
                        Text("Chocolate").tag(Flavor.chocolate)
                        Text("Vanilla").tag(Flavor.vanilla)
                        Text("Strawberry").tag(Flavor.strawberry)
                        Label("Carrot", systemImage: "carrot")
                            .labelStyle(.titleAndIcon)
                            .font(.largeTitle)
                            .tag(Flavor.carrot)
                    }
                    Button("OK") {}
                }
                .controlSize(.large)
                
                Divider()
                
                AsyncImage(url: URL(string: "https://picsum.photos/200/200")) { image in
                    image.resizable()
                } placeholder: {
                    ProgressView()
                }
                .frame(width: 200, height: 200)
                
                Divider()
                
                HStack {
                    Rectangle()
                        .foregroundColor(.blue)
                    Circle()
                        .foregroundColor(.orange)
                    RoundedRectangle(cornerRadius: 15, style: .continuous)
                        .foregroundColor(.green)
                }
                .aspectRatio(3.0, contentMode: .fit)
                .padding()
                
                Divider()
                
                KeywordBubble(keyword: "Car", symbol: "car")
            }
        }
    }
}

struct LibraryView_Previews: PreviewProvider {
    static var previews: some View {
        LibraryView()
    }
}
