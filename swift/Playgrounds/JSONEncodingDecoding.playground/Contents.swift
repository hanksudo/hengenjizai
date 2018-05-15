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

func main() {
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

main()

PlaygroundPage.current.needsIndefiniteExecution = true


//{
//    "avatar_url": "https://avatars2.githubusercontent.com/u/467745?v=4",
//    "bio": null,
//    "blog": "",
//    "company": "Health2Sync",
//    "created_at": "2010-11-04T18:31:48Z",
//    "email": null,
//    "events_url": "https://api.github.com/users/hanksudo/events{/privacy}",
//    "followers": 84,
//    "followers_url": "https://api.github.com/users/hanksudo/followers",
//    "following": 457,
//    "following_url": "https://api.github.com/users/hanksudo/following{/other_user}",
//    "gists_url": "https://api.github.com/users/hanksudo/gists{/gist_id}",
//    "gravatar_id": "",
//    "hireable": null,
//    "html_url": "https://github.com/hanksudo",
//    "id": 467745,
//    "location": "Taipei, Taiwan & Tokyo, Japan",
//    "login": "hanksudo",
//    "name": "Hank Wang",
//    "organizations_url": "https://api.github.com/users/hanksudo/orgs",
//    "public_gists": 144,
//    "public_repos": 84,
//    "received_events_url": "https://api.github.com/users/hanksudo/received_events",
//    "repos_url": "https://api.github.com/users/hanksudo/repos",
//    "site_admin": false,
//    "starred_url": "https://api.github.com/users/hanksudo/starred{/owner}{/repo}",
//    "subscriptions_url": "https://api.github.com/users/hanksudo/subscriptions",
//    "type": "User",
//    "updated_at": "2018-05-07T03:09:10Z",
//    "url": "https://api.github.com/users/hanksudo"
//}
