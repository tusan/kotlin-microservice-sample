import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.inject
import org.koin.standalone.StandAloneContext.startKoin

fun main(args: Array<String>) {
    startKoin(listOf(applicationContext))
    embeddedServer(Netty, 8080, module = Application::main).start(wait = true)
}

fun Application.main() {
    val itemRepository: ItemRepository by inject()

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    install(Routing) {
        itemsApi(itemRepository)
    }
}

