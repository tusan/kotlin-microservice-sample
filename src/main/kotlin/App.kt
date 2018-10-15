import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

private val itemRepository = ItemRepository()

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, module = Application::main).start(wait = true)
}

fun Application.main() {

    routing {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        get("/") {
            call.respondText { "Hello from my first ktor service :)" }
        }

        itemRouting(itemRepository)
    }
}