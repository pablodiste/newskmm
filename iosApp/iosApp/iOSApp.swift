import SwiftUI
import shared

@main
struct iOSApp: App {
    
    let observableRepository: ObservableNewsRepository
    
    init() {
        CommonInjectorKt.doInitKoin()
        let repository = KoinHelper().newsRepository()
        observableRepository = ObservableNewsRepository(repository: repository)
    }
    
	var body: some Scene {
		WindowGroup {
            ContentView().environmentObject(observableRepository)
		}
	}
}

class ObservableNewsRepository: ObservableObject {
    @Published public var state: [Article_] = []
    
    let repository: NewsRepository
    var stateWatcher : Closeable?

    init(repository: NewsRepository) {
        self.repository = repository
        stateWatcher = self.repository.watchBreakingNews().watch { [weak self] state in
            if (state is ResponseSuccess) {
                self?.state = (state as! ResponseSuccess).data!.articles
            }
            
        }
    }
    
    public func fetchNews() {
        repository.fetchBreakingNews()
    }
    
    deinit {
        stateWatcher?.close()
    }
}
