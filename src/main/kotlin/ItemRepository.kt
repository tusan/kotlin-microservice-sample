import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger

class ItemRepository : KoinComponent {
    private val idCounter: AtomicInteger by inject()
    private val items: CopyOnWriteArrayList<Item> by inject()

    fun add(i: Item) =
            if (!items.contains(i)) {
                val id = idCounter.incrementAndGet()
                items.add(i.copy(id = id))
                id
            } else throw java.lang.IllegalArgumentException("Duplicate $i")

    fun remove(id: Int) =
        get(id)?.let { remove(it) }
                ?: throw java.lang.IllegalArgumentException("not found $id")



    fun update(i: Item): Int {
        remove(i.id)
        return add(i)
    }

    fun getAll() = items
    fun get(id: Int) = items.find { i -> i.id == id }

    private fun remove(i: Item) {
        if (!items.contains(i)) {
            throw IllegalArgumentException("not contained $i")
        }
        items.remove(i)
    }
}

data class Item(val id: Int, val name: String, val price: Double)