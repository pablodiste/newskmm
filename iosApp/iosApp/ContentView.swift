import SwiftUI
import shared

struct ContentView: View {
    
	let greet = Greeting().greeting()
    @EnvironmentObject var store: ObservableNewsRepository

	var body: some View {
        List(store.state, id: \.id) { item in
            Text(item.title!).padding(10)
        }.onAppear {
            store.fetchNews()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
