import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Route.itemRouting(itemRepository: ItemRepository) {
    get("/items") {
        call.respond(itemRepository.getAll())
    }

    get("/itemRouting/{id}") {
        call.parameters["id"]?.let { id ->
            itemRepository.get(id.toInt())?.let { item ->
                call.respond(item)
            }
        }
        call.respond(HttpStatusCode.NotFound)
    }

    delete("/itemRouting/{id}") {
        call.parameters["id"]?.let { id ->
            itemRepository.remove(id.toInt())
        }

        call.respond(HttpStatusCode.Accepted)
    }

    put("/itemRouting") {
        val item = call.receive<Item>()
        itemRepository.add(item)

        call.respond(HttpStatusCode.Created)
    }

    post("/itemRouting") {
        val item = call.receive<Item>()
        itemRepository.update(item)

        call.respond(HttpStatusCode.OK)
    }
}