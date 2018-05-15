// Swift 4 with struct with codable
// JSON Encoding and Decoding

import PlaygroundSupport
import UIKit

struct MyGithub: Codable {

    let name: String?
    let location: String?
    let followers: Int?
    let avatarUrl: URL?
    let repos: Int?

    private enum CodingKeys: String, CodingKey {
        case name
        case location
        case followers
        case repos = "public_repos"
        case avatarUrl = "avatar_url"
    }
}

func decodeDemo() {
    guard let gitUrl = URL(string: "https://api.github.com/users/hanksudo") else { return }
    URLSession.shared.dataTask(with: gitUrl) { (data, response, error) in
        guard let data = data else { return }
    
        do {
            let decoder = JSONDecoder()
            let gitData = try decoder.decode(MyGithub.self, from: data)
            DispatchQueue.main.async {
                print(gitData.name ?? "")
                print(gitData.location ?? "")
                print(gitData.followers ?? 1)
            }
        } catch let err {
            print("Err", err)
        }
    }.resume()
}

func encodeDemo() {
    // Encode
    let me = MyGithub(name: "Hank Wang", location: "Taiwan", followers: 0, avatarUrl: nil, repos: 0)
    let encoder = JSONEncoder()
    let data = try! encoder.encode(me)
    let string = String(data: data, encoding: .utf8)
    print(string!)
}

decodeDemo()
encodeDemo()

PlaygroundPage.current.needsIndefiniteExecution = true
