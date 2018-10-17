import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*

fun Routing.itemsApi(repository: ItemRepository) {
    get("/") {
        call.respondText { "Hello from my first ktor service :)" }
    }

    get("/items") {
        call.respond(repository.getAll())
    }
    get("/itemRouting/{id}") {
        call.parameters["id"]?.let { id ->
            repository.get(id.toInt())?.let { item ->
                call.respond(item)
            }
        }
        call.respond(HttpStatusCode.NotFound)
    }
    delete("/itemRouting/{id}") {
        call.parameters["id"]?.let { id ->
            repository.remove(id.toInt())
        }

        call.respond(HttpStatusCode.Accepted)
    }
    put("/itemRouting") {
        val item = call.receive<Item>()
        repository.add(item)

        call.respond(HttpStatusCode.Created)
    }
    post("/itemRouting") {
        val item = call.receive<Item>()
        repository.update(item)

        call.respond(HttpStatusCode.OK)
    }
}