//
//  CaptionedPhoto.swift
//  Showcase
//
//  Created by Hank Wang on 2023/06/15.
//

import SwiftUI

struct CaptionedPhoto: View {
    let captionText: String
    
    var body: some View {
        AsyncImage(url: URL(string: "https://picsum.photos/200/200")) { image in
            image
                .resizable()
                .scaledToFit()
                .overlay(alignment: .bottom) {
                    Caption(text: captionText)
                }
                .clipShape(RoundedRectangle(cornerRadius: 10.0, style: .continuous))
                .padding()
        } placeholder: {
            ProgressView()
        }
    }
}

struct Caption: View {
    let text: String
    var body: some View {
        Text(text)
            .padding()
            .background(Color("TextContrast").opacity(0.75),
                        in: RoundedRectangle(cornerRadius: 10.0, style: .continuous))
            .padding()
    }
}

struct CaptionedPhoto_Previews: PreviewProvider {
    static var previews: some View {
        CaptionedPhoto(captionText: "What's this?")
            .preferredColorScheme(.dark)
        CaptionedPhoto(captionText: "What's this?")
            .preferredColorScheme(.light)
    }
}
